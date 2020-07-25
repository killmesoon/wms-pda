package com.siirisoft.aim.wms.service.impl.quantity;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.siirisoft.aim.wms.entity.quantity.WmsItemOnhandQuantity;
import com.siirisoft.aim.wms.service.quantity.ABWmsItemOnQuantityService;
import com.siirisoft.aim.wms.service.quantity.IWmsItemOnhandQuantityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @User DKY
 * @Date 2020/7/24
 * @Description ABWmsItemOnQuantityService实现类
 */
@Service
public class ABWmsItemOnQuantityServiceImpl implements ABWmsItemOnQuantityService {

    @Autowired
    private IWmsItemOnhandQuantityService iWmsItemOnhandQuantityService;

    @Override
    @Transactional
    public synchronized boolean updateOnHandQuantity(WmsItemOnhandQuantity wmsItemOnhandQuantity) {
        //先判断是否存在
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("plant_id", wmsItemOnhandQuantity.getPlantId());
        wrapper.eq("warehouse_id", wmsItemOnhandQuantity.getWarehouseId());
        wrapper.eq("locator_id", wmsItemOnhandQuantity.getLocatorId());
        wrapper.eq("item_id", wmsItemOnhandQuantity.getItemId());
        wrapper.eq("lot_number", wmsItemOnhandQuantity.getLotNumber());
        WmsItemOnhandQuantity one = iWmsItemOnhandQuantityService.getOne(wrapper);
        if (one == null) {
            iWmsItemOnhandQuantityService.save(wmsItemOnhandQuantity);
            return true;
        } else {
            one.setLoctOnhand(one.getLoctOnhand() + 1);
            return  iWmsItemOnhandQuantityService.update(one, wrapper);
        }
        //先判断merge_flag
    }
}
