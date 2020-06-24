package com.siirisoft.aim.wms.controller.supplier;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.siirisoft.aim.wms.entity.data.Result;
import com.siirisoft.aim.wms.entity.data.ResultCode;
import com.siirisoft.aim.wms.entity.supplier.WmsSupplier;
import com.siirisoft.aim.wms.service.supplier.IWmsSupplierService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 供应商设置; InnoDB free: 11264 kB 前端控制器
 * </p>
 *
 * @author DKY
 * @since 2020-05-20
 */
@RestController
@RequestMapping("/web-api/wms/supplier/wms-supplier")
public class WmsSupplierController {

    @Autowired
    private IWmsSupplierService iWmsSupplierService;


    @PostMapping("/findSupplierList")
    @ApiOperation(value = "查询供应商列表")
    @ApiImplicitParam(name = "wmsSupplier", value = "供应商po")
    public Result findSupplierList(@RequestParam(defaultValue = "1") int current,
                                   @RequestParam(defaultValue = "-1") int size,
                                   @RequestBody(required = false) WmsSupplier wmsSupplier) {
        QueryWrapper wrapper = new QueryWrapper<>();
        if (wmsSupplier != null) {
            wrapper.eq(wmsSupplier.getSupplierCode() != null, "a.supplier_code", wmsSupplier.getSupplierCode());
            wrapper.eq(wmsSupplier.getSupplierName() != null, "a.supplier_name", wmsSupplier.getSupplierName());
            wrapper.eq(wmsSupplier.getSupplierStatus() != null, "a.supplier_status", wmsSupplier.getSupplierStatus());
            wrapper.eq(wmsSupplier.getSupplierType() != null, "a.supplier_type", wmsSupplier.getSupplierType());
            wrapper.eq(wmsSupplier.getShortName() != null, "a.short_name", wmsSupplier.getShortName());
        }
        wrapper.orderByDesc("creation_date");
        return Result.success(iWmsSupplierService.findWmsSupplierListExt(new Page<>(current, size), wrapper));
    }



    @GetMapping("/deleteSupplierById/{supplierId}")
    @ApiOperation("单个删除供应商")
    @ApiImplicitParam(name = "supplierId", value = "供应商ID")
    public Result deleteSupplierById(@PathVariable int supplierId) {
        if (iWmsSupplierService.removeById(supplierId)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }


    @PostMapping("/saveOrUpdateSupplier")
    @ApiOperation(value = "保存或更新供应商")
    @ApiImplicitParam(name = "wmsSupplier", value = "供应商po")
    public Result saveOrUpdateSupplier(@RequestBody WmsSupplier wmsSupplier) {
        if (wmsSupplier.getCreationDate() == null) {
            wmsSupplier.setCreationDate(new Date());
        } else {
            wmsSupplier.setLastUpdateDate(new Date());
        }
        if (iWmsSupplierService.saveOrUpdate(wmsSupplier)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

    @PostMapping("/deleteSupplierList")
    @ApiOperation(value = "批量删除供应商")
    @ApiImplicitParam(name = "supplierIdList", value = "供应商ID列表")
    public Result deleteSupplierList(@RequestBody List<Integer> supplierIdList) {
        if (iWmsSupplierService.removeByIds(supplierIdList)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

    @PostMapping("/checkSupplierExits")
    @ApiOperation(value = "校验供应商编码是否重复")
    @ApiImplicitParam(name = "wmsSupplier", value = "供应商po")
    public Result checkSupplierExits(@RequestBody WmsSupplier wmsSupplier) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("supplier_code", wmsSupplier.getSupplierCode());
        if (iWmsSupplierService.count(wrapper) > 0) {
            return Result.success(false);
        }
        return Result.success(true);
    }

}
