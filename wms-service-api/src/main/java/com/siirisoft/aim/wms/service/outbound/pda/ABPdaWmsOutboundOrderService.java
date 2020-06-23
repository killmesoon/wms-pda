package com.siirisoft.aim.wms.service.outbound.pda;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.siirisoft.aim.wms.entity.locator.ext.WmsLocatorExt;
import com.siirisoft.aim.wms.entity.locator.ext.pda.WmsPdaLocatorExt;
import com.siirisoft.aim.wms.entity.outbound.ext.pda.WmsOutboundCondition;
import com.siirisoft.aim.wms.entity.outbound.ext.pda.WmsPdaOutboundOrderDetail;

import java.util.List;

/**
 * @User DKY
 * @Date 2020/6/22
 * @Description 出库模块PDA service-api
 */
public interface ABPdaWmsOutboundOrderService {
    IPage queryOutboundOrderDetail(Page page, Wrapper wrapper);

    boolean commitPreparation(WmsOutboundCondition wmsOutboundCondition);

    boolean outboundOrderExc(List<WmsPdaOutboundOrderDetail> detailList);
}
