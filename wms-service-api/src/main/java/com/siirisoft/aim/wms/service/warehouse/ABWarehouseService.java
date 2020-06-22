package com.siirisoft.aim.wms.service.warehouse;

import com.siirisoft.aim.wms.entity.warehouse.WmsWarehouse;

import java.util.List;

/**
 * @User DKY
 * @Date 2020/6/4
 * @Description 仓库service扩展
 */
public interface ABWarehouseService {
    boolean ifWarehouseDelete(int wmsWarehouseId);

    boolean ifWarehouseListDelete(List<Integer> wmsWarehouseIdList);
}
