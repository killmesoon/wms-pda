package com.siirisoft.aim.wms.mapper.dic.ext;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.siirisoft.aim.wms.entity.dic.ext.WmsDicListExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @User DKY
 * @Date 2020/6/3
 * @Description WmsDicListMapperExt扩展
 */
public interface WmsDicListMapperExt {
    List<WmsDicListExt> findWmsDicListExtList(@Param(Constants.WRAPPER)Wrapper wrapper);
}
