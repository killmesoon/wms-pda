package com.siirisoft.aim.wms.service.inbound;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.siirisoft.aim.wms.entity.inbound.WmsInboundOrderLine;
import com.siirisoft.aim.wms.entity.inbound.ext.WmsInboundOrderLineExt;

/**
 * <p>
 * 入库单行表; InnoDB free: 11264 kB 服务类
 * </p>
 *
 * @author DKY
 * @since 2020-05-14
 */
public interface IWmsInboundOrderLineService extends IService<WmsInboundOrderLine> {
    IPage<WmsInboundOrderLineExt> wmsInboundLineOrderList(Page<WmsInboundOrderLineExt> page, QueryWrapper wrapper);
}
