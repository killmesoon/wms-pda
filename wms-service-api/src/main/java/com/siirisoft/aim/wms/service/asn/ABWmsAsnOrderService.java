package com.siirisoft.aim.wms.service.asn;

import com.siirisoft.aim.wms.entity.asn.WmsErpAsnDetail;
import com.siirisoft.aim.wms.entity.asn.WmsErpAsnHead;
import com.siirisoft.aim.wms.entity.asn.WmsErpAsnLine;
import com.siirisoft.aim.wms.entity.asn.ext.WmsErpAsnCondition;

import java.util.List;

/**
 * @User DKY
 * @Date 2020/6/13
 * @Description ASN模块service-api
 */
public interface ABWmsAsnOrderService {

    boolean deleteAsnOrder(int headId);

    boolean deleteAsnOrderList(List<Integer> headIdList);

    boolean saveWmsErpAsnOrder(WmsErpAsnCondition wmsErpAsnCondition);

    boolean deleteLineOrderAndDetail(int lineId);

    boolean deleteLineOrderAndDetailList(List<Integer> lineIdList);

    boolean saveWmsErpOrderLineList(int lineId, List<WmsErpAsnDetail> list);

    boolean asnOrderCheck(List<WmsErpAsnHead> list);

    Integer getMaxLineNumber(WmsErpAsnLine wmsErpAsnLine);
}
