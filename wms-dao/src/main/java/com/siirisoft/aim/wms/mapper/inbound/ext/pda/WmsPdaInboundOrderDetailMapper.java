package com.siirisoft.aim.wms.mapper.inbound.ext.pda;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * @User DKY
 * @Date 2020/6/17
 * @Description 入库单pda服务mapper
 */
public interface WmsPdaInboundOrderDetailMapper {
    IPage queryInboundOrderDetail(Page page , @Param(Constants.WRAPPER)Wrapper wrapper);
}
