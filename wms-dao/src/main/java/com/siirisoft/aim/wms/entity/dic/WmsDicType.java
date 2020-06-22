package com.siirisoft.aim.wms.entity.dic;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 字典类型表; InnoDB free: 11264 kB
 * </p>
 *
 * @author DKY
 * @since 2020-05-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="WmsDicType对象", description="字典类型表; InnoDB free: 11264 kB")
public class WmsDicType implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "事件类型ID")
    @TableId(value = "dic_type_id", type = IdType.AUTO)
    private Integer dicTypeId;

    @ApiModelProperty(value = "事件类型编码")
    private String dicTypeCode;

    @ApiModelProperty(value = "事件类型名称")
    private String dicTypeName;


}
