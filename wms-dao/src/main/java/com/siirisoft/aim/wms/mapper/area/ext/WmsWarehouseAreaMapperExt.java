package com.siirisoft.aim.wms.mapper.area.ext;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * @User DKY
 * @Date 2020/5/27
 * @Description WmsWarehouseAreaMapper扩展
 */
public interface WmsWarehouseAreaMapperExt {
    IPage findWarehouseAreaList(Page page , @Param(Constants.WRAPPER)Wrapper wrapper);
}
