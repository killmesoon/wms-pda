package com.siirisoft.aim.wms.service.impl.inbound;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.siirisoft.aim.wms.entity.inbound.WmsInboundOrderLine;
import com.siirisoft.aim.wms.entity.inbound.ext.WmsInboundOrderLineExt;
import com.siirisoft.aim.wms.mapper.inbound.WmsInboundOrderLineMapper;
import com.siirisoft.aim.wms.mapper.inbound.ext.WmsInboundOrderLineMapperExt;
import com.siirisoft.aim.wms.service.inbound.IWmsInboundOrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 入库单行表; InnoDB free: 11264 kB 服务实现类
 * </p>
 *
 * @author DKY
 * @since 2020-05-14
 */
@Service
public class WmsInboundOrderLineServiceImpl extends ServiceImpl<WmsInboundOrderLineMapper, WmsInboundOrderLine> implements IWmsInboundOrderLineService {

    @Autowired
    private WmsInboundOrderLineMapperExt wmsInboundOrderLineMapperExt;

    @Override
    public IPage<WmsInboundOrderLineExt> wmsInboundLineOrderList(Page<WmsInboundOrderLineExt> page, QueryWrapper wrapper) {
        return wmsInboundOrderLineMapperExt.wmsInboundLineOrderList(page, wrapper);
    }
}
