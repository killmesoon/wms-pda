package com.siirisoft.aim.wms.service.inbound;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.siirisoft.aim.wms.entity.inbound.WmsInboundOrderDetail;

/**
 * <p>
 * 入库单明细表; InnoDB free: 11264 kB 服务类
 * </p>
 *
 * @author DKY
 * @since 2020-05-14
 */
public interface IWmsInboundOrderDetailService extends IService<WmsInboundOrderDetail> {
    IPage queryInboundOrderDetail(Page page, Wrapper wrapper);
}
