package com.siirisoft.aim.wms.service.inbound;

import com.siirisoft.aim.wms.entity.inbound.WmsInboundOrderDetail;
import com.siirisoft.aim.wms.entity.inbound.WmsInboundOrderHead;

import java.util.List;

/**
 * @User DKY
 * @Date 2020/6/16
 * @Description 入库单service-api
 */
public interface ABWmsInboundOrderService {
    boolean saveWmsInboundOrderDetail(int lineId, List<WmsInboundOrderDetail> list);

    boolean deleteLineAndDetailByLineId(int lineId);

    boolean deleteHeadAndAll(int headId);

    boolean deleteHeadAndAllList(List<Integer> headIdlist);
}
