package com.siirisoft.aim.wms.mapper.quantity.ext;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * @User DKY
 * @Date 2020/6/29
 * @Description WmsItemOnhandQuantityMapper扩展
 */
public interface WmsItemOnhandQuantityMapperExt {
    IPage queryTotal(Page page, @Param(Constants.WRAPPER)Wrapper wrapper);
}
