package com.siirisoft.aim.wms.service.outbound;

import com.siirisoft.aim.wms.entity.outbound.WmsOutboundOrderDetail;
import com.siirisoft.aim.wms.entity.outbound.WmsOutboundOrderLine;

import java.util.List;

/**
 * @User DKY
 * @Date 2020/6/16
 * @Description 出库单service-api
 */
public interface ABWmsOutboundOrderService {
    boolean saveWmsInboundOrderDetail(int lineId, List<WmsOutboundOrderDetail> list);

    boolean deleteLineAndDetail(int lineId);

    boolean deleteLineAndDetail(List<Integer> lineIdList);

    boolean deleteOutboundHeadOrder(int headId);

    boolean deleteOutboundHeadOrder(List<Integer> headIdList);

    int getMaxLineNumber(WmsOutboundOrderLine wmsOutboundOrderLine);
}
