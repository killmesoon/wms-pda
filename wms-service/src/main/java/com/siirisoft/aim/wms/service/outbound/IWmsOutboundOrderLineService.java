package com.siirisoft.aim.wms.service.outbound;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.siirisoft.aim.wms.entity.outbound.WmsOutboundOrderLine;
import com.siirisoft.aim.wms.entity.outbound.ext.WmsOutboundOrderLineExt;

/**
 * <p>
 * 出库单行表; InnoDB free: 11264 kB 服务类
 * </p>
 *
 * @author DKY
 * @since 2020-05-15
 */
public interface IWmsOutboundOrderLineService extends IService<WmsOutboundOrderLine> {
    IPage<WmsOutboundOrderLineExt> findWmsOutboundOrderLineListExt(Page page, Wrapper wrapper);
}
