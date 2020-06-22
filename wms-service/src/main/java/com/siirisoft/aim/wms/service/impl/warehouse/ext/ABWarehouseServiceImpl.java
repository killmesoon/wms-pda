package com.siirisoft.aim.wms.service.impl.warehouse.ext;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.siirisoft.aim.wms.entity.warehouse.WmsWarehouse;
import com.siirisoft.aim.wms.mapper.area.WmsWarehouseAreaMapper;
import com.siirisoft.aim.wms.mapper.inbound.WmsInboundOrderLineMapper;
import com.siirisoft.aim.wms.mapper.locator.WmsLocatorMapper;
import com.siirisoft.aim.wms.mapper.outbound.WmsOutboundOrderLineMapper;
import com.siirisoft.aim.wms.service.warehouse.ABWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @User DKY
 * @Date 2020/6/4
 * @Description ABWarehouseService实现类
 */
@Service
public class ABWarehouseServiceImpl implements ABWarehouseService {

    @Autowired
    private WmsLocatorMapper wmsLocatorMapper;

    @Autowired
    private WmsWarehouseAreaMapper wmsWarehouseAreaMapper;

    @Autowired
    private WmsInboundOrderLineMapper wmsInboundOrderLineMapper;

    @Autowired
    private WmsOutboundOrderLineMapper wmsOutboundOrderLineMapper;

    @Override
    public boolean ifWarehouseDelete(int warehouseId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("warehouse_id", warehouseId);
        if (wmsLocatorMapper.selectCount(wrapper) == 0 && wmsWarehouseAreaMapper.selectCount(wrapper) == 0
                && wmsInboundOrderLineMapper.selectCount(wrapper) == 0 && wmsOutboundOrderLineMapper.selectCount(wrapper) == 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean ifWarehouseListDelete(List<Integer> wmsWarehouseIdList) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.in("warehouse_id", wmsWarehouseIdList );
        if (wmsLocatorMapper.selectCount(wrapper) == 0 && wmsWarehouseAreaMapper.selectCount(wrapper) == 0
                && wmsInboundOrderLineMapper.selectCount(wrapper) == 0 && wmsOutboundOrderLineMapper.selectCount(wrapper) == 0) {
            return true;
        }
        return false;
    }
}
