package com.siirisoft.aim.wms.entity.dic.ext;

import com.siirisoft.aim.wms.entity.dic.WmsDicType;
import lombok.Data;

import java.util.List;

/**
 * @User DKY
 * @Date 2020/6/3
 * @Description
 */
@Data
public class WmsDic extends WmsDicType {
    private List<WmsDicListExt> children;
    private String treeName;
}
