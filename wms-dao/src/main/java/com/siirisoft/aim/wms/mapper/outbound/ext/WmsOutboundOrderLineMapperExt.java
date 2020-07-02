package com.siirisoft.aim.wms.mapper.outbound.ext;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.siirisoft.aim.wms.entity.outbound.ext.WmsOutboundOrderLineExt;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @User DKY
 * @Date 2020/5/20
 * @Description WmsOutboundOrderLineMapper扩展
 */
public interface WmsOutboundOrderLineMapperExt extends BaseMapper<WmsOutboundOrderLineExt> {
    IPage<WmsOutboundOrderLineExt> findWmsOutboundOrderLineListExt(IPage page, @Param(Constants.WRAPPER) Wrapper wrapper);

    @Select("SELECT max(line_num) FROM wms_outbound_order_line ${ew.customSqlSegment}")
    Integer getMaxLineNumber(@Param(Constants.WRAPPER) Wrapper wrapper);
}
