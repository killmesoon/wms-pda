package com.siirisoft.aim.wms.entity.asn;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * 送货单头表; InnoDB free: 11264 kB
 * </p>
 *
 * @author DKY
 * @since 2020-06-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="WmsErpAsnHead对象", description="送货单头表; InnoDB free: 11264 kB")
public class WmsErpAsnHead implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "创建用户ID")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
    private Date creationDate;

    @ApiModelProperty(value = "最后更新用户ID")
    private String lastUpdateBy;

    @ApiModelProperty(value = "最后更新时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
    private Date lastUpdateDate;

    @ApiModelProperty(value = "送货单头ID")
    @TableId(value = "head_id", type = IdType.AUTO)
    private Integer headId;

    @ApiModelProperty(value = "送货单类型")
    private String asnType;

    @ApiModelProperty(value = "送货单号")
    private String asnNumber;

    @ApiModelProperty(value = "送货单状态")
    private String asnStatus;

    @ApiModelProperty(value = "是否紧急")
    private Boolean isEmergency;

    @ApiModelProperty(value = "采购工厂")
    private Integer plantId;

    @ApiModelProperty(value = "工厂编码")
    private String plantCode;

    @ApiModelProperty(value = "工厂名称")
    private String plantName;

    @ApiModelProperty(value = "预计到货日期")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
    private Date planDeliverDate;

    @ApiModelProperty(value = "来源单据类型")
    private String sourceDocType;

    @ApiModelProperty(value = "来源单号")
    private String sourceDocNum;

    @ApiModelProperty(value = "收货地址")
    private String shippingAddress;

    @ApiModelProperty(value = "供应商ID")
    private Integer supplierId;

    @ApiModelProperty(value = "送货单位")
    @JsonProperty(value = "dCompanyName")
    private String dCompanyName;

    @ApiModelProperty(value = "送货人")
    @JsonProperty(value = "dUserName")
    private String dUserName;

    @ApiModelProperty(value = "车船号")
    @JsonProperty(value = "dShipNum")
    private String dShipNum;

    @ApiModelProperty(value = "联系电话")
    @JsonProperty(value = "dPhone")
    private String dPhone;

    @ApiModelProperty(value = "来源方式")
    private String createType;

    @ApiModelProperty(value = "备注")
    private String note;


}
