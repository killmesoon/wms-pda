package com.siirisoft.aim.wms.service.impl.outbound;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.siirisoft.aim.wms.entity.outbound.WmsOutboundOrderDetail;
import com.siirisoft.aim.wms.mapper.outbound.WmsOutboundOrderDetailMapper;
import com.siirisoft.aim.wms.mapper.outbound.ext.WmsOutboundOrderDetailMapperExt;
import com.siirisoft.aim.wms.service.outbound.IWmsOutboundOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 入库单明细表; InnoDB free: 11264 kB 服务实现类
 * </p>
 *
 * @author DKY
 * @since 2020-05-15
 */
@Service
public class WmsOutboundOrderDetailServiceImpl extends ServiceImpl<WmsOutboundOrderDetailMapper, WmsOutboundOrderDetail> implements IWmsOutboundOrderDetailService {

    @Autowired
    private WmsOutboundOrderDetailMapperExt wmsOutboundOrderDetailMapperExt;

    @Override
    public IPage queryOutboundOrderDetail(Page page, Wrapper wrapper) {
        return wmsOutboundOrderDetailMapperExt.findOutboundOrderDetail(page, wrapper);
    }
}
