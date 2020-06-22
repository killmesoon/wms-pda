package com.siirisoft.aim.wms.mapper.outbound.ext;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.siirisoft.aim.wms.entity.outbound.ext.WmsOutboundOrderHeadExt;
import org.apache.ibatis.annotations.Param;

/**
 * @User DKY
 * @Date 2020/5/19
 * @Description WmsOutboundOrderHeadMapper扩展类
 */
public interface WmsOutboundOrderHeadMapperExt extends BaseMapper<WmsOutboundOrderHeadExt> {
    IPage<WmsOutboundOrderHeadExt> findOutboundOrderList(Page<WmsOutboundOrderHeadExt> page, @Param(Constants.WRAPPER) Wrapper wrapper);
}
