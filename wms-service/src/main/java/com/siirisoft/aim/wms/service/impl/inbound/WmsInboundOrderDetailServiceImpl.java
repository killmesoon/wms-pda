package com.siirisoft.aim.wms.service.impl.inbound;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.siirisoft.aim.wms.entity.inbound.WmsInboundOrderDetail;
import com.siirisoft.aim.wms.mapper.inbound.WmsInboundOrderDetailMapper;
import com.siirisoft.aim.wms.mapper.inbound.ext.WmsInboundOrderDetailMapperExt;
import com.siirisoft.aim.wms.service.inbound.IWmsInboundOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 入库单明细表; InnoDB free: 11264 kB 服务实现类
 * </p>
 *
 * @author DKY
 * @since 2020-05-14
 */
@Service
public class WmsInboundOrderDetailServiceImpl extends ServiceImpl<WmsInboundOrderDetailMapper, WmsInboundOrderDetail> implements IWmsInboundOrderDetailService {

    @Autowired
    private WmsInboundOrderDetailMapperExt wmsInboundOrderDetailMapperExt;

    @Override
    public IPage queryInboundOrderDetail(Page page, Wrapper wrapper) {
        return wmsInboundOrderDetailMapperExt.findInboundOrderDetail(page, wrapper);
    }
}
