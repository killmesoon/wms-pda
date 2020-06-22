package com.siirisoft.aim.wms.service.impl.locator.ext.pda;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.siirisoft.aim.wms.mapper.locator.ext.pda.WmsPdaLocatorMapperExt;
import com.siirisoft.aim.wms.service.locator.pda.ABPdaWmsLocatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @User DKY
 * @Date 2020/6/22
 * @Description ABPdaWmsLocatorService实现类
 */
@Service
public class ABPdaWmsLocatorServiceImpl implements ABPdaWmsLocatorService {

    @Autowired
    private WmsPdaLocatorMapperExt wmsPdaLocatorMapperExt;

    @Override
    public IPage queryLocatorDetail(Page page, Wrapper wrapper) {
        return wmsPdaLocatorMapperExt.queryLocatorDetail(page, wrapper);
    }
}
