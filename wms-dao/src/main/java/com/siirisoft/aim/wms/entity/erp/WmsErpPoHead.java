package com.siirisoft.aim.wms.entity.erp;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 采购订单头表; InnoDB free: 11264 kB
 * </p>
 *
 * @author DKY
 * @since 2020-05-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="WmsErpPoHead对象", description="采购订单头表; InnoDB free: 11264 kB")
public class WmsErpPoHead implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "创建用户ID")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    private Date creationDate;

    @ApiModelProperty(value = "最后更新用户ID")
    private String lastUpdateBy;

    @ApiModelProperty(value = "最后更新时间")
    private Date lastUpdateDate;

    @ApiModelProperty(value = "订单头ID")
    @TableId(value = "head_id", type = IdType.AUTO)
    private Integer headId;

    @ApiModelProperty(value = "工厂ID")
    private Integer plantId;

    @ApiModelProperty(value = "订单类型")
    private String poType;

    @ApiModelProperty(value = "订单状态")
    private String poStatus;

    @ApiModelProperty(value = "采购员ID")
    private Integer purchaserId;

    @ApiModelProperty(value = "发货地址")
    private String shippingAddress;

    @ApiModelProperty(value = "供应商ID")
    private Integer supplierId;

    @ApiModelProperty(value = "付款方式")
    private String paymentTerms;

    @ApiModelProperty(value = "来源类型")
    private String poSourceType;

    @ApiModelProperty(value = "备注")
    private String note;


}
