package com.siirisoft.aim.wms.service.impl.barcode;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.siirisoft.aim.wms.entity.barcode.WmsBarcode;
import com.siirisoft.aim.wms.entity.barcode.ext.WmsBarcodeExt;
import com.siirisoft.aim.wms.mapper.barcode.WmsBarcodeMapper;
import com.siirisoft.aim.wms.mapper.barcode.ext.WmsBarcodeMapperExt;
import com.siirisoft.aim.wms.service.barcode.IWmsBarcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 条码定义表; InnoDB free: 11264 kB 服务实现类
 * </p>
 *
 * @author DKY
 * @since 2020-05-22
 */
@Service
public class WmsBarcodeServiceImpl extends ServiceImpl<WmsBarcodeMapper, WmsBarcode> implements IWmsBarcodeService {

    @Autowired
    private WmsBarcodeMapperExt wmsBarcodeMapperExt;

    @Override
    public IPage<WmsBarcodeExt> findBarcodeList(Page page, Wrapper wrapper) {
        return wmsBarcodeMapperExt.findBarcodeList(page, wrapper);
    }
}
