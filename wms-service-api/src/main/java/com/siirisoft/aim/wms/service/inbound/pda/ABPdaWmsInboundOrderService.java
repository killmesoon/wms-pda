package com.siirisoft.aim.wms.service.inbound.pda;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.siirisoft.aim.wms.entity.inbound.ext.pda.WmsPdaInboundOrderDetail;

/**
 * @User DKY
 * @Date 2020/6/16
 * @Description PDA端的service-api
 */
public interface ABPdaWmsInboundOrderService {
    IPage queryInboundOrderDetail(Page page, Wrapper wrapper);

    boolean commitInboundOrder(WmsPdaInboundOrderDetail wmsPdaInboundOrderDetail);
}
