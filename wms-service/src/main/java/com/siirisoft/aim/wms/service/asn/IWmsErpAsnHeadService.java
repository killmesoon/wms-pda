package com.siirisoft.aim.wms.service.asn;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.siirisoft.aim.wms.entity.asn.WmsErpAsnHead;

/**
 * <p>
 * 送货单头表; InnoDB free: 11264 kB 服务类
 * </p>
 *
 * @author DKY
 * @since 2020-06-08
 */
public interface IWmsErpAsnHeadService extends IService<WmsErpAsnHead> {
    IPage queryWmsErpAsnHeadList(Page page, Wrapper wrapper);
}
