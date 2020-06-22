package com.siirisoft.aim.wms.service.impl.erp;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.siirisoft.aim.wms.entity.erp.WmsErpPoHead;
import com.siirisoft.aim.wms.mapper.erp.WmsErpPoHeadMapper;
import com.siirisoft.aim.wms.mapper.erp.ext.WmsErpPoHeadMapperExt;
import com.siirisoft.aim.wms.service.erp.IWmsErpPoHeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 采购订单头表; InnoDB free: 11264 kB 服务实现类
 * </p>
 *
 * @author DKY
 * @since 2020-05-27
 */
@Service
public class WmsErpPoHeadServiceImpl extends ServiceImpl<WmsErpPoHeadMapper, WmsErpPoHead> implements IWmsErpPoHeadService {

    @Autowired
    private WmsErpPoHeadMapperExt wmsErpPoHeadMapperExt;

    @Override
    public IPage queryErpPoHeadOrder(Page page, Wrapper wrapper) {
        return wmsErpPoHeadMapperExt.findErpPoHeadOrder(page, wrapper);
    }
}
