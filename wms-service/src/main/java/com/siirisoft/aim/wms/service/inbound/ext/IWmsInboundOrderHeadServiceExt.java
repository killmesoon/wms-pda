package com.siirisoft.aim.wms.service.inbound.ext;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.siirisoft.aim.wms.entity.inbound.ext.WmsInboundOrderHeadExt;

/**
 * @User DKY
 * @Date 2020/5/15
 * @Description IWmsInboundOrderHeadService接口
 */
public interface IWmsInboundOrderHeadServiceExt {
    IPage<WmsInboundOrderHeadExt> wmsInboundHeadOrderList(Page<WmsInboundOrderHeadExt> page, QueryWrapper wrapper);
}
