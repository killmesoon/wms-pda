package com.siirisoft.aim.wms.entity.events;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 事件设置; InnoDB free: 11264 kB
 * </p>
 *
 * @author DKY
 * @since 2020-06-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="WmsObjectEvents对象", description="事件设置; InnoDB free: 11264 kB")
public class WmsObjectEvents implements Serializable {

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

    @ApiModelProperty(value = "事件ID")
    @TableId(value = "event_id", type = IdType.AUTO)
    private Integer eventId;

    @ApiModelProperty(value = "事件类型ID")
    private Integer eventTypeId;

    @ApiModelProperty(value = "事件类型编码")
    private String eventTypeCode;

    @ApiModelProperty(value = "条码号")
    private String barcode;

    @ApiModelProperty(value = "物料ID")
    private Integer itemId;

    @ApiModelProperty(value = "批次")
    private String lotCode;

    @ApiModelProperty(value = "事件时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
    private Date eventTime;

    @ApiModelProperty(value = "事件数量")
    private Integer eventQty;

    @ApiModelProperty(value = "单位编码")
    private String eventUomCode;

    @ApiModelProperty(value = "账务时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
    private Date accountTime;

    @ApiModelProperty(value = "事件原因编码")
    private String eventReasonCode;

    @ApiModelProperty(value = "来源仓库ID")
    private Integer warehouseIdFrom;

    @ApiModelProperty(value = "来源货位ID")
    private Integer locatorIdFrom;

    @ApiModelProperty(value = "目标工厂ID")
    private Integer plantIdTo;

    @ApiModelProperty(value = "目标仓库ID")
    private Integer warehouseIdTo;

    @ApiModelProperty(value = "目标货位ID")
    private Integer locatorIdTo;

    @ApiModelProperty(value = "供应商ID")
    private Integer supplierId;

    @ApiModelProperty(value = "来源单据类型")
    private String sourceDocType;

    @ApiModelProperty(value = "来源单据号")
    private String sourceDocNumber;

    @ApiModelProperty(value = "来源单据头ID")
    private Integer sourceDocHeadId;

    @ApiModelProperty(value = "来源行ID")
    private Integer sourceDocLineId;

    @ApiModelProperty(value = "同步ERP标识")
    private String erpFlag;

    @ApiModelProperty(value = "父事件ID")
    private Integer parentEventId;

    @ApiModelProperty(value = "备注")
    private String note;


}
