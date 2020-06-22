package com.siirisoft.aim.wms.entity.warehouse.ext;

import com.siirisoft.aim.wms.entity.warehouse.WmsWarehouse;
import lombok.Data;

/**
 * @User DKY
 * @Date 2020/5/26
 * @Description
 */
@Data
public class WmsWarehouseExt extends WmsWarehouse {
    private String warehouseTypeDic;
    private String plantCode;
    private String plantName;
}
