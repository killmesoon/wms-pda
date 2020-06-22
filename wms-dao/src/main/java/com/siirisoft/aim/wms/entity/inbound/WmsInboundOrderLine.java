package com.siirisoft.aim.wms.entity.inbound;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 入库单行表; InnoDB free: 11264 kB
 * </p>
 *
 * @author DKY
 * @since 2020-05-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="WmsInboundOrderLine对象", description="入库单行表; InnoDB free: 11264 kB")
public class WmsInboundOrderLine implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "创建用户ID")
    private Integer createdBy;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date creationDate;

    @ApiModelProperty(value = "最后更新用户ID")
    private Integer lastUpdateBy;

    @ApiModelProperty(value = "最后更新时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date lastUpdateDate;

    @ApiModelProperty(value = "订单头ID")
    private Integer headId;

    @ApiModelProperty(value = "行ID")
    @TableId(value = "line_id", type = IdType.AUTO)
    private Integer lineId;

    @ApiModelProperty(value = "行号")
    private Integer lineNum;

    @ApiModelProperty(value = "物料ID")
    private Integer itemId;

    @ApiModelProperty(value = "仓库ID")
    private Integer warehouseId;

    @ApiModelProperty(value = "计划数量")
    private Integer planQty;

    @ApiModelProperty(value = "单位")
    private String uom;

    @ApiModelProperty(value = "执行数量")
    private Integer executeQty;

    @ApiModelProperty(value = "来源单据号ID")
    private Integer sourceDocId;

    @ApiModelProperty(value = "来源单据类型")
    private String sourceDocType;

    @ApiModelProperty(value = "来源单据号")
    private Integer sourceDocNum;

    @ApiModelProperty(value = "来源单据行号")
    private Integer sourceLineNum;

    @ApiModelProperty(value = "备注")
    private String note;


}
