package com.siirisoft.aim.wms.service.impl.asn;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.siirisoft.aim.wms.entity.asn.WmsErpAsnLine;
import com.siirisoft.aim.wms.mapper.asn.WmsErpAsnLineMapper;
import com.siirisoft.aim.wms.mapper.asn.ext.WmsErpAsnLineMapperExt;
import com.siirisoft.aim.wms.service.asn.IWmsErpAsnLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 送货单行表; InnoDB free: 11264 kB 服务实现类
 * </p>
 *
 * @author DKY
 * @since 2020-06-08
 */
@Service
public class WmsErpAsnLineServiceImpl extends ServiceImpl<WmsErpAsnLineMapper, WmsErpAsnLine> implements IWmsErpAsnLineService {

    @Autowired
    private WmsErpAsnLineMapperExt wmsErpAsnLineMapperExt;

    @Override
    public IPage queryWmsErpAsnLineList(Page page, Wrapper wrapper) {
        return wmsErpAsnLineMapperExt.findWmsErpAsnLineList(page, wrapper);
    }
}
