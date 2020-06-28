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
 * 入库单明细表; InnoDB free: 11264 kB
 * </p>
 *
 * @author DKY
 * @since 2020-05-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="WmsInboundOrderDetail对象", description="入库单明细表; InnoDB free: 11264 kB")
public class WmsInboundOrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "创建人编码")
    private Integer createdBy;

    @ApiModelProperty(value = "创建日期")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
    private Date creationDate;

    @ApiModelProperty(value = "更新人编码")
    private Integer lastUpdateBy;

    @ApiModelProperty(value = "更新日期")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
    private Date lastUpdateDate;

    @ApiModelProperty(value = "订单头ID")
    private Integer headId;

    @ApiModelProperty(value = "行ID")
    private Integer lineId;

    @ApiModelProperty(value = "明细ID")
    @TableId(value = "detail_id", type = IdType.AUTO)
    private Integer detailId;

    @ApiModelProperty(value = "建议仓库ID")
    private Integer advWarehouseId;

    @ApiModelProperty(value = "建议货位ID")
    private Integer advLocatorId;

    @ApiModelProperty(value = "建议批次")
    private String advLotCode;

    @ApiModelProperty(value = "建议数量")
    private Integer advQuantity;

    @ApiModelProperty(value = "建议条码")
    private String advBarcode;

    @ApiModelProperty(value = "执行条码")
    private String execBarcode;

    @ApiModelProperty(value = "执行仓库ID")
    private Integer excuWarehouseId;

    @ApiModelProperty(value = "执行货位ID")
    private Integer excuLocatorId;

    @ApiModelProperty(value = "执行批次号")
    private String excuLotCode;

    @ApiModelProperty(value = "执行数量")
    private Integer excuQuantity;

    @ApiModelProperty(value = "备注")
    private String note;


}
