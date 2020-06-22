package com.siirisoft.aim.wms.mapper.inbound.ext;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * @User DKY
 * @Date 2020/5/26
 * @Description WmsInboundOrderDetailMapper扩展
 */
public interface WmsInboundOrderDetailMapperExt {
    IPage findInboundOrderDetail(Page page, @Param(Constants.WRAPPER)Wrapper wrapper);
}
