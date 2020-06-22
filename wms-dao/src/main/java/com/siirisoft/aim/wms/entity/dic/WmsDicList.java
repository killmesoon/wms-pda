package com.siirisoft.aim.wms.entity.dic;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * wms字典表; InnoDB free: 11264 kB; (`dic_type_id`) REFER `wms/wms_dic_type`(`dic_typ
 * </p>
 *
 * @author DKY
 * @since 2020-05-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("wms_dic_list")
@ApiModel(value="WmsDicList对象", description="wms字典表; InnoDB free: 11264 kB; (`dic_type_id`) REFER `wms/wms_dic_type`(`dic_typ")
public class WmsDicList implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "创建用户ID")
    private Integer createdBy;

    @ApiModelProperty(value = "创建时间")
    private Date creationDate;

    @ApiModelProperty(value = "最后更新用户ID")
    private Integer lastUpdateBy;

    @ApiModelProperty(value = "最后更新时间")
    private Date lastUpdateDate;

    @ApiModelProperty(value = "事件类型ID")
    private Integer dicTypeId;

    @ApiModelProperty(value = "事件ID")
    @TableId(value = "dic_id", type = IdType.AUTO)
    private Integer dicId;

    @ApiModelProperty(value = "事件类型编码")
    private String dicCode;

    @ApiModelProperty(value = "条码号")
    private String dicName;

    @ApiModelProperty(value = "是否有效")
    private Boolean enableFlag;

    @ApiModelProperty(value = "是否系统录入")
    private Boolean isSystem;

    @ApiModelProperty(value = "单据录入标识")
    private Boolean docFlag;

    @ApiModelProperty(value = "备注")
    private String note;



}
