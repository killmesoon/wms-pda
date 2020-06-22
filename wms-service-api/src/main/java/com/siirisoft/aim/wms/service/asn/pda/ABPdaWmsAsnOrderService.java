package com.siirisoft.aim.wms.service.asn.pda;

import com.siirisoft.aim.wms.entity.asn.WmsErpAsnDetail;
import com.siirisoft.aim.wms.entity.asn.ext.WmsErpAsnDetailExt;

import java.util.List;

/**
 * @User DKY
 * @Date 2020/6/16
 * @Description pda接口api
 */
public interface ABPdaWmsAsnOrderService {
    boolean commitAsnOrderList(List<WmsErpAsnDetailExt> list);
}
