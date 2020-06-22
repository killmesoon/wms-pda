package com.siirisoft.aim.wms.service.impl.area;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.siirisoft.aim.wms.entity.area.WmsWarehouseArea;
import com.siirisoft.aim.wms.mapper.area.WmsWarehouseAreaMapper;
import com.siirisoft.aim.wms.mapper.area.ext.WmsWarehouseAreaMapperExt;
import com.siirisoft.aim.wms.service.area.IWmsWarehouseAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 区域设置; InnoDB free: 11264 kB; (`warehouse_id`) REFER `wms/wms_warehouse`(`warehou 服务实现类
 * </p>
 *
 * @author DKY
 * @since 2020-05-27
 */
@Service
public class WmsWarehouseAreaServiceImpl extends ServiceImpl<WmsWarehouseAreaMapper, WmsWarehouseArea> implements IWmsWarehouseAreaService {

    @Autowired
    private WmsWarehouseAreaMapperExt wmsWarehouseAreaMapperExt;

    @Override
    public IPage findWarehouseAreaList(Page page, Wrapper wrapper) {
        return wmsWarehouseAreaMapperExt.findWarehouseAreaList(page, wrapper);
    }
}
