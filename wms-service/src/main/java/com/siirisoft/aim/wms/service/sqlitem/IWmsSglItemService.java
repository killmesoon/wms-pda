package com.siirisoft.aim.wms.service.sqlitem;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.siirisoft.aim.wms.entity.sqlitem.WmsSglItem;

/**
 * <p>
 * 条码无聊表详情; InnoDB free: 11264 kB 服务类
 * </p>
 *
 * @author DKY
 * @since 2020-06-15
 */
public interface IWmsSglItemService extends IService<WmsSglItem> {
    IPage queryWmsSglItemList(Page page , Wrapper wrapper);
}
