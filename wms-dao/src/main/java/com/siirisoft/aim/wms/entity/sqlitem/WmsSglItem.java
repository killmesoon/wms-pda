package com.siirisoft.aim.wms.entity.sqlitem;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 条码无聊表详情; InnoDB free: 11264 kB
 * </p>
 *
 * @author DKY
 * @since 2020-06-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="WmsSglItem对象", description="条码无聊表详情; InnoDB free: 11264 kB")
public class WmsSglItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "创建用户ID")
    private Integer createdBy;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
    private Date creationDate;

    @ApiModelProperty(value = "最后更新用户ID")
    private Integer lastUpdateBy;

    @ApiModelProperty(value = "最后更新时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
    private Date lastUpdateDate;

    @ApiModelProperty(value = "工厂ID")
    private Integer plantId;

    @ApiModelProperty(value = "工厂编码")
    private String plantCode;

    @ApiModelProperty(value = "工厂名称")
    private String plantName;

    @ApiModelProperty(value = "条码号")
    private String barcode;

    @ApiModelProperty(value = "钢板号")
    @JsonProperty(value = "dSequenceNum")
    private String dSequenceNum;

    @ApiModelProperty(value = "物料ID")
    private Integer itemId;

    @ApiModelProperty(value = "仓库ID")
    private Integer warehouseId;

    @ApiModelProperty(value = "货位ID")
    private Integer locatorId;

    @ApiModelProperty(value = "层号")
    private Integer layerNumber;

    @ApiModelProperty(value = "规格")
    private String size;

    @ApiModelProperty(value = "质量状态")
    private String qcStatus;

    @ApiModelProperty(value = "主单位")
    private String primaryUom;

    @ApiModelProperty(value = "数量")
    private Integer quantity;

    @ApiModelProperty(value = "批次号")
    private String lotNumber;

    @ApiModelProperty(value = "尺寸单位")
    private String sizeUom;

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
    private String heatNumber;

    @ApiModelProperty(value = "供应商批次")
    private Integer supplierLotNumber;

    @ApiModelProperty(value = "是否有效")
    private Boolean enableFlag;

    @ApiModelProperty(value = "空载标识")
    private Boolean freeFlag;

    @ApiModelProperty(value = "赋值时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
    private Date assignedTime;

    @ApiModelProperty(value = "单据锁定标识")
    private Boolean docHoldFlag;

    @ApiModelProperty(value = "备注")
    private String note;

    @ApiModelProperty(value = "材质")
    private String itemTexture;

    @ApiModelProperty(value = "质保书号")
    private String qcReport;

    @ApiModelProperty(value = "船级社")
    private String css;

    @ApiModelProperty(value = "分段号")
    private String sectionNum;

    @ApiModelProperty(value = "船号")
    private String shipNumber;


}
