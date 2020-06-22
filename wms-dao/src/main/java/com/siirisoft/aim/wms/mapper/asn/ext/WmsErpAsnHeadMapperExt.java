package com.siirisoft.aim.wms.mapper.asn.ext;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.siirisoft.aim.wms.entity.asn.ext.WmsErpAsnHeadExt;
import org.apache.ibatis.annotations.Param;

/**
 * @User DKY
 * @Date 2020/6/8
 * @Description WmsErpAsnHeadMapper扩展
 */
public interface WmsErpAsnHeadMapperExt {
    IPage<WmsErpAsnHeadExt> findWmsErpAsnHeadList(Page page, @Param(Constants.WRAPPER)Wrapper wrapper);
}
