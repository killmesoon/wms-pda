package com.siirisoft.aim.wms.entity.locator.ext.pda;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.siirisoft.aim.wms.entity.locator.WmsLocator;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @User DKY
 * @Date 2020/6/22
 * @Description 货位PDA BO
 */
@Data
public class WmsPdaLocatorExt extends WmsLocator {
    @ApiModelProperty(value = "钢板号")
    @JsonProperty(value = "dSequenceNum")
    private String dSequenceNum;

    @ApiModelProperty(value = "炉号")
    private String heatNumber;

    @ApiModelProperty(value = "船号")
    private String shipNumber;

    @ApiModelProperty(value = "分段号")
    private String sectionNum;

    @ApiModelProperty(value = "层号")
    private Integer layerNumber;

    @ApiModelProperty(value = "尺寸")
    private String size;

    @ApiModelProperty(value = "仓库编码")
    private String warehouseCode;

    @ApiModelProperty(value = "仓库名称")
    private String warehouseName;

    @ApiModelProperty(value = "区域编码")
    private String areaCode;

    @ApiModelProperty(value = "区域名称")
    private String areaName;
}
