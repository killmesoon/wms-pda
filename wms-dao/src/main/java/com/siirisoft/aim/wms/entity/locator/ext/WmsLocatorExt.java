package com.siirisoft.aim.wms.entity.locator.ext;

import com.siirisoft.aim.wms.entity.locator.WmsLocator;
import lombok.Data;

/**
 * @User DKY
 * @Date 2020/5/26
 * @Description WmsLocator扩展
 */
@Data
public class WmsLocatorExt extends WmsLocator {
    private String locatorCodeAuto; //自动生成编码
    private String locatorTypeDic;
    private String areaName;
    private String areaCode;
    private String areaTypeDic;
    private String warehouseShortName;
    private String warehouseCode;
    private String plantCode;
    private String plantName;
    private Integer layerNumber;
}
