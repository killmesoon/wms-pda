package com.siirisoft.aim.wms.entity.outbound.ext.pda;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.siirisoft.aim.wms.entity.outbound.WmsOutboundOrderDetail;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @User DKY
 * @Date 2020/6/22
 * @Description 出库单详情pda BO
 */
@Data
public class WmsPdaOutboundOrderDetail extends WmsOutboundOrderDetail {
    @ApiModelProperty(value = "物料ID")
    private Integer itemId;

    @ApiModelProperty(value = "钢板号")
    @JsonProperty(value = "dSequenceNum")
    private String dSequenceNum;

    @ApiModelProperty(value = "炉号")
    private String heatNumber;

    @ApiModelProperty(value = "船号")
    private String shipNumber;

    @ApiModelProperty(value = "分段号")
    private String sectionNum;

    @ApiModelProperty(value = "尺寸")
    private String size;

    @ApiModelProperty(value = "层号")
    private Integer layerNumber;

    @ApiModelProperty(value = "建议货位编码")
    private String advLocatorCode;

    @ApiModelProperty(value = "建议货位名称")
    private String advLocatorName;

    @ApiModelProperty(value = "执行货位编码")
    private String excuLocatorCode;

    @ApiModelProperty(value = "执行货位名称")
    private String excuLocatorName;


}
