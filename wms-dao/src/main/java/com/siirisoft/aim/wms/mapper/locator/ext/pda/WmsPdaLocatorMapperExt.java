package com.siirisoft.aim.wms.mapper.locator.ext.pda;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * @User DKY
 * @Date 2020/6/22
 * @Description 货位PDA mapper
 */
public interface WmsPdaLocatorMapperExt {
    IPage queryLocatorDetail(Page page , @Param(Constants.WRAPPER) Wrapper wrapper);

    Integer querySglItemByLocatorId(@Param(Constants.WRAPPER) Wrapper wrapper);
}
