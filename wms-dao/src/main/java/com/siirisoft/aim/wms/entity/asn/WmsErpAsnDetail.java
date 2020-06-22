package com.siirisoft.aim.wms.entity.asn;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 送货单明细表; InnoDB free: 11264 kB
 * </p>
 *
 * @author DKY
 * @since 2020-06-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="WmsErpAsnDetail对象", description="送货单明细表; InnoDB free: 11264 kB")
public class WmsErpAsnDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "明细ID")
    @TableId(value = "detail_id", type = IdType.AUTO)
    private Integer detailId;

    @ApiModelProperty(value = "头ID")
    private Integer headId;

    @ApiModelProperty(value = "行ID")
    private Integer lineId;

    @ApiModelProperty(value = "接收标识")
    private Boolean receiveFlag;

    @ApiModelProperty(value = "质检状态")
    private String qcStatus;

    @ApiModelProperty(value = "物料ID")
    private Integer itemId;

    @ApiModelProperty(value = "条码号")
    private String dBarcode;

    @ApiModelProperty(value = "钢板号")
    @JsonProperty(value = "dSequenceNum")
    private String dSequenceNum;

    @ApiModelProperty(value = "材质")
    private String itemTexture;

    @ApiModelProperty(value = "数量单位")
    private String primaryUom;

    @ApiModelProperty(value = "数量")
    private Integer quantity;

    @ApiModelProperty(value = "尺寸单位")
    private String sizeUom;

    @ApiModelProperty(value = "尺寸")
    private String size;

    @ApiModelProperty(value = "长度")
    private String length;

    @ApiModelProperty(value = "宽度")
    private String width;

    @ApiModelProperty(value = "厚度")
    private String thickness;

    @ApiModelProperty(value = "重量单位")
    private String weightUom;

    @ApiModelProperty(value = "重量")
    private String weight;

    @ApiModelProperty(value = "供应商ID")
    private Integer supplierId;

    @ApiModelProperty(value = "炉号")
    private Integer heatNumber;

    @ApiModelProperty(value = "供应商批次")
    private Integer supplierLotNumber;

    @ApiModelProperty(value = "质保书号")
    private String qcReport;

    @ApiModelProperty(value = "船社级")
    private String ccs;

    @ApiModelProperty(value = "船号")
    private Integer shipNumber;

    @ApiModelProperty(value = "分段号")
    private Integer sectionNum;

    @ApiModelProperty(value = "备注")
    private String note;


}
