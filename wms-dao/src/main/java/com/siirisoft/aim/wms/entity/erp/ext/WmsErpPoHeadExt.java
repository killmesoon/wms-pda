package com.siirisoft.aim.wms.entity.erp.ext;

import com.siirisoft.aim.wms.entity.erp.WmsErpPoHead;
import lombok.Data;

/**
 * @User DKY
 * @Date 2020/5/27
 * @Description WmsErpPoHead扩展
 */
@Data
public class WmsErpPoHeadExt extends WmsErpPoHead {
    private String poTypeDic;
    private String poStatusDic;
    private String supplierCode;
    private String supplierName;
    private String paymentTermsDic;
    private String poSourceTypeDic;
}
