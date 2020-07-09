package com.siirisoft.aim.wms.entity.events.type;

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
 * 事件类型设置; InnoDB free: 11264 kB
 * </p>
 *
 * @author DKY
 * @since 2020-06-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="WmsObjectEventType对象", description="事件类型设置; InnoDB free: 11264 kB")
public class WmsObjectEventType implements Serializable {

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

    @ApiModelProperty(value = "事件类型ID")
    @TableId(value = "event_type_id", type = IdType.AUTO)
    private Integer eventTypeId;

    @ApiModelProperty(value = "事件类型编码")
    private String eventTypeCode;

    @ApiModelProperty(value = "事件类型名称")
    private String eventTypeName;

    @ApiModelProperty(value = "库存变化标识")
    private String calculatorFlag;

    @ApiModelProperty(value = "库存变化类型")
    private String calculatorType;

    @ApiModelProperty(value = "是否有效")
    private Boolean enableFlag;

    @ApiModelProperty(value = "是否系统录入")
    private Boolean isSystem;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "事件包信息")
    private String eventPackInfo;


}
