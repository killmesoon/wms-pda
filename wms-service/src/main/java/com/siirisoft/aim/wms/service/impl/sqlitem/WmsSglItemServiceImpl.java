package com.siirisoft.aim.wms.service.impl.sqlitem;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.siirisoft.aim.wms.entity.sqlitem.WmsSglItem;
import com.siirisoft.aim.wms.mapper.sqlitem.WmsSglItemMapper;
import com.siirisoft.aim.wms.mapper.sqlitem.ext.WmsSglItemMapperExt;
import com.siirisoft.aim.wms.service.sqlitem.IWmsSglItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 条码无聊表详情; InnoDB free: 11264 kB 服务实现类
 * </p>
 *
 * @author DKY
 * @since 2020-06-15
 */
@Service
public class WmsSglItemServiceImpl extends ServiceImpl<WmsSglItemMapper, WmsSglItem> implements IWmsSglItemService {

    @Autowired
    private WmsSglItemMapperExt wmsSglItemMapperExt;

    @Override
    public IPage queryWmsSglItemList(Page page, Wrapper wrapper) {
        return wmsSglItemMapperExt.findWmsSglItemList(page, wrapper);
    }
}
