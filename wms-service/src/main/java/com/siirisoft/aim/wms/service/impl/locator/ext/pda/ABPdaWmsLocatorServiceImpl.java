package com.siirisoft.aim.wms.service.impl.locator.ext.pda;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.siirisoft.aim.wms.entity.events.WmsObjectEvents;
import com.siirisoft.aim.wms.entity.locator.ext.pda.WmsPdaLocatorExt;
import com.siirisoft.aim.wms.entity.quantity.WmsItemOnhandQuantity;
import com.siirisoft.aim.wms.entity.sqlitem.WmsSglItem;
import com.siirisoft.aim.wms.mapper.events.WmsObjectEventsMapper;
import com.siirisoft.aim.wms.mapper.locator.ext.pda.WmsPdaLocatorMapperExt;
import com.siirisoft.aim.wms.mapper.quantity.WmsItemOnhandQuantityMapper;
import com.siirisoft.aim.wms.mapper.sqlitem.WmsSglItemMapper;
import com.siirisoft.aim.wms.mapper.sqlitem.ext.WmsSglItemMapperExt;
import com.siirisoft.aim.wms.service.locator.pda.ABPdaWmsLocatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * @User DKY
 * @Date 2020/6/22
 * @Description ABPdaWmsLocatorService实现类
 */
@Service
public class ABPdaWmsLocatorServiceImpl implements ABPdaWmsLocatorService {

    @Autowired
    private WmsPdaLocatorMapperExt wmsPdaLocatorMapperExt;


    @Autowired
    private WmsSglItemMapper wmsSglItemMapper;

    @Autowired
    private WmsSglItemMapperExt wmsSglItemMapperExt;

    @Autowired
    private WmsItemOnhandQuantityMapper wmsItemOnhandQuantityMapper;

    @Autowired
    private WmsObjectEventsMapper wmsObjectEventsMapper;

    @Override
    public IPage queryLocatorDetail(Page page, Wrapper wrapper) {
        Integer count = wmsPdaLocatorMapperExt.querySglItemByLocatorId(wrapper);
        if (count == 0) {
            return new Page();
        }
        return wmsPdaLocatorMapperExt.queryLocatorDetail(page, wrapper);
    }


    @Override
    @Transactional
    public boolean transformLocator(int locatorId, WmsPdaLocatorExt wmsPdaLocatorExt) {
        //货位移动操作
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("d_sequence_num", wmsPdaLocatorExt.getDSequenceNum());
        WmsSglItem sglItem = wmsSglItemMapper.selectOne(wrapper);//获取钢板条目

        //获取目标位置最大层号
        QueryWrapper layerWrapper = new QueryWrapper();
        layerWrapper.eq("locator_id", locatorId);
        Integer maxLayerNumber = wmsSglItemMapperExt.findMaxLayerNumber(layerWrapper);

        //更新货位号 仓库号 与层号
        if (maxLayerNumber == null) {

        }
        sglItem.setLayerNumber(maxLayerNumber + 1);
        sglItem.setLocatorId(locatorId);
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
        quantityWrapper.eq("locator_id",wmsPdaLocatorExt.getLocatorId());
        List<WmsItemOnhandQuantity> list = wmsItemOnhandQuantityMapper.selectList(quantityWrapper);
        if (list.size() > 0) {
            WmsItemOnhandQuantity quantity = list.get(0);
            wmsItemOnhandQuantityMapper.deleteById(quantity.getId());
        }
        //插入事务
        WmsObjectEvents wmsObjectEvents = new WmsObjectEvents();
        wmsObjectEvents.setEventTime(new Date());
        wmsObjectEvents.setBarcode(sglItem.getDSequenceNum());
        wmsObjectEvents.setCreationDate(new Date());
        wmsObjectEvents.setEventTypeId(5);
        wmsObjectEvents.setEventTypeCode("GD_LOCATOR_TRANSFER");
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
    public int queryLocatorByCode(String advBarcode) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("d_sequence_num", advBarcode);
        WmsSglItem sglItem = wmsSglItemMapper.selectOne(wrapper);
        return sglItem.getLocatorId();
    }
}
