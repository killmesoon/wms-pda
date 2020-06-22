package com.siirisoft.aim.wms.entity.outbound.ext;

import com.siirisoft.aim.wms.entity.outbound.WmsOutboundOrderDetail;
import lombok.Data;

/**
 * @User DKY
 * @Date 2020/5/26
 * @Description WmsOutboundOrderDetail扩展
 */
@Data
public class WmsOutboundOrderDetailExt extends WmsOutboundOrderDetail {
    private String advWarehouseName;
    private String excuWarehouseName;
    private String advLocatorName;
    private String excuLocatorName;
    private String itemCode;
    private String itemName;
    private String itemDesc;
}
