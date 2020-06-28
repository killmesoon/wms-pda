package com.siirisoft.aim.wms.entity.area.ext;

import com.siirisoft.aim.wms.entity.area.WmsWarehouseArea;
import lombok.Data;

/**
 * @User DKY
 * @Date 2020/5/27
 * @Description WmsWarehouseArea扩展
 */
@Data
public class WmsWarehouseAreaExt extends WmsWarehouseArea {
    private String warehouseCode;
    private String warehouseShortName;
    private String areaTypeDic;
}
