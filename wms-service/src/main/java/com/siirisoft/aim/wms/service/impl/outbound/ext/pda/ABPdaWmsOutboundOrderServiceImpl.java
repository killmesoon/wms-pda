package com.siirisoft.aim.wms.service.impl.outbound.ext.pda;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.siirisoft.aim.wms.entity.events.WmsObjectEvents;
import com.siirisoft.aim.wms.entity.locator.ext.WmsLocatorExt;
import com.siirisoft.aim.wms.entity.locator.ext.pda.WmsPdaLocatorExt;
import com.siirisoft.aim.wms.entity.outbound.WmsOutboundOrderDetail;
import com.siirisoft.aim.wms.entity.outbound.WmsOutboundOrderLine;
import com.siirisoft.aim.wms.entity.outbound.ext.pda.WmsOutboundCondition;
import com.siirisoft.aim.wms.entity.outbound.ext.pda.WmsPdaOutboundOrderDetail;
import com.siirisoft.aim.wms.entity.quantity.WmsItemOnhandQuantity;
import com.siirisoft.aim.wms.entity.sqlitem.WmsSglItem;
import com.siirisoft.aim.wms.mapper.events.WmsObjectEventsMapper;
import com.siirisoft.aim.wms.mapper.outbound.WmsOutboundOrderDetailMapper;
import com.siirisoft.aim.wms.mapper.outbound.WmsOutboundOrderLineMapper;
import com.siirisoft.aim.wms.mapper.outbound.ext.pda.WmsPdaOutboundOrderDetailMapper;
import com.siirisoft.aim.wms.mapper.quantity.WmsItemOnhandQuantityMapper;
import com.siirisoft.aim.wms.mapper.sqlitem.WmsSglItemMapper;
import com.siirisoft.aim.wms.mapper.sqlitem.ext.WmsSglItemMapperExt;
import com.siirisoft.aim.wms.service.outbound.IWmsOutboundOrderDetailService;
import com.siirisoft.aim.wms.service.outbound.pda.ABPdaWmsOutboundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @User DKY
 * @Date 2020/6/22
 * @Description ABPdaWmsOutboundOrderService实现类
 */
@Service
public class ABPdaWmsOutboundOrderServiceImpl implements ABPdaWmsOutboundOrderService {

    @Autowired
    private WmsPdaOutboundOrderDetailMapper wmsPdaOutboundOrderDetailMapper;

    @Autowired
    private WmsSglItemMapper wmsSglItemMapper;

    @Autowired
    private WmsSglItemMapperExt wmsSglItemMapperExt;

    @Autowired
    private WmsItemOnhandQuantityMapper wmsItemOnhandQuantityMapper;

    @Autowired
    private WmsObjectEventsMapper wmsObjectEventsMapper;

    @Autowired
    private WmsOutboundOrderDetailMapper wmsOutboundOrderDetailMapper;

    @Autowired
    private WmsOutboundOrderLineMapper wmsOutboundOrderLineMapper;

    @Autowired
    private IWmsOutboundOrderDetailService wmsOutboundOrderDetailService;

    @Override
    public IPage queryOutboundOrderDetail(Page page, Wrapper wrapper) {
        return wmsPdaOutboundOrderDetailMapper.queryOutboundOrderDetail(page, wrapper);
    }

