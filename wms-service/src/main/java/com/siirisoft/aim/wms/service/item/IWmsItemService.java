package com.siirisoft.aim.wms.service.item;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.siirisoft.aim.wms.entity.item.WmsItem;

/**
 * <p>
 * 物料主数据; InnoDB free: 11264 kB 服务类
 * </p>
 *
 * @author DKY
 * @since 2020-05-18
 */
public interface IWmsItemService extends IService<WmsItem> {
    IPage findItemAllList(Page page, Wrapper wrapper);
}
