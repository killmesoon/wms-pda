package com.siirisoft.aim.wms.service.impl.locator;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.siirisoft.aim.wms.entity.locator.WmsLocator;
import com.siirisoft.aim.wms.entity.locator.ext.WmsLocatorExt;
import com.siirisoft.aim.wms.mapper.locator.WmsLocatorMapper;
import com.siirisoft.aim.wms.mapper.locator.ext.WmsLocatorMapperExt;
import com.siirisoft.aim.wms.service.locator.IWmsLocatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 货位设置; InnoDB free: 11264 kB 服务实现类
 * </p>
 *
 * @author DKY
 * @since 2020-05-26
 */
@Service
public class WmsLocatorServiceImpl extends ServiceImpl<WmsLocatorMapper, WmsLocator> implements IWmsLocatorService {

    @Autowired
    private WmsLocatorMapperExt wmsLocatorMapperExt;

    @Override
    public IPage queryLocatorList(Page page, Wrapper wrapper) {
        IPage locatorList = wmsLocatorMapperExt.findLocatorList(page, wrapper);
        List<WmsLocatorExt> records = locatorList.getRecords();
        for (WmsLocatorExt w : records) {
            w.setLocatorCodeAuto(w.getWarehouseCode() + "-" + w.getAreaCode() + "-" + w.getLocatorCode());
        }
        locatorList.setRecords(records);
        return locatorList;
    }
}
