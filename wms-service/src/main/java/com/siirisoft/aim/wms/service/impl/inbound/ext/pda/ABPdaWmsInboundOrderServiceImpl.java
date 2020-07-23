package com.siirisoft.aim.wms.service.impl.inbound.ext.pda;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.siirisoft.aim.wms.entity.events.WmsObjectEvents;
import com.siirisoft.aim.wms.entity.inbound.WmsInboundOrderHead;
import com.siirisoft.aim.wms.entity.inbound.ext.pda.WmsPdaInboundOrderDetail;
import com.siirisoft.aim.wms.entity.locator.ext.WmsLocatorExt;
import com.siirisoft.aim.wms.entity.quantity.WmsItemOnhandQuantity;
import com.siirisoft.aim.wms.entity.sqlitem.WmsSglItem;
import com.siirisoft.aim.wms.mapper.events.WmsObjectEventsMapper;
import com.siirisoft.aim.wms.mapper.inbound.WmsInboundOrderDetailMapper;
import com.siirisoft.aim.wms.mapper.inbound.WmsInboundOrderLineMapper;
import com.siirisoft.aim.wms.mapper.inbound.ext.pda.WmsPdaInboundOrderDetailMapper;
import com.siirisoft.aim.wms.mapper.locator.ext.pda.WmsPdaLocatorMapperExt;
import com.siirisoft.aim.wms.mapper.quantity.WmsItemOnhandQuantityMapper;
import com.siirisoft.aim.wms.mapper.sqlitem.WmsSglItemMapper;
import com.siirisoft.aim.wms.mapper.sqlitem.ext.WmsSglItemMapperExt;
import com.siirisoft.aim.wms.service.inbound.IWmsInboundOrderHeadService;
import com.siirisoft.aim.wms.service.inbound.pda.ABPdaWmsInboundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @User DKY
 * @Date 2020/6/17
 * @Description ABPdaWmsInboundOrderService实现类
 */
@Service
public class ABPdaWmsInboundOrderServiceImpl implements ABPdaWmsInboundOrderService {

    @Autowired
    private WmsPdaInboundOrderDetailMapper wmsPdaInboundOrderDetailMapper;

    @Autowired
    private WmsSglItemMapperExt wmsSglItemMapperExt;

    @Autowired
    private WmsSglItemMapper wmsSglItemMapper;

    @Autowired
    private WmsItemOnhandQuantityMapper wmsItemOnhandQuantityMapper;

    @Autowired
    private WmsInboundOrderLineMapper wmsInboundOrderLineMapper;

    @Autowired
    private WmsInboundOrderDetailMapper wmsInboundOrderDetailMapper;

    @Autowired
    private WmsObjectEventsMapper wmsObjectEventsMapper;

    @Autowired
    private WmsPdaLocatorMapperExt wmsPdaLocatorMapperExt;

    @Autowired
    private IWmsInboundOrderHeadService inboundOrderHeadService;


    @Override
    public IPage queryInboundOrderDetail(Page page, Wrapper wrapper) {
        return wmsPdaInboundOrderDetailMapper.queryInboundOrderDetail(page, wrapper);
    }

