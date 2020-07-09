package com.siirisoft.aim.wms.entity.autonumber;

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
 * 单据自动编码设置; InnoDB free: 11264 kB
 * </p>
 *
 * @author DKY
 * @since 2020-06-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="WmsDocAutonumber对象", description="单据自动编码设置; InnoDB free: 11264 kB")
public class WmsDocAutonumber implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "创建用户ID")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    private Date creationDate;

    @ApiModelProperty(value = "最后更新用户ID")
    private String lastUpdateBy;

    @ApiModelProperty(value = "最后更新时间")
    private Date lastUpdateDate;

    @ApiModelProperty(value = "快码")
    private String lookupcode;

    @ApiModelProperty(value = "类型值")
    private String docType;

    @ApiModelProperty(value = "拓展说明")
    private String docTypeExtend;

    @ApiModelProperty(value = "单据名称")
    private String docName;

    @ApiModelProperty(value = "前缀")
    private String prefix;

    @ApiModelProperty(value = "位数")
    private Integer sequenceNum;

    @ApiModelProperty(value = "备注")
    private String note;


}
