package com.siirisoft.aim.wms.service.barcode;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.siirisoft.aim.wms.entity.barcode.WmsBarcode;
import com.siirisoft.aim.wms.entity.barcode.ext.WmsBarcodeExt;


/**
 * <p>
 * 条码定义表; InnoDB free: 11264 kB 服务类
 * </p>
 *
 * @author DKY
 * @since 2020-05-22
 */
public interface IWmsBarcodeService extends IService<WmsBarcode> {
    IPage<WmsBarcodeExt> findBarcodeList(Page page , Wrapper wrapper);
}
