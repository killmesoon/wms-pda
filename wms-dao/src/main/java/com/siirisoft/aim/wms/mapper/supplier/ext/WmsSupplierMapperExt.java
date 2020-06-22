package com.siirisoft.aim.wms.mapper.supplier.ext;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.siirisoft.aim.wms.entity.supplier.ext.WmsSupplierExt;
import org.apache.ibatis.annotations.Param;

/**
 * @User DKY
 * @Date 2020/5/20
 * @Description WmsSupplierMapper扩展
 */
public interface WmsSupplierMapperExt extends BaseMapper<WmsSupplierExt> {
    IPage findWmsSupplierListExt(Page page, @Param(Constants.WRAPPER)Wrapper wrapper);
}
