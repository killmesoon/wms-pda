package com.siirisoft.aim.wms.entity.quantity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 库存现有量; InnoDB free: 11264 kB
 * </p>
 *
 * @author DKY
 * @since 2020-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("wms_item_onhand_quantity")
@ApiModel(value="WmsItemOnhandQuantity对象", description="库存现有量; InnoDB free: 11264 kB")
public class WmsItemOnhandQuantity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "工厂ID")
    private Integer plantId;

    @ApiModelProperty(value = "物料ID")
    private Integer itemId;

    @ApiModelProperty(value = "仓库ID")
    private Integer warehouseId;

    @ApiModelProperty(value = "货位ID")
    private Integer locatorId;

    @ApiModelProperty(value = "现有量")
    private Integer loctOnhand;

    @ApiModelProperty(value = "批次")
    private String lotNumber;

    @ApiModelProperty(value = "单位")
    private String uomCode;

    @ApiModelProperty(value = "合并标识")
    private Boolean mergeFlag;

    @ApiModelProperty(value = "所有者类型")
    private String ownerType;

    @ApiModelProperty(value = "所有者地址ID")
    private Integer ownerSiteId;


}
