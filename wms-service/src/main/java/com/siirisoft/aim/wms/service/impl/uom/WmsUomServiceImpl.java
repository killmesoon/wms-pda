package com.siirisoft.aim.wms.service.impl.uom;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.siirisoft.aim.wms.entity.uom.WmsUom;
import com.siirisoft.aim.wms.entity.uom.ext.WmsUomExt;
import com.siirisoft.aim.wms.mapper.uom.WmsUomMapper;
import com.siirisoft.aim.wms.mapper.uom.ext.WmsUomMapperExt;
import com.siirisoft.aim.wms.service.uom.IWmsUomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 单位设置; InnoDB free: 11264 kB 服务实现类
 * </p>
 *
 * @author DKY
 * @since 2020-05-18
 */
@Service
public class WmsUomServiceImpl extends ServiceImpl<WmsUomMapper, WmsUom> implements IWmsUomService {

    @Autowired
    private WmsUomMapperExt wmsUomMapperExt;

    @Override
    public IPage<WmsUomExt> findUomList(Page page, Wrapper wrapper) {
        return wmsUomMapperExt.findUomList(page, wrapper);
    }
}
