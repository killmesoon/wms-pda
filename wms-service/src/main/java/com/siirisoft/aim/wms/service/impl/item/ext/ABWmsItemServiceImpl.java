package com.siirisoft.aim.wms.service.impl.item.ext;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.siirisoft.aim.wms.service.asn.IWmsErpAsnLineService;
import com.siirisoft.aim.wms.service.inbound.IWmsInboundOrderLineService;
import com.siirisoft.aim.wms.service.item.ABWmsItemService;
import com.siirisoft.aim.wms.service.outbound.IWmsOutboundOrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @User DKY
 * @Date 2020/7/7
 * @Description ABWmsItemService实现类
 */
@Service
public class ABWmsItemServiceImpl implements ABWmsItemService {

    @Autowired
    private IWmsInboundOrderLineService iWmsInboundOrderLineService;

    @Autowired
    private IWmsOutboundOrderLineService iWmsOutboundOrderLineService;

    @Autowired
    private IWmsErpAsnLineService iWmsErpAsnLineService;

    @Override
    @Transactional
    public boolean checkItemDelete(Integer itemId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("item_id", itemId);
        if (iWmsErpAsnLineService.count(wrapper) >0 || iWmsInboundOrderLineService.count(wrapper) > 0 || iWmsOutboundOrderLineService.count(wrapper) > 0) {
            return false;
        }
        return true;
    }
}
