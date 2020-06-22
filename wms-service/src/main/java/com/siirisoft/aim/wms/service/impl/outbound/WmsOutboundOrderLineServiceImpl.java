package com.siirisoft.aim.wms.service.impl.outbound;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.siirisoft.aim.wms.entity.outbound.WmsOutboundOrderLine;
import com.siirisoft.aim.wms.entity.outbound.ext.WmsOutboundOrderLineExt;
import com.siirisoft.aim.wms.mapper.outbound.WmsOutboundOrderLineMapper;
import com.siirisoft.aim.wms.mapper.outbound.ext.WmsOutboundOrderLineMapperExt;
import com.siirisoft.aim.wms.service.outbound.IWmsOutboundOrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 出库单行表; InnoDB free: 11264 kB 服务实现类
 * </p>
 *
 * @author DKY
 * @since 2020-05-15
 */
@Service
public class WmsOutboundOrderLineServiceImpl extends ServiceImpl<WmsOutboundOrderLineMapper, WmsOutboundOrderLine> implements IWmsOutboundOrderLineService {

    @Autowired
    private WmsOutboundOrderLineMapperExt wmsOutboundOrderLineMapperExt;

    @Override
    public IPage<WmsOutboundOrderLineExt> findWmsOutboundOrderLineListExt(Page page, Wrapper wrapper) {
        return wmsOutboundOrderLineMapperExt.findWmsOutboundOrderLineListExt(page, wrapper);
    }
}
