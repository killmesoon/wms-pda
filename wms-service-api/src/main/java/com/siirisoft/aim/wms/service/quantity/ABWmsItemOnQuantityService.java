package com.siirisoft.aim.wms.service.quantity;

import com.siirisoft.aim.wms.entity.quantity.WmsItemOnhandQuantity;

/**
 * @User DKY
 * @Date 2020/7/24
 * @Description 现有量更新接口
 */
public interface ABWmsItemOnQuantityService {
    boolean updateOnHandQuantity(WmsItemOnhandQuantity wmsItemOnhandQuantity);
}
