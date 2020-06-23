package com.siirisoft.aim.wms.entity.plant;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @User DKY
 * @Date 2020/6/23
 * @Description 工厂PO
 */
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Data
public class Plant {
    @ApiModelProperty(value = "工厂ID")
    private Integer id;

    @ApiModelProperty(value = "应用ID")
    private String appId;

    @ApiModelProperty(value = "工厂编码")
    private String code;

    @ApiModelProperty(value = "扩展")
    private String extend;

    @ApiModelProperty(value = "扩展使用")
    private String mapFile;

    @ApiModelProperty(value = "工厂名称")
    private String name;

    @ApiModelProperty(value = "合格率")
    private float passRate;

    @ApiModelProperty(value = "实现率")
    private float achieveRate;

    @ApiModelProperty(value = "描述")
    private String remark;

}