    @Override
    @Transactional
    public synchronized boolean commitInboundOrder(WmsPdaInboundOrderDetail wmsPdaInboundOrderDetail) {
        QueryWrapper wrapper = new QueryWrapper();
        Integer headId = wmsPdaInboundOrderDetail.getHeadId();
        WmsInboundOrderHead inboundHead = inboundOrderHeadService.getById(headId);

        wrapper.eq("locator_id", wmsPdaInboundOrderDetail.getExcuLocatorId());
        //获取要更新的这个板子要去货位的最大层号
        Integer maxLayerNumber = wmsSglItemMapperExt.findMaxLayerNumber(wrapper);

        //更新sgl表中的货位位置，以及层号 由于是一张板子，不涉及此处不涉及数量变化
        WmsSglItem wmsSglItem = new WmsSglItem();
        wmsSglItem.setLocatorId(wmsPdaInboundOrderDetail.getExcuLocatorId());
        wmsSglItem.setLayerNumber(maxLayerNumber + 1);
        wmsSglItem.setLastUpdateBy(inboundHead.getCreatedBy());
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("d_sequence_num", wmsPdaInboundOrderDetail.getDSequenceNum());
        wmsSglItemMapper.update(wmsSglItem, updateWrapper);

        //现有量更新，一笔增一笔减, 由于单张钢板入库，每次增减数量为1 原有货位 -1 目标货位  + 1
        //更新过后的钢板
        WmsSglItem sglItem = wmsSglItemMapper.selectOne(updateWrapper);
        //更新现有量
        QueryWrapper quantityWrapper = new QueryWrapper();
        quantityWrapper.eq("item_id", sglItem.getItemId());
        quantityWrapper.eq("lot_number", sglItem.getLotNumber());
        quantityWrapper.eq("locator_id", wmsPdaInboundOrderDetail.getAdvLocatorId());
        wmsItemOnhandQuantityMapper.delete(quantityWrapper);

        WmsItemOnhandQuantity wmsItemOnhandQuantity = new WmsItemOnhandQuantity();
        wmsItemOnhandQuantity.setLocatorId(sglItem.getLocatorId());
        wmsItemOnhandQuantity.setLoctOnhand(1);
        wmsItemOnhandQuantity.setWarehouseId(sglItem.getWarehouseId());
        wmsItemOnhandQuantity.setLotNumber(sglItem.getLotNumber());
        wmsItemOnhandQuantity.setItemId(sglItem.getItemId());
        wmsItemOnhandQuantity.setUomCode(sglItem.getWeightUom());
        wmsItemOnhandQuantity.setPlantId(sglItem.getPlantId());
        wmsItemOnhandQuantityMapper.insert(wmsItemOnhandQuantity);

        //更新明细信息信息
        wmsPdaInboundOrderDetail.setExcuLotCode(wmsPdaInboundOrderDetail.getShipNumber() + wmsPdaInboundOrderDetail.getSectionNum()); //执行批次，船号+分段号
        wmsPdaInboundOrderDetail.setExcuQuantity(1);
        wmsInboundOrderDetailMapper.updateById(wmsPdaInboundOrderDetail);

        //更新行信息
        QueryWrapper lineWrapper = new QueryWrapper();
        lineWrapper.eq("line_id", wmsPdaInboundOrderDetail.getLineId());
        wmsInboundOrderLineMapper.updateInboundLineInfo(lineWrapper);

        //插入事务
        WmsObjectEvents wmsObjectEvents = new WmsObjectEvents();
        wmsObjectEvents.setLocatorIdTo(sglItem.getLocatorId());
        wmsObjectEvents.setLocatorIdFrom(wmsPdaInboundOrderDetail.getAdvLocatorId());
        wmsObjectEvents.setEventTime(new Date());
        wmsObjectEvents.setEventQty(1);
        wmsObjectEvents.setItemId(sglItem.getItemId());
        wmsObjectEvents.setEventTypeId(2);
        wmsObjectEvents.setEventTypeCode("GD_ASN_INSTOCK");
        wmsObjectEvents.setCreationDate(new Date());
        wmsObjectEvents.setCreatedBy(inboundHead.getCreatedBy());
        wmsObjectEvents.setSupplierId(inboundHead.getSupplierId());
        wmsObjectEvents.setSourceDocType(inboundHead.getSourceDocType());
        wmsObjectEvents.setSourceDocNumber(inboundHead.getDocNumber());
        wmsObjectEvents.setSourceDocHeadId(inboundHead.getHeadId());
        wmsObjectEvents.setSourceDocLineId(wmsPdaInboundOrderDetail.getLineId());
        wmsObjectEvents.setWarehouseIdFrom(sglItem.getWarehouseId());
        wmsObjectEvents.setWarehouseIdTo(sglItem.getWarehouseId());
        wmsObjectEvents.setLastUpdateDate(new Date());
        wmsObjectEvents.setBarcode(sglItem.getDSequenceNum());
        wmsObjectEvents.setLotCode(sglItem.getLotNumber());
        wmsObjectEvents.setEventUomCode(sglItem.getWeightUom());
        wmsObjectEvents.setLastUpdateBy(inboundHead.getCreatedBy());
        wmsObjectEventsMapper.insert(wmsObjectEvents);
        return true;
    }

    @Override
    public WmsLocatorExt getAdviceLocator() {
        return wmsPdaLocatorMapperExt.getAdviceLocator();
    }
}
