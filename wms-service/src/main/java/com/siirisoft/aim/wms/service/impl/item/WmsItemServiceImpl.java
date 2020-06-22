package com.siirisoft.aim.wms.service.impl.item;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.siirisoft.aim.wms.entity.item.WmsItem;
import com.siirisoft.aim.wms.mapper.item.WmsItemMapper;
import com.siirisoft.aim.wms.mapper.item.ext.WmsItemMapperExt;
import com.siirisoft.aim.wms.service.item.IWmsItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 物料主数据; InnoDB free: 11264 kB 服务实现类
 * </p>
 *
 * @author DKY
 * @since 2020-05-18
 */
@Service
public class WmsItemServiceImpl extends ServiceImpl<WmsItemMapper, WmsItem> implements IWmsItemService {

    @Autowired
    private WmsItemMapperExt wmsItemMapperExt;

    @Override
    public IPage findItemAllList(Page page, Wrapper wrapper) {
        return wmsItemMapperExt.findItemAllList(page, wrapper);
    }
}
