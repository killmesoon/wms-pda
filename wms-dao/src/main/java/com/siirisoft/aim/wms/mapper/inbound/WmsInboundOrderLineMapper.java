package com.siirisoft.aim.wms.mapper.inbound;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.siirisoft.aim.wms.entity.inbound.WmsInboundOrderLine;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 入库单行表; InnoDB free: 11264 kB Mapper 接口
 * </p>
 *
 * @author DKY
 * @since 2020-05-14
 */
public interface WmsInboundOrderLineMapper extends BaseMapper<WmsInboundOrderLine> {
    @Update("update wms_inbound_order_line set execute_qty=execute_qty + 1 ${ew.customSqlSegment}")
    boolean updateInboundLineInfo(@Param(Constants.WRAPPER) Wrapper wrapper);
}
