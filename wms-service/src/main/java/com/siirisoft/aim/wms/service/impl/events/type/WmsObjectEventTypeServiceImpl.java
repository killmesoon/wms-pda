package com.siirisoft.aim.wms.service.impl.events.type;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.siirisoft.aim.wms.entity.events.type.WmsObjectEventType;
import com.siirisoft.aim.wms.mapper.events.type.WmsObjectEventTypeMapper;
import com.siirisoft.aim.wms.mapper.events.type.ext.WmsObjectEventTypeMapperExt;
import com.siirisoft.aim.wms.service.events.type.IWmsObjectEventTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 事件类型设置; InnoDB free: 11264 kB 服务实现类
 * </p>
 *
 * @author DKY
 * @since 2020-06-17
 */
@Service
public class WmsObjectEventTypeServiceImpl extends ServiceImpl<WmsObjectEventTypeMapper, WmsObjectEventType> implements IWmsObjectEventTypeService {

    @Autowired
    private WmsObjectEventTypeMapperExt wmsObjectEventTypeMapperExt;

    @Override
    public IPage queryObjectEventTypeList(Page page, Wrapper wrapper) {
        return wmsObjectEventTypeMapperExt.queryWmsObjectEventTypeList(page, wrapper);
    }
}
