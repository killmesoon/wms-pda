package com.siirisoft.aim.wms.service.impl.outbound;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.siirisoft.aim.wms.entity.outbound.WmsOutboundOrderHead;
import com.siirisoft.aim.wms.entity.outbound.ext.WmsOutboundOrderHeadExt;
import com.siirisoft.aim.wms.mapper.outbound.WmsOutboundOrderHeadMapper;
import com.siirisoft.aim.wms.mapper.outbound.ext.WmsOutboundOrderHeadMapperExt;
import com.siirisoft.aim.wms.service.outbound.IWmsOutboundOrderHeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 出库单头表; InnoDB free: 11264 kB 服务实现类
 * </p>
 *
 * @author DKY
 * @since 2020-05-15
 */
@Service
public class WmsOutboundOrderHeadServiceImpl extends ServiceImpl<WmsOutboundOrderHeadMapper, WmsOutboundOrderHead> implements IWmsOutboundOrderHeadService {

    @Autowired
    private WmsOutboundOrderHeadMapperExt wmsOutboundOrderHeadMapperExt;


    @Override
    public IPage<WmsOutboundOrderHeadExt> findOutboundOrderList(Page page, Wrapper wrapper) {
        return wmsOutboundOrderHeadMapperExt.findOutboundOrderList(page,wrapper);
    }
}
