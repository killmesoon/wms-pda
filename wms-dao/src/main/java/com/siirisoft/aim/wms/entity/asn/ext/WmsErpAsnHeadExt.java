package com.siirisoft.aim.wms.entity.asn.ext;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.siirisoft.aim.wms.entity.asn.WmsErpAsnHead;
import lombok.Data;

import java.util.Date;

/**
 * @User DKY
 * @Date 2020/6/8
 * @Description WmsErpAsnHead扩展
 */
@Data
public class WmsErpAsnHeadExt extends WmsErpAsnHead {
    private String asnStatusDic;
    private String asnTypeDic;
    private String sourceDocTypeDic;
    private String supplierName;
    private String supplierCode;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
    private Date planStartDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
    private Date planEndDate;
}
