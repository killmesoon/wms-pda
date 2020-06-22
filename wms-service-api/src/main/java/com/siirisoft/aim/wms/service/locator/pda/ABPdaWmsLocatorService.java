package com.siirisoft.aim.wms.service.locator.pda;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @User DKY
 * @Date 2020/6/22
 * @Description 货位pda service-api
 */
public interface ABPdaWmsLocatorService {
    IPage queryLocatorDetail(Page page, Wrapper wrapper);
}
