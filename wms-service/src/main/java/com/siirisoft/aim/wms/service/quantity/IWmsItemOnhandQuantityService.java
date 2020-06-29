package com.siirisoft.aim.wms.service.quantity;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.siirisoft.aim.wms.entity.quantity.WmsItemOnhandQuantity;

/**
 * <p>
 * 库存现有量; InnoDB free: 11264 kB 服务类
 * </p>
 *
 * @author DKY
 * @since 2020-06-05
 */
public interface IWmsItemOnhandQuantityService extends IService<WmsItemOnhandQuantity> {
    IPage queryTotal(Page page , Wrapper wrapper);
}
