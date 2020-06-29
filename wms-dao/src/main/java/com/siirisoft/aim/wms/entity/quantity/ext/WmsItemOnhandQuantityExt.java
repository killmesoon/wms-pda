package com.siirisoft.aim.wms.entity.quantity.ext;

import com.siirisoft.aim.wms.entity.quantity.WmsItemOnhandQuantity;
import lombok.Data;

/**
 * @User DKY
 * @Date 2020/6/29
 * @Description WmsItemOnhandQuantity扩展类
 */
@Data
public class WmsItemOnhandQuantityExt extends WmsItemOnhandQuantity {
    private Integer total;
    private String warehouseName;
    private String warehouseCode;
    private String itemCode;
    private String description;
    private String locatorCode;
    private String locatorName;
    private String keyWord;
}
