package com.siirisoft.aim.wms.service.asn;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.siirisoft.aim.wms.entity.asn.WmsErpAsnLine;

/**
 * <p>
 * 送货单行表; InnoDB free: 11264 kB 服务类
 * </p>
 *
 * @author DKY
 * @since 2020-06-08
 */
public interface IWmsErpAsnLineService extends IService<WmsErpAsnLine> {
    IPage queryWmsErpAsnLineList(Page page , Wrapper wrapper);
}
