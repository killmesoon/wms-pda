package com.siirisoft.aim.wms.entity.dic.ext;

import com.siirisoft.aim.wms.entity.dic.WmsDicList;
import lombok.Data;

/**
 * @User DKY
 * @Date 2020/6/3
 * @Description WmsDicList扩展类
 */
@Data
public class WmsDicListExt extends WmsDicList {
    private String dicTypeCode;
    private String dicTypeName;
    private String treeName;
}
