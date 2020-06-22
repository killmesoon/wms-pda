package com.siirisoft.aim.wms.entity.outbound.ext;

import com.siirisoft.aim.wms.entity.outbound.WmsOutboundOrderLine;
import lombok.Data;

/**
 * @User DKY
 * @Date 2020/5/20
 * @Description WmsOutboundOrderLine扩展类
 */
@Data
public class WmsOutboundOrderLineExt extends WmsOutboundOrderLine {
    private String itemCode; //物料编码
    private String description; //物料描述
    private String realSourceDocType; //实际物料类型
    private String warehouseName; //仓库名称
    private String warehouseCode; //仓库编码
}
