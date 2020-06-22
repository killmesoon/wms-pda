package com.siirisoft.aim.wms.service.uom;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.siirisoft.aim.wms.entity.uom.WmsUom;
import com.siirisoft.aim.wms.entity.uom.ext.WmsUomExt;

/**
 * <p>
 * 单位设置; InnoDB free: 11264 kB 服务类
 * </p>
 *
 * @author DKY
 * @since 2020-05-18
 */
public interface IWmsUomService extends IService<WmsUom> {
    IPage<WmsUomExt> findUomList(Page page, Wrapper wrapper);
}
