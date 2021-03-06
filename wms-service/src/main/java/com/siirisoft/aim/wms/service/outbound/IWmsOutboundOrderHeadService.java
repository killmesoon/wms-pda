package com.siirisoft.aim.wms.service.outbound;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.siirisoft.aim.wms.entity.outbound.WmsOutboundOrderHead;
import com.siirisoft.aim.wms.entity.outbound.ext.WmsOutboundOrderHeadExt;

/**
 * <p>
 * 出库单头表; InnoDB free: 11264 kB 服务类
 * </p>
 *
 * @author DKY
 * @since 2020-05-15
 */
public interface IWmsOutboundOrderHeadService extends IService<WmsOutboundOrderHead> {
    IPage<WmsOutboundOrderHeadExt> findOutboundOrderList(Page page, Wrapper wrapper);
}
