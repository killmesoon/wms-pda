package com.siirisoft.aim.wms.service.erp;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.siirisoft.aim.wms.entity.erp.WmsErpPoHead;

/**
 * <p>
 * 采购订单头表; InnoDB free: 11264 kB 服务类
 * </p>
 *
 * @author DKY
 * @since 2020-05-27
 */
public interface IWmsErpPoHeadService extends IService<WmsErpPoHead> {
    IPage queryErpPoHeadOrder(Page page, Wrapper wrapper);
}
