package com.siirisoft.aim.wms.service.outbound.pda;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @User DKY
 * @Date 2020/6/22
 * @Description 出库模块PDA service-api
 */
public interface ABPdaWmsOutboundOrderService {
    IPage queryOutboundOrderDetail(Page page, Wrapper wrapper);
}
