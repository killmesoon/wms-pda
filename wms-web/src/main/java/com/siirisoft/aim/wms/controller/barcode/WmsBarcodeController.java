package com.siirisoft.aim.wms.controller.barcode;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.siirisoft.aim.wms.entity.barcode.WmsBarcode;
import com.siirisoft.aim.wms.entity.data.Result;
import com.siirisoft.aim.wms.entity.data.ResultCode;
import com.siirisoft.aim.wms.service.barcode.IWmsBarcodeService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 条码定义表; InnoDB free: 11264 kB 前端控制器
 * </p>
 *
 * @author DKY
 * @since 2020-05-22
 */
@RestController
@RequestMapping("/web-api/wms/barcode/wms-barcode")
public class WmsBarcodeController {

    @Autowired
    private IWmsBarcodeService iWmsBarcodeService;


    @PostMapping("/queryBarcodeList")
    @ApiOperation(value = "wmsBarcode列表查询")
    @ApiImplicitParam(name = "wmsBarcode", value = "条码po")
    public Result queryBarcodeList(@RequestParam(defaultValue = "1") int current ,
                                   @RequestParam(defaultValue = "-1") int size,
                                   @RequestBody(required = false) WmsBarcode wmsBarcode) {
        QueryWrapper wrapper = new QueryWrapper();
        if (wmsBarcode != null) {
            wrapper.eq(wmsBarcode.getBarcode() != null , "a.barcode", wmsBarcode.getBarcode());
            wrapper.eq(wmsBarcode.getBarcodeType() != null , "a.barcode_type", wmsBarcode.getBarcodeType());
            wrapper.eq(wmsBarcode.getBarcodeStatus() != null , "a.barcode_status", wmsBarcode.getBarcodeStatus());
            wrapper.eq(wmsBarcode.getDescription() != null , "a.description", wmsBarcode.getDescription());
            wrapper.eq(wmsBarcode.getSupplierId() != null , "a.supplier_id", wmsBarcode.getSupplierId());
        }
        wrapper.orderByDesc("creation_date");
        return Result.success(iWmsBarcodeService.findBarcodeList(new Page(current,size), wrapper));
    }


    @PostMapping("/saveOrUpdateBarcode")
    @ApiOperation(value = "插入或者更新wmsBarcode")
    @ApiImplicitParam(name = "wmsBarcode", value = "条码po")
    public Result saveOrUpdateBarcode(@RequestBody WmsBarcode wmsBarcode) {
        if (wmsBarcode.getCreationDate() == null) {
            wmsBarcode.setCreationDate(new Date());
        } else {
            wmsBarcode.setLastUpdateDate(new Date());
        }
        if (iWmsBarcodeService.saveOrUpdate(wmsBarcode)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

    @GetMapping("/deleteBarcodeById/{id}")
    @ApiOperation(value = "根据id删除")
    @ApiImplicitParam(name = "id", value = "条码id")
    public Result deleteBarcodeById(@PathVariable int id) {
        if (iWmsBarcodeService.removeById(id)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

    @PostMapping("/deleteBarCodeByIdList")
    @ApiOperation(value = "根据id批量删除")
    @ApiImplicitParam(name = "list", value = "条码id集合")
    public Result deleteBarCodeByIdList(@RequestBody List<Integer> list) {
        if (iWmsBarcodeService.removeByIds(list)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }
}
