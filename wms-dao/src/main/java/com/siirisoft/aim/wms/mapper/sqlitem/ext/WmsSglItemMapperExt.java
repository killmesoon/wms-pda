package com.siirisoft.aim.wms.mapper.sqlitem.ext;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @User DKY
 * @Date 2020/6/15
 * @Description WmsSglItemMapper扩展
 */
public interface WmsSglItemMapperExt {
    IPage findWmsSglItemList(Page page, @Param(Constants.WRAPPER)Wrapper wrapper);

    @Select("select COALESCE(max(layer_number),0) from wms_sgl_item ${ew.customSqlSegment}")
    Integer findMaxLayerNumber(@Param(Constants.WRAPPER) Wrapper wrapper);
}
