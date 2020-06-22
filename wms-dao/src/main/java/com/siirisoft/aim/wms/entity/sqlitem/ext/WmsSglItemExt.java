package com.siirisoft.aim.wms.entity.sqlitem.ext;

import com.siirisoft.aim.wms.entity.sqlitem.WmsSglItem;
import lombok.Data;

/**
 * @User DKY
 * @Date 2020/6/15
 * @Description WmsSglItem扩展类
 */
@Data
public class WmsSglItemExt extends WmsSglItem {
    private String itemCode;
    private String description;
    private String warehouseName;
    private String warehouseCode;
    private String locatorName;
    private String locatorCode;
    private String uomName;
}
