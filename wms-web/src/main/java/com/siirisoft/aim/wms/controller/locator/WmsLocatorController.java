package com.siirisoft.aim.wms.controller.locator;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.siirisoft.aim.wms.entity.data.Result;
import com.siirisoft.aim.wms.entity.data.ResultCode;
import com.siirisoft.aim.wms.entity.locator.WmsLocator;
import com.siirisoft.aim.wms.entity.locator.ext.WmsLocatorExt;
import com.siirisoft.aim.wms.service.locator.IWmsLocatorService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 货位设置; InnoDB free: 11264 kB 前端控制器
 * </p>
 *
 * @author DKY
 * @since 2020-05-26
 */
@RestController
@RequestMapping("/web-api/wms/locator/wms-locator")
public class WmsLocatorController {

    @Autowired
    private IWmsLocatorService iWmsLocatorService;

    @PostMapping("/queryLocatorList")
    @ApiOperation(value = "查询货位列表")
    @ApiImplicitParam(name = "wmsLocator", value = "货位po")
    public Result queryLocatorList(@RequestParam(defaultValue = "1") int current,
                                   @RequestParam(defaultValue = "-1") int size,
                                   @RequestBody(required = false) WmsLocator wmsLocator) {
        QueryWrapper wrapper = new QueryWrapper();
        if (wmsLocator != null) {
            wrapper.eq(wmsLocator.getPlantId() != null , "a.plant_id", wmsLocator.getPlantId());
            wrapper.eq(wmsLocator.getWarehouseId() != null , "a.warehouse_id", wmsLocator.getWarehouseId());
            wrapper.eq(wmsLocator.getAreaId() != null , "a.area_id", wmsLocator.getAreaId());
            wrapper.eq(wmsLocator.getLocatorCode() != null , "a.locator_code", wmsLocator.getLocatorCode());
            wrapper.eq(wmsLocator.getLocatorName() != null , "a.locator_name", wmsLocator.getLocatorName());
            wrapper.eq(wmsLocator.getLocatorType() != null , "a.locator_type", wmsLocator.getLocatorType());
            wrapper.eq(wmsLocator.getGpsInfo() != null , "a.gps_info", wmsLocator.getGpsInfo());
            wrapper.eq(wmsLocator.getEnableFlag() != null , "a.enable_flag", wmsLocator.getEnableFlag());
            wrapper.eq(wmsLocator.getDescription() != null , "a.description", wmsLocator.getDescription());
        }
        return Result.success(iWmsLocatorService.queryLocatorList(new Page(current, size), wrapper));
    }

    @PostMapping("/saveOrUpdateLocator")
    @ApiOperation(value = "更新或插入货位信息")
    @ApiImplicitParam(name = "wmsLocator", value = "货位po")
    public Result saveOrUpdateLocator(@RequestBody WmsLocator wmsLocator) {
        if (wmsLocator.getCreationDate() != null) {
            wmsLocator.setLastUpdateBy(wmsLocator.getCreatedBy());
            wmsLocator.setLastUpdateDate(new Date());
        } else {
            wmsLocator.setCreationDate(new Date());
        }
        if (iWmsLocatorService.saveOrUpdate(wmsLocator)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

    @PostMapping("/deleteLocatorList")
    @ApiOperation(value = "批量删除")
    @ApiImplicitParam(name = "idList", value = "货位ID列表")
    public Result deleteLocatorList(@RequestBody List<Integer> idList) {
        if (iWmsLocatorService.removeByIds(idList)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }


    @GetMapping("/deleteLocatorById/{id}")
    @ApiOperation(value = "逐条删除")
    @ApiImplicitParam(name = "id", value = "货位ID")
    public Result deleteLocatorById(@PathVariable int id) {
        if (iWmsLocatorService.removeById(id)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

    @PostMapping("/checkLocatorCodeExits")
    @ApiOperation(value = "验证货位编码是否存在")
    @ApiImplicitParam(name = "wmsLocator", value = "货位po")
    public Result checkLocatorCodeExits(@RequestBody WmsLocator wmsLocator) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("locator_code", wmsLocator.getLocatorCode());
        wrapper.eq("area_id", wmsLocator.getAreaId());
        WmsLocator tmp = iWmsLocatorService.getById(wmsLocator.getLocatorId());
        if (tmp == null) {
            if (iWmsLocatorService.count(wrapper) > 0) {
                return Result.success(false);
            }
            return Result.success(true);
        }
        if ((tmp.getAreaId() == wmsLocator.getAreaId()) && (tmp.getLocatorCode().equals(wmsLocator.getLocatorCode()))) {
            return Result.success(true);
        }

        if (iWmsLocatorService.count(wrapper) > 0) {
            return Result.success(false);
        }
        return Result.success(true);
    }

}
