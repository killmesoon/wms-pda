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
 * 入库单头表; InnoDB free: 11264 kB
 * </p>
 *
 * @author DKY
 * @since 2020-05-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="WmsInboundOrderHead对象", description="入库单头表; InnoDB free: 11264 kB")
public class WmsInboundOrderHead implements Serializable {

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
    @TableId(value = "head_id", type = IdType.AUTO)
    private Integer headId;

    @ApiModelProperty(value = "工厂ID")
    private Integer plantId;

    @ApiModelProperty(value = "单据类型")
    private String docType;

    @ApiModelProperty(value = "单据状态")
    private String docStatus;

    @ApiModelProperty(value = "单据号")
    private String docNumber;

    @ApiModelProperty(value = "供应商ID")
    private Integer supplierId;

    @ApiModelProperty(value = "来源单据ID")
    private Integer sourceDocId;

    @ApiModelProperty(value = "来源单据类型")
    private String sourceDocType;

    @ApiModelProperty(value = "来源单据号")
    private Integer sourceDocNum;

    @ApiModelProperty(value = "分配人ID")
    private Integer assigneeId;

    @ApiModelProperty(value = "计划时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date planTime;

    @ApiModelProperty(value = "打印次数")
    private Integer printCount;

    @ApiModelProperty(value = "最后打印时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date lastPrintDate;

    @ApiModelProperty(value = "最后打印用户")
    private String lastPrintUser;

    @ApiModelProperty(value = "RFID集成标识")
    private Boolean rfidFlag;

    @ApiModelProperty(value = "ERP集成标识")
    private Boolean erpFlag;

    @ApiModelProperty(value = "备注")
    private String note;


}
