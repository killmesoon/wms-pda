package com.siirisoft.aim.wms.entity.asn.ext;

import com.siirisoft.aim.wms.entity.asn.WmsErpAsnHead;
import com.siirisoft.aim.wms.entity.asn.WmsErpAsnLine;
import lombok.Data;

import java.util.List;

/**
 * @User DKY
 * @Date 2020/6/13
 * @Description ASN条件包装类
 */
@Data
public class WmsErpAsnCondition {
    private WmsErpAsnHead wmsErpAsnHead;
    private List<WmsErpAsnLine> wmsErpAsnLines;
}
