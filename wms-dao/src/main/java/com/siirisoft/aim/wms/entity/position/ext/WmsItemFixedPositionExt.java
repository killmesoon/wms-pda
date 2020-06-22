package com.siirisoft.aim.wms.entity.position.ext;

import com.siirisoft.aim.wms.entity.position.WmsItemFixedPosition;
import lombok.Data;

/**
 * @User DKY
 * @Date 2020/5/22
 * @Description WmsItemFixedPosition扩展类
 */
@Data
public class WmsItemFixedPositionExt extends WmsItemFixedPosition {
    private String plantCode;
    private String plantName;
    private String warehouseCode;
    private String warehouseName;
    private String warehouseAreaCode;
    private String warehouseAreaName;
}
