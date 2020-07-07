package com.siirisoft.aim.wms.entity.barcode;

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
 * 条码定义表; InnoDB free: 11264 kB
 * </p>
 *
 * @author DKY
 * @since 2020-05-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="WmsBarcode对象", description="条码定义表; InnoDB free: 11264 kB")
public class WmsBarcode implements Serializable {

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

    @ApiModelProperty(value = "工厂ID")
    private Integer plantId;

    @ApiModelProperty(value = "工厂编码")
    private String plantCode;

    @ApiModelProperty(value = "工厂名称")
    private String plantName;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "barcode_id", type = IdType.AUTO)
    private Integer barcodeId;

    @ApiModelProperty(value = "条码号")
    private String barcode;

    @ApiModelProperty(value = "二维码内容")
    private String dCode;

    @ApiModelProperty(value = "条码类型")
    private String barcodeType;

    @ApiModelProperty(value = "条码状态")
    private String barcodeStatus;

    @ApiModelProperty(value = "供应商ID")
    private Integer supplierId;

    @ApiModelProperty(value = "描述")
    private String description;


}
