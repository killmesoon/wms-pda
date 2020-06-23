package com.siirisoft.aim.wms.entity.outbound.ext.pda;

import com.siirisoft.aim.wms.entity.locator.ext.pda.WmsPdaLocatorExt;
import lombok.Data;

/**
 * @User DKY
 * @Date 2020/6/23
 * @Description 出库单条件查询类
 */
@Data
public class WmsOutboundCondition {
    private WmsPdaOutboundOrderDetail wmsPdaOutboundOrderDetail;
    private WmsPdaLocatorExt wmsPdaLocatorExt;
}
