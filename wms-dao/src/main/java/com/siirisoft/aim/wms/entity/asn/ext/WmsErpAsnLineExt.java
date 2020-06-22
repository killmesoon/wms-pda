package com.siirisoft.aim.wms.entity.asn.ext;

import com.siirisoft.aim.wms.entity.asn.WmsErpAsnLine;
import lombok.Data;

/**
 * @User DKY
 * @Date 2020/6/8
 * @Description WmsErpAsnLine扩展
 */
@Data
public class WmsErpAsnLineExt extends WmsErpAsnLine {
    private String uomName;
    private String itemCode;
    private String description;
    private String warehouseCode;
    private String warehouseName;
    private String sourcePoDic;
}
