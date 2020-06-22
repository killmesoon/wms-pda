package com.siirisoft.aim.wms.service.impl.asn;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.siirisoft.aim.wms.entity.asn.WmsErpAsnDetail;
import com.siirisoft.aim.wms.mapper.asn.WmsErpAsnDetailMapper;
import com.siirisoft.aim.wms.mapper.asn.ext.WmsErpAsnDetailMapperExt;
import com.siirisoft.aim.wms.service.asn.IWmsErpAsnDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 送货单明细表; InnoDB free: 11264 kB 服务实现类
 * </p>
 *
 * @author DKY
 * @since 2020-06-08
 */
@Service
public class WmsErpAsnDetailServiceImpl extends ServiceImpl<WmsErpAsnDetailMapper, WmsErpAsnDetail> implements IWmsErpAsnDetailService {

    @Autowired
    private WmsErpAsnDetailMapperExt wmsErpAsnDetailMapperExt;

    @Override
    public IPage queryWmsErpAsnDetailListByHeadId(Page page, Wrapper wrapper) {
        return wmsErpAsnDetailMapperExt.findWmsErpAsnDetailByHeadId(page, wrapper);
    }
}
