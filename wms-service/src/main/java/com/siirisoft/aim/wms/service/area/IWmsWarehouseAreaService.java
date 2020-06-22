package com.siirisoft.aim.wms.service.area;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.siirisoft.aim.wms.entity.area.WmsWarehouseArea;

/**
 * <p>
 * 区域设置; InnoDB free: 11264 kB; (`warehouse_id`) REFER `wms/wms_warehouse`(`warehou 服务类
 * </p>
 *
 * @author DKY
 * @since 2020-05-27
 */
public interface IWmsWarehouseAreaService extends IService<WmsWarehouseArea> {
    IPage findWarehouseAreaList(Page page, Wrapper wrapper);
}
