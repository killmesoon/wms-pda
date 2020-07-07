package com.siirisoft.aim.wms.entity.barcode.ext;

import com.siirisoft.aim.wms.entity.barcode.WmsBarcode;
import lombok.Data;

/**
 * @User DKY
 * @Date 2020/5/22
 * @Description WmsBarcode扩展
 */
@Data
public class WmsBarcodeExt extends WmsBarcode {
    private String barcodeTypeDic;
    private String barcodeStatusDic;
    private String supplierCode;
    private String supplierName;

}
