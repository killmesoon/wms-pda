package com.siirisoft.aim.wms.entity.item;

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
 * 物料主数据; InnoDB free: 11264 kB
 * </p>
 *
 * @author DKY
 * @since 2020-05-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="WmsItem对象", description="物料主数据; InnoDB free: 11264 kB")
public class WmsItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "工厂编码")
    private String plantCode;

    @ApiModelProperty(value = "工厂名称")
    private String plantName;

    @ApiModelProperty(value = "创建用户ID")
    private Integer createdBy;

    @ApiModelProperty(value = "创建时间")
    private Date creationDate;

    @ApiModelProperty(value = "最后更新用户ID")
    private Integer lastUpdateBy;

    @ApiModelProperty(value = "最后更新时间")
    private Date lastUpdateDate;

    @ApiModelProperty(value = "工厂ID")
    private Integer plantId;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "item_id", type = IdType.AUTO)
    private Integer itemId;

    @ApiModelProperty(value = "物料编码")
    private String itemCode;

    @ApiModelProperty(value = "旧编码")
    private String oldCode;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "物料类型")
    private String itemType;

    @ApiModelProperty(value = "是否有效")
    private Boolean enableFlag;

    @ApiModelProperty(value = "主单位")
    private String primaryUom;

    @ApiModelProperty(value = "单重")
    private String unitWeight;

    @ApiModelProperty(value = "材质")
    private String itemTexture;

    @ApiModelProperty(value = "尺寸")
    private String itemSize;

    @ApiModelProperty(value = "规格")
    private String itemSpec;

    @ApiModelProperty(value = "管理模式")
    private String codeType;

    @ApiModelProperty(value = "最小包装数")
    private Integer minPackageQty;

    @ApiModelProperty(value = "制单数量")
    private Integer docCreateQty;

    @ApiModelProperty(value = "是否来料检")
    private Boolean iqcFlag;

    @ApiModelProperty(value = "默认接收仓")
    private String rcvWarehouseCode;

    @ApiModelProperty(value = "默认入库仓")
    private String invWarehouseCode;

    @ApiModelProperty(value = "入库规则")
    private String instockRule;

    @ApiModelProperty(value = "出库规则")
    private String outstockRule;

    @ApiModelProperty(value = "安全库存值")
    private Integer safetyStockValue;

    @ApiModelProperty(value = "最大库存")
    private Integer maxStockQty;

    @ApiModelProperty(value = "最小库存")
    private Integer minStockQty;

    @ApiModelProperty(value = "转运批量")
    private Integer transferLotSize;


}
