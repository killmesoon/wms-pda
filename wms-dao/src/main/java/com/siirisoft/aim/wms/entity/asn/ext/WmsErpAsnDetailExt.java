package com.siirisoft.aim.wms.entity.asn.ext;

import com.siirisoft.aim.wms.entity.asn.WmsErpAsnDetail;
import lombok.Data;

/**
 * @User DKY
 * @Date 2020/6/12
 * @Description WmsErpAsnDetail扩展
 */
@Data
public class WmsErpAsnDetailExt extends WmsErpAsnDetail {
    private Integer layerNumber; //层号
    private String itemCode;
    private String description;
    private Integer warehouseId;
    private Integer locatorId;
    private String plantCode;
    private String plantName;
}
