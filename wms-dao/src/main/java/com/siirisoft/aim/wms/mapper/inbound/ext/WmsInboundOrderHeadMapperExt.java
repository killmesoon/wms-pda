package com.siirisoft.aim.wms.mapper.inbound.ext;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.siirisoft.aim.wms.entity.inbound.ext.WmsInboundOrderHeadExt;
import org.apache.ibatis.annotations.Param;

/**
 * @User DKY
 * @Date 2020/5/18
 * @Description WmsInboundOrderHeadMapper扩展
 */
public interface WmsInboundOrderHeadMapperExt extends BaseMapper<WmsInboundOrderHeadExt> {
    IPage<WmsInboundOrderHeadExt> wmsInboundHeadOrderList(IPage<WmsInboundOrderHeadExt> page, @Param(Constants.WRAPPER) QueryWrapper wrapper);
}
