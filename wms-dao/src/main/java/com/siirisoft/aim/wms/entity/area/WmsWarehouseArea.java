package com.siirisoft.aim.wms.entity.area;

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
 * 区域设置; InnoDB free: 11264 kB; (`warehouse_id`) REFER `wms/wms_warehouse`(`warehou
 * </p>
 *
 * @author DKY
 * @since 2020-05-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="WmsWarehouseArea对象", description="区域设置; InnoDB free: 11264 kB; (`warehouse_id`) REFER `wms/wms_warehouse`(`warehou")
public class WmsWarehouseArea implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "工厂ID")
    private Integer plantId;

    @ApiModelProperty(value = "工厂编码")
    private String plantCode;

    @ApiModelProperty(value = "工厂名称")
    private String plantName;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "area_id", type = IdType.AUTO)
    private Integer areaId;

    @ApiModelProperty(value = "仓库ID")
    private Integer warehouseId;

    @ApiModelProperty(value = "区域编码")
    private String areaCode;

    @ApiModelProperty(value = "区域名称")
    private String areaName;

    @ApiModelProperty(value = "区域类型")
    private String areaType;

    @ApiModelProperty(value = "是否有效")
    private Boolean enableFlag;

    @ApiModelProperty(value = "描述")
    private String description;

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


}
