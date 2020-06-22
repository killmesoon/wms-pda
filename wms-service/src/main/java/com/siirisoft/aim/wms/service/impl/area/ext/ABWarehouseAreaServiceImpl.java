package com.siirisoft.aim.wms.service.impl.area.ext;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.siirisoft.aim.wms.mapper.locator.WmsLocatorMapper;
import com.siirisoft.aim.wms.service.area.ABWarehouseAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @User DKY
 * @Date 2020/6/5
 * @Description ABWarehouseAreaService实现类
 */
@Service
public class ABWarehouseAreaServiceImpl implements ABWarehouseAreaService {

    @Autowired
    private WmsLocatorMapper wmsLocatorMapper;

    @Override
    public boolean isWarehouseAreaDelete(int areaId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("area_id", areaId);
        return wmsLocatorMapper.selectCount(wrapper) == 0;
    }

    @Override
    public boolean isWarehouseAreaListDelete(List<Integer> areaIdList) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.in("area_id", areaIdList);
        return wmsLocatorMapper.selectCount(wrapper) == 0;
    }
}
