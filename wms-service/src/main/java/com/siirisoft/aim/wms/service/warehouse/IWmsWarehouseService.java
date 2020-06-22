package com.siirisoft.aim.wms.service.warehouse;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.siirisoft.aim.wms.entity.warehouse.WmsWarehouse;

/**
 * <p>
 * 仓库设置; InnoDB free: 11264 kB 服务类
 * </p>
 *
 * @author DKY
 * @since 2020-05-18
 */
public interface IWmsWarehouseService extends IService<WmsWarehouse> {
    IPage queryWarehouseList(Page page, Wrapper wrapper);
}
