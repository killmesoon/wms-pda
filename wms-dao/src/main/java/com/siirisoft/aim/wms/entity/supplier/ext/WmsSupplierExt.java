package com.siirisoft.aim.wms.entity.supplier.ext;

import com.siirisoft.aim.wms.entity.supplier.WmsSupplier;
import lombok.Data;

/**
 * @User DKY
 * @Date 2020/5/20
 * @Description WmsSupplier扩展
 */
@Data
public class WmsSupplierExt extends WmsSupplier {
    private String supplierStatusDic;
    private String supplierTypeDic;
}
