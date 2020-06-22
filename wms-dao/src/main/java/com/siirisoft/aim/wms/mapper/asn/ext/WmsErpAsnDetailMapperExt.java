package com.siirisoft.aim.wms.mapper.asn.ext;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * @User DKY
 * @Date 2020/6/12
 * @Description WmsErpAsnDetailMapper扩展
 */
public interface WmsErpAsnDetailMapperExt {
    IPage findWmsErpAsnDetailByHeadId(Page page, @Param(Constants.WRAPPER)Wrapper wrapper);
}
