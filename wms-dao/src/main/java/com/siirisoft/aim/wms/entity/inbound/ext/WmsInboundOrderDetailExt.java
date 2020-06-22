package com.siirisoft.aim.wms.entity.inbound.ext;

import com.siirisoft.aim.wms.entity.inbound.WmsInboundOrderDetail;
import lombok.Data;

/**
 * @User DKY
 * @Date 2020/5/26
 * @Description WmsInboundOrderDetail扩展
 */
@Data
public class WmsInboundOrderDetailExt extends WmsInboundOrderDetail {
    private String advWarehouseName;
    private String excuWarehouseName;
    private String advLocatorName;
    private String excuLocatorName;
    private String itemCode;
    private String itemName;
    private String itemDesc;
}
