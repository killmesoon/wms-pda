package com.siirisoft.aim.wms.mapper.inbound.ext;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.siirisoft.aim.wms.entity.inbound.ext.WmsInboundOrderLineExt;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 入库单行表; InnoDB free: 11264 kB Mapper 接口
 * </p>
 *
 * @author DKY
 * @since 2020-05-14
 */
public interface WmsInboundOrderLineMapperExt extends BaseMapper<WmsInboundOrderLineExt> {
    IPage<WmsInboundOrderLineExt> wmsInboundLineOrderList(IPage<WmsInboundOrderLineExt> page, @Param(Constants.WRAPPER) Wrapper wrapper);
}
