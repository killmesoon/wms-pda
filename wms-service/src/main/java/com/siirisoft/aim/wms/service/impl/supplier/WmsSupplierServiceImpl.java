package com.siirisoft.aim.wms.service.impl.supplier;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.siirisoft.aim.wms.entity.supplier.WmsSupplier;
import com.siirisoft.aim.wms.mapper.supplier.WmsSupplierMapper;
import com.siirisoft.aim.wms.mapper.supplier.ext.WmsSupplierMapperExt;
import com.siirisoft.aim.wms.service.supplier.IWmsSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 供应商设置; InnoDB free: 11264 kB 服务实现类
 * </p>
 *
 * @author DKY
 * @since 2020-05-20
 */
@Service
public class WmsSupplierServiceImpl extends ServiceImpl<WmsSupplierMapper, WmsSupplier> implements IWmsSupplierService {

    @Autowired
    private WmsSupplierMapperExt wmsSupplierMapperExt;

    @Override
    public IPage findWmsSupplierListExt(Page page, Wrapper wrapper) {
        return wmsSupplierMapperExt.findWmsSupplierListExt(page, wrapper);
    }
}
