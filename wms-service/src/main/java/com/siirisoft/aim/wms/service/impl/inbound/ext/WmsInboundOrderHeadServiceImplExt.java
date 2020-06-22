package com.siirisoft.aim.wms.service.impl.inbound.ext;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.siirisoft.aim.wms.entity.inbound.ext.WmsInboundOrderHeadExt;
import com.siirisoft.aim.wms.mapper.inbound.ext.WmsInboundOrderHeadMapperExt;
import com.siirisoft.aim.wms.service.inbound.ext.IWmsInboundOrderHeadServiceExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @User DKY
 * @Date 2020/5/15
 * @Description WmsInboundOrderHeadServiceImpl扩展
 */
@Service
public class WmsInboundOrderHeadServiceImplExt implements IWmsInboundOrderHeadServiceExt {

    @Autowired
    private WmsInboundOrderHeadMapperExt wmsInboundOrderHeadMapperExt;

    @Override
    public IPage<WmsInboundOrderHeadExt> wmsInboundHeadOrderList(Page<WmsInboundOrderHeadExt> page,QueryWrapper wrapper) {
        return wmsInboundOrderHeadMapperExt.wmsInboundHeadOrderList(page,wrapper);
    }
}
