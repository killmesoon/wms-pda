package com.siirisoft.aim.wms.service.impl.inbound.ext.pda;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.siirisoft.aim.wms.entity.inbound.ext.pda.WmsPdaInboundOrderDetail;
import com.siirisoft.aim.wms.entity.quantity.WmsItemOnhandQuantity;
import com.siirisoft.aim.wms.entity.sqlitem.WmsSglItem;
import com.siirisoft.aim.wms.mapper.inbound.WmsInboundOrderDetailMapper;
import com.siirisoft.aim.wms.mapper.inbound.WmsInboundOrderLineMapper;
import com.siirisoft.aim.wms.mapper.inbound.ext.pda.WmsPdaInboundOrderDetailMapper;
import com.siirisoft.aim.wms.mapper.quantity.WmsItemOnhandQuantityMapper;
import com.siirisoft.aim.wms.mapper.sqlitem.WmsSglItemMapper;
import com.siirisoft.aim.wms.mapper.sqlitem.ext.WmsSglItemMapperExt;
import com.siirisoft.aim.wms.service.inbound.pda.ABPdaWmsInboundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    @Override
    public IPage queryInboundOrderDetail(Page page, Wrapper wrapper) {
        return wmsPdaInboundOrderDetailMapper.queryInboundOrderDetail(page, wrapper);
    }

    @Override
    @Transactional
    public synchronized boolean commitInboundOrder(WmsPdaInboundOrderDetail wmsPdaInboundOrderDetail) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("locator_id", wmsPdaInboundOrderDetail.getExcuLocatorId());
        //获取要更新的这个板子要去货位的最大层号
        Integer maxLayerNumber = wmsSglItemMapperExt.findMaxLayerNumber(wrapper);

        //更新sgl表中的货位位置，以及层号 由于是一张板子，不涉及此处不涉及数量变化
        WmsSglItem wmsSglItem = new WmsSglItem();
        wmsSglItem.setLocatorId(wmsPdaInboundOrderDetail.getExcuLocatorId());
        wmsSglItem.setLayerNumber(maxLayerNumber);
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("dSequenceNum", wmsPdaInboundOrderDetail.getDSequenceNum());
        wmsSglItemMapper.update(wmsSglItem, updateWrapper);

        //现有量更新，一笔增一笔减, 由于单张钢板入库，每次增减数量为1 原有货位 -1 目标货位  + 1
        //更新过后的钢板
        WmsSglItem sglItem = wmsSglItemMapper.selectOne(updateWrapper);
        //更新现有量
        QueryWrapper quantityWrapper = new QueryWrapper();
        quantityWrapper.eq("item_id", sglItem.getItemId());
        quantityWrapper.eq("d_sequence_num", sglItem.getDSequenceNum());
        quantityWrapper.eq("lot_number", sglItem.getLotNumber());
        quantityWrapper.eq("locator_id", wmsPdaInboundOrderDetail.getAdvLocatorId());
        wmsItemOnhandQuantityMapper.delete(quantityWrapper);

        WmsItemOnhandQuantity wmsItemOnhandQuantity = new WmsItemOnhandQuantity();
        wmsItemOnhandQuantity.setLocatorId(sglItem.getLocatorId());
        wmsItemOnhandQuantity.setLoctOnhand(1);
        wmsItemOnhandQuantity.setWarehouseId(sglItem.getWarehouseId());
        wmsItemOnhandQuantity.setLotNumber(sglItem.getLotNumber() + "");
        wmsItemOnhandQuantity.setItemId(sglItem.getItemId());
        wmsItemOnhandQuantityMapper.insert(wmsItemOnhandQuantity);

        //更新明细信息信息
        wmsPdaInboundOrderDetail.setExcuLotCode(wmsPdaInboundOrderDetail.getShipNumber() + wmsPdaInboundOrderDetail.getSectionNum() + ""); //执行批次，船号+分段号
        wmsPdaInboundOrderDetail.setExcuQuantity(1);
        wmsInboundOrderDetailMapper.updateById(wmsPdaInboundOrderDetail);

        //更新行信息
        QueryWrapper lineWrapper = new QueryWrapper();
        lineWrapper.eq("line_id", wmsPdaInboundOrderDetail.getLineId());
        wmsInboundOrderLineMapper.updateInboundLineInfo(lineWrapper);



        return true;
    }
}