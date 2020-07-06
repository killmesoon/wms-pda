package com.siirisoft.aim.wms.entity.warehouse;

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
 * 仓库设置; InnoDB free: 11264 kB
 * </p>
 *
 * @author DKY
 * @since 2020-05-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="WmsWarehouse对象", description="仓库设置; InnoDB free: 11264 kB")
public class WmsWarehouse implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "工厂ID")
    private Integer plantId;

    @ApiModelProperty(value = "工厂编码")
    private String plantCode;

    @ApiModelProperty(value = "工厂名称")
    private String plantName;

    @ApiModelProperty(value = "仓库ID")
    @TableId(value = "warehouse_id", type = IdType.AUTO)
    private Integer warehouseId;

    @ApiModelProperty(value = "仓库编码")
    private String warehouseCode;

    @ApiModelProperty(value = "仓库名称")
    private String warehouseName;

    @ApiModelProperty(value = "仓库简称")
    private String shortName;

    @ApiModelProperty(value = "仓库类型")
    private String warehouseType;

    @ApiModelProperty(value = "是否允许负库存")
    private Boolean negativeFlag;

    @ApiModelProperty(value = "备用 - 是否配送管控")
    private Boolean disFlag;

    @ApiModelProperty(value = "备用 - 默认配送区")
    private String defaultDisArea;

    @ApiModelProperty(value = "备用 - 是否参与计划运送")
    private Boolean netFlag;

    @ApiModelProperty(value = "是否参与盘点")
    private Boolean panrangeFlag;

    @ApiModelProperty(value = "是否有效")
    private Boolean enableFlag;

    @ApiModelProperty(value = "创建用户ID")
    private Integer createdBy;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "创建日期")
    private Date creationDate;

    @ApiModelProperty(value = "最后更新用户ID")
    private Integer lastUpdateBy;

    @ApiModelProperty(value = "最后更新日期")
    private Date lastUpdateDate;


}
