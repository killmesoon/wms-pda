package com.siirisoft.aim.wms.service.impl.outbound.ext.pda;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.siirisoft.aim.wms.mapper.outbound.ext.pda.WmsPdaOutboundOrderDetailMapper;
import com.siirisoft.aim.wms.service.outbound.pda.ABPdaWmsOutboundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @User DKY
 * @Date 2020/6/22
 * @Description ABPdaWmsOutboundOrderService实现类
 */
@Service
public class ABPdaWmsOutboundOrderServiceImpl implements ABPdaWmsOutboundOrderService {

    @Autowired
    private WmsPdaOutboundOrderDetailMapper wmsPdaOutboundOrderDetailMapper;

    @Override
    public IPage queryOutboundOrderDetail(Page page, Wrapper wrapper) {
        return wmsPdaOutboundOrderDetailMapper.queryOutboundOrderDetail(page, wrapper);
    }
}
