package com.siirisoft.aim.wms.mapper.asn.ext;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.siirisoft.aim.wms.entity.asn.ext.WmsErpAsnLineExt;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @User DKY
 * @Date 2020/6/8
 * @Description WmsErpAsnLineMapper扩展
 */
public interface WmsErpAsnLineMapperExt {
    IPage<WmsErpAsnLineExt> findWmsErpAsnLineList(Page page, @Param(Constants.WRAPPER)Wrapper wrapper);

    @Select("SELECT COALESCE(max(line_num) , 0) FROM wms_erp_asn_line ${ew.customSqlSegment}")
    Integer findMaxLineNumber(@Param(Constants.WRAPPER) Wrapper wrapper);
}
