package com.siirisoft.aim.wms.mapper.barcode.ext;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.siirisoft.aim.wms.entity.barcode.ext.WmsBarcodeExt;
import org.apache.ibatis.annotations.Param;

/**
 * @User DKY
 * @Date 2020/5/22
 * @Description WmsBarcodeMapper扩展
 */
public interface WmsBarcodeMapperExt {
    IPage<WmsBarcodeExt> findBarcodeList(Page page , @Param(Constants.WRAPPER)Wrapper wrapper);
}
