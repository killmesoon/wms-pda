package com.siirisoft.aim.wms.service.locator;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.siirisoft.aim.wms.entity.locator.WmsLocator;

/**
 * <p>
 * 货位设置; InnoDB free: 11264 kB 服务类
 * </p>
 *
 * @author DKY
 * @since 2020-05-26
 */
public interface IWmsLocatorService extends IService<WmsLocator> {
    IPage queryLocatorList(Page page , Wrapper wrapper);
}
