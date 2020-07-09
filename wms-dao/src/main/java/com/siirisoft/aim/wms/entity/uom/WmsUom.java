package com.siirisoft.aim.wms.entity.uom;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 单位设置; InnoDB free: 11264 kB
 * </p>
 *
 * @author DKY
 * @since 2020-05-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="WmsUom对象", description="单位设置; InnoDB free: 11264 kB")
public class WmsUom implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "单位ID")
    @TableId(value = "uom_id", type = IdType.AUTO)
    private Integer uomId;

    @ApiModelProperty(value = "单位编码")
    private String uomCode;

    @ApiModelProperty(value = "单位名称")
    private String uomName;

    @ApiModelProperty(value = "非必输,一个单位类型只能有一个")
    private Boolean primaryFlag;

    @ApiModelProperty(value = "单位类型")
    private String uomType;

    @ApiModelProperty(value = "与主单位换算系数")
    private Double conversionRatio;

    @ApiModelProperty(value = "保留小数位")
    private BigDecimal decimalNumber;

    @ApiModelProperty(value = "是否有效")
    private Boolean enableFlag;

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


}
