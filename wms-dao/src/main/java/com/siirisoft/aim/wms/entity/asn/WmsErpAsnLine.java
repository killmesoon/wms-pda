package com.siirisoft.aim.wms.entity.asn;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 送货单行表; InnoDB free: 11264 kB
 * </p>
 *
 * @author DKY
 * @since 2020-06-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="WmsErpAsnLine对象", description="送货单行表; InnoDB free: 11264 kB")
public class WmsErpAsnLine implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "送货单行ID")
    @TableId(value = "line_id", type = IdType.AUTO)
    private Integer lineId;

    @ApiModelProperty(value = "送货单头ID")
    private Integer headId;

    @ApiModelProperty(value = "送货单行号")
    private String lineNum;

    @ApiModelProperty(value = "物料ID")
    private Integer itemId;

    @ApiModelProperty(value = "仓库ID")
    private Integer warehouseId;

    @ApiModelProperty(value = "计划数量")
    private Integer planQty;

    @ApiModelProperty(value = "单位")
    private String uom;

    @ApiModelProperty(value = "单价")
    private String unitPrice;

    @ApiModelProperty(value = "来源PO")
    private String sourcePo;

    @ApiModelProperty(value = "来源PO行")
    private String sourceLine;

    @ApiModelProperty(value = "收货数量")
    private Integer receiveQty;

    @ApiModelProperty(value = "合格数量")
    private Integer qcOkQty;

    @ApiModelProperty(value = "收货完成标识")
    private Boolean completeFlag;

    @ApiModelProperty(value = "收货完成日期")
    private Date completeDate;

    @ApiModelProperty(value = "备注")
    private String note;


}
