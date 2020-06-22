package com.siirisoft.aim.wms.service.impl.warehouse;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.siirisoft.aim.wms.entity.warehouse.WmsWarehouse;
import com.siirisoft.aim.wms.mapper.warehouse.WmsWarehouseMapper;
import com.siirisoft.aim.wms.mapper.warehouse.ext.WmsWarehouseMapperExt;
import com.siirisoft.aim.wms.service.warehouse.IWmsWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 仓库设置; InnoDB free: 11264 kB 服务实现类
 * </p>
 *
 * @author DKY
 * @since 2020-05-18
 */
@Service
public class WmsWarehouseServiceImpl extends ServiceImpl<WmsWarehouseMapper, WmsWarehouse> implements IWmsWarehouseService {
    @Autowired
    private WmsWarehouseMapperExt wmsWarehouseMapperExt;


    @Override
    public IPage queryWarehouseList(Page page, Wrapper wrapper) {
        return wmsWarehouseMapperExt.findWarehouseList(page,wrapper);
    }
}
