package com.siirisoft.aim.wms.mapper.uom.ext;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * @User DKY
 * @Date 2020/5/21
 * @Description WmsUomMapper扩展
 */
public interface WmsUomMapperExt {
    IPage findUomList(Page page, @Param(Constants.WRAPPER) Wrapper wrapper);
}