    @Override
    public boolean commitPreparation(WmsOutboundCondition outboundCondition) {
        WmsPdaLocatorExt wmsPdaLocatorExt = outboundCondition.getWmsPdaLocatorExt();
        WmsPdaOutboundOrderDetail wmsPdaOutboundOrderDetail = outboundCondition.getWmsPdaOutboundOrderDetail();
        Integer targetLocatorId = outboundCondition.getTargetLocatorId();
        Integer targetAreaId = outboundCondition.getTargetAreaId();
        Integer targetWarehouseId = outboundCondition.getTargetWarehouseId();

        //货位移动操作
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("d_sequence_num", wmsPdaLocatorExt.getDSequenceNum());
        WmsSglItem sglItem = wmsSglItemMapper.selectOne(wrapper);//获取钢板条目

        //获取目标位置最大层号
        QueryWrapper layerWrapper = new QueryWrapper();
        layerWrapper.eq("locator_id", targetLocatorId);
        layerWrapper.eq("warehouse_id", targetWarehouseId);
        Integer maxLayerNumber = wmsSglItemMapperExt.findMaxLayerNumber(layerWrapper);

        //更新货位号 仓库号 与层号
        sglItem.setLayerNumber(maxLayerNumber + 1);
        sglItem.setLocatorId(targetLocatorId);
        sglItem.setWarehouseId(targetWarehouseId);
        wmsSglItemMapper.update(sglItem, wrapper);

        //更新现有量
        WmsItemOnhandQuantity wmsItemOnhandQuantity = new WmsItemOnhandQuantity();
        wmsItemOnhandQuantity.setItemId(sglItem.getItemId());
        wmsItemOnhandQuantity.setLocatorId(sglItem.getLocatorId());
        wmsItemOnhandQuantity.setLotNumber(sglItem.getShipNumber() + "-" + sglItem.getSectionNum());
        wmsItemOnhandQuantity.setUomCode(sglItem.getPrimaryUom());
        wmsItemOnhandQuantity.setWarehouseId(sglItem.getWarehouseId());
        wmsItemOnhandQuantity.setLoctOnhand(1);
        wmsItemOnhandQuantityMapper.insert(wmsItemOnhandQuantity);
        //删除一条之前的现有量
        QueryWrapper quantityWrapper = new QueryWrapper();
        quantityWrapper.eq("warehouse_id", sglItem.getWarehouseId());
        quantityWrapper.eq("locator_id", wmsPdaLocatorExt.getLocatorId());
        List<WmsItemOnhandQuantity> list = wmsItemOnhandQuantityMapper.selectList(quantityWrapper);
        if (list.size() > 0) {
            WmsItemOnhandQuantity quantity = list.get(0);
            wmsItemOnhandQuantityMapper.deleteById(quantity.getId());
        }
        WmsObjectEvents wmsObjectEvents = new WmsObjectEvents();
        if (targetLocatorId == wmsPdaOutboundOrderDetail.getAdvLocatorId()) {
            //说明是备料
            //插入明细行
            QueryWrapper lineDetailWrapper = new QueryWrapper();
            lineDetailWrapper.eq("line_id", wmsPdaOutboundOrderDetail.getLineId());
            WmsOutboundOrderDetail detail = new WmsOutboundOrderDetail();
            detail.setPreQuantity(1);
            detail.setPreLotCode(sglItem.getDSequenceNum());
            detail.setPreLocatorCode(wmsPdaLocatorExt.getLocatorCode());
            wmsOutboundOrderDetailMapper.insert(detail);


            //备料
            wmsObjectEvents.setEventTypeId(3);
            wmsObjectEvents.setEventTypeCode("GD_WO_PREPARE");
        } else {
            //移库
            wmsObjectEvents.setEventTypeId(5);
            wmsObjectEvents.setEventTypeCode("GD_LOCATOR_TRANSFER");

        }
        wmsObjectEvents.setEventTime(new Date());
        wmsObjectEvents.setBarcode(sglItem.getDSequenceNum());
        wmsObjectEvents.setCreationDate(new Date());
        wmsObjectEvents.setItemId(sglItem.getItemId());
        wmsObjectEvents.setWarehouseIdFrom(wmsPdaLocatorExt.getWarehouseId());
        wmsObjectEvents.setWarehouseIdTo(sglItem.getWarehouseId());
        wmsObjectEvents.setEventQty(1);
        wmsObjectEvents.setLocatorIdFrom(wmsPdaLocatorExt.getLocatorId());
        wmsObjectEvents.setLocatorIdTo(sglItem.getLocatorId());
        wmsObjectEventsMapper.insert(wmsObjectEvents);

        return true;
    }

    @Override
    @Transactional
    public boolean outboundOrderExc(List<WmsPdaOutboundOrderDetail> detailList) {
        //出库执行
        //新增明细列表
        List<WmsOutboundOrderDetail> dList = new ArrayList<>();
        for (WmsPdaOutboundOrderDetail detail : detailList) {
            //出库单更新,行信息更新
            QueryWrapper lineWrapper = new QueryWrapper();
            lineWrapper.eq("line_id", detail.getLineId());
            WmsOutboundOrderLine wmsOutboundOrderLine = wmsOutboundOrderLineMapper.selectOne(lineWrapper);
            if (wmsOutboundOrderLine.getExecuteQty() != null) {
                wmsOutboundOrderLine.setExecuteQty(wmsOutboundOrderLine.getExecuteQty() + 1);
            } else {
                wmsOutboundOrderLine.setExecuteQty(1);
            }
            detail.setExcuLocatorId(detail.getAdvLocatorId());
            detail.setExcuQuantity(1);
            detail.setExecBarcode(detail.getDSequenceNum());
            detail.setExcuLotCode(detail.getShipNumber() + "-" + detail.getSectionNum());
            detail.setExcuWarehouseId(detail.getAdvWarehouseId());
            dList.add(detail);


            //更新现有量 移除此条
            QueryWrapper quantityWrapper = new QueryWrapper();
            quantityWrapper.eq("item_id", detail.getItemId());
            quantityWrapper.eq("locator_id",detail.getExcuLocatorId());
            quantityWrapper.eq("warehouse_id", detail.getExcuWarehouseId());
            List<WmsItemOnhandQuantity> list = wmsItemOnhandQuantityMapper.selectList(quantityWrapper);
            if (list.size() > 0) {
                wmsItemOnhandQuantityMapper.deleteById(list.get(0).getId());
            }

            //更新条码表
            QueryWrapper itemSglWrapper = new QueryWrapper();
            WmsSglItem wmsSglItem = new WmsSglItem();
            itemSglWrapper.eq("d_sequence_num", detail.getDSequenceNum());
            wmsSglItem.setEnableFlag(false);
            wmsSglItemMapper.update(wmsSglItem, itemSglWrapper);


            //新增事务
            WmsObjectEvents e = new WmsObjectEvents();
            e.setEventTypeId(4);
            e.setEventTime(new Date());
            e.setEventTypeCode("GD_OUTBOUND");
            e.setEventQty(1);
            e.setItemId(detail.getItemId());
            e.setLotCode(detail.getExcuLotCode());
            e.setLocatorIdFrom(detail.getAdvLocatorId());
            e.setLocatorIdTo(detail.getExcuLocatorId());
            e.setWarehouseIdTo(detail.getExcuWarehouseId());
            e.setWarehouseIdFrom(detail.getAdvWarehouseId());
            e.setCreationDate(new Date());


        }

        //更新出库明细信息
        wmsOutboundOrderDetailService.saveOrUpdateBatch(dList);
        return true;
    }
}
