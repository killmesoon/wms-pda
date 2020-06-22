package com.siirisoft.aim.wms.entity.locator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.xml.stream.events.DTD;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 货位设置; InnoDB free: 11264 kB
 * </p>
 *
 * @author DKY
 * @since 2020-05-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="WmsLocator对象", description="货位设置; InnoDB free: 11264 kB")
public class WmsLocator implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "仓库ID")
    private Integer warehouseId;

    @ApiModelProperty(value = "区域ID")
    private Integer areaId;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "locator_id", type = IdType.AUTO)
    private Integer locatorId;

    @ApiModelProperty(value = "货位编码")
    private String locatorCode;

    @ApiModelProperty(value = "货位名称")
    private String locatorName;

    @ApiModelProperty(value = "货位类型")
    private String locatorType;

    @ApiModelProperty(value = "是否有效")
    private Boolean enableFlag;

    @ApiModelProperty(value = "地理位置")
    private String gpsInfo;

    @ApiModelProperty(value = "描述")
    private String description;

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


}
