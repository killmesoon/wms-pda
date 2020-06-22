package com.siirisoft.aim.wms.mapper.events.type.ext;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * @User DKY
 * @Date 2020/6/17
 * @Description WmsObjectEventTypeMapper扩展
 */
public interface WmsObjectEventTypeMapperExt {
    IPage queryWmsObjectEventTypeList(Page page , @Param(Constants.WRAPPER)Wrapper wrapper);
}
