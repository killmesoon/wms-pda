package com.siirisoft.aim.wms.service.events.type;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.siirisoft.aim.wms.entity.events.type.WmsObjectEventType;

/**
 * <p>
 * 事件类型设置; InnoDB free: 11264 kB 服务类
 * </p>
 *
 * @author DKY
 * @since 2020-06-17
 */
public interface IWmsObjectEventTypeService extends IService<WmsObjectEventType> {
    IPage queryObjectEventTypeList(Page page , Wrapper wrapper);
}
