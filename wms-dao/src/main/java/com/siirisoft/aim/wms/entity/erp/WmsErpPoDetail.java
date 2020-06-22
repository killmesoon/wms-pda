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
 * 采购订单明细表; InnoDB free: 11264 kB
 * </p>
 *
 * @author DKY
 * @since 2020-05-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="WmsErpPoDetail对象", description="采购订单明细表; InnoDB free: 11264 kB")
public class WmsErpPoDetail implements Serializable {

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
    private Integer lineId;

    @ApiModelProperty(value = "明细号")
    @TableId(value = "detail_id", type = IdType.AUTO)
    private Integer detailId;

    @ApiModelProperty(value = "计划数量")
    private Integer planQty;

    @ApiModelProperty(value = "计划单号")
    private String planOrder;

    @ApiModelProperty(value = "计划单行号")
    private String planOrderLine;

    @ApiModelProperty(value = "发运计划日期")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date shippingPlanDate;

    @ApiModelProperty(value = "收货日期")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date deliveryDate;

    @ApiModelProperty(value = "收货数量")
    private Integer deliveryQty;

    @ApiModelProperty(value = "收货单号")
    private String deliveryOrder;

    @ApiModelProperty(value = "收货单行号")
    private String deliveryOrderLine;

    @ApiModelProperty(value = "备注")
    private String note;


}
