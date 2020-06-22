package com.siirisoft.aim.wms.service.impl.asn;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.siirisoft.aim.wms.entity.asn.WmsErpAsnHead;
import com.siirisoft.aim.wms.mapper.asn.WmsErpAsnHeadMapper;
import com.siirisoft.aim.wms.mapper.asn.ext.WmsErpAsnHeadMapperExt;
import com.siirisoft.aim.wms.service.asn.IWmsErpAsnHeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 送货单头表; InnoDB free: 11264 kB 服务实现类
 * </p>
 *
 * @author DKY
 * @since 2020-06-08
 */
@Service
public class WmsErpAsnHeadServiceImpl extends ServiceImpl<WmsErpAsnHeadMapper, WmsErpAsnHead> implements IWmsErpAsnHeadService {

    @Autowired
    private WmsErpAsnHeadMapperExt wmsErpAsnHeadMapperExt;

    @Override
    public IPage queryWmsErpAsnHeadList(Page page, Wrapper wrapper) {
        return wmsErpAsnHeadMapperExt.findWmsErpAsnHeadList(page, wrapper);
    }
}
