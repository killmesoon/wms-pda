package com.siirisoft.aim.wms.mapper.locator.ext;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * @User DKY
 * @Date 2020/5/26
 * @Description WmsLocatorMapper扩展
 */
public interface WmsLocatorMapperExt {
    IPage findLocatorList(Page page, @Param(Constants.WRAPPER)Wrapper wrapper);
}
