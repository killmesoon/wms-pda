package com.siirisoft.aim.wms.entity.erp;

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
 * 采购订单行表; InnoDB free: 11264 kB
 * </p>
 *
 * @author DKY
 * @since 2020-05-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="WmsErpPoLine对象", description="采购订单行表; InnoDB free: 11264 kB")
public class WmsErpPoLine implements Serializable {

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

    @ApiModelProperty(value = "物料IT")
    private Integer itemId;

    @ApiModelProperty(value = "仓库ID")
    private Integer warehouseId;

    @ApiModelProperty(value = "计划数量")
    private Integer planQty;

    @ApiModelProperty(value = "单位")
    private String uom;

    @ApiModelProperty(value = "接收数量汇总")
    private Integer receiveQty;

    @ApiModelProperty(value = "合格数量")
    private Integer qcOkQty;

    @ApiModelProperty(value = "完成日期")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date completeDate;

    @ApiModelProperty(value = "完成标识")
    private Boolean completeFlag;

    @ApiModelProperty(value = "采购价格")
    private Double poPrice;

    @ApiModelProperty(value = "备注")
    private String note;


}
