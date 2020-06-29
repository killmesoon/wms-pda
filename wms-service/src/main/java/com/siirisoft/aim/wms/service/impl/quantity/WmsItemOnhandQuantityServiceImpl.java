package com.siirisoft.aim.wms.service.impl.quantity;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.siirisoft.aim.wms.entity.quantity.WmsItemOnhandQuantity;
import com.siirisoft.aim.wms.mapper.quantity.WmsItemOnhandQuantityMapper;
import com.siirisoft.aim.wms.mapper.quantity.ext.WmsItemOnhandQuantityMapperExt;
import com.siirisoft.aim.wms.service.quantity.IWmsItemOnhandQuantityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 库存现有量; InnoDB free: 11264 kB 服务实现类
 * </p>
 *
 * @author DKY
 * @since 2020-06-05
 */
@Service
public class WmsItemOnhandQuantityServiceImpl extends ServiceImpl<WmsItemOnhandQuantityMapper, WmsItemOnhandQuantity> implements IWmsItemOnhandQuantityService {

    @Autowired
    private WmsItemOnhandQuantityMapperExt wmsItemOnhandQuantityMapperExt;

    @Override
    public IPage queryTotal(Page page, Wrapper wrapper) {
        return wmsItemOnhandQuantityMapperExt.queryTotal(page, wrapper);
    }
}
