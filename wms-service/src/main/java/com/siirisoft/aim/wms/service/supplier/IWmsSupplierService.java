package com.siirisoft.aim.wms.service.supplier;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.siirisoft.aim.wms.entity.supplier.WmsSupplier;

/**
 * <p>
 * 供应商设置; InnoDB free: 11264 kB 服务类
 * </p>
 *
 * @author DKY
 * @since 2020-05-20
 */
public interface IWmsSupplierService extends IService<WmsSupplier> {
    IPage findWmsSupplierListExt(Page page, Wrapper wrapper);
}
