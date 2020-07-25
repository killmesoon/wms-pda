package com.siirisoft.aim.wms.controller.area;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.siirisoft.aim.wms.entity.area.WmsWarehouseArea;
import com.siirisoft.aim.wms.entity.area.ext.WmsWarehouseAreaExt;
import com.siirisoft.aim.wms.entity.data.Result;
import com.siirisoft.aim.wms.entity.data.ResultCode;
import com.siirisoft.aim.wms.entity.warehouse.WmsWarehouse;
import com.siirisoft.aim.wms.service.area.ABWarehouseAreaService;
import com.siirisoft.aim.wms.service.area.IWmsWarehouseAreaService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 区域设置; InnoDB free: 11264 kB; (`warehouse_id`) REFER `wms/wms_warehouse`(`warehou 前端控制器
 * </p>
 *
 * @author DKY
 * @since 2020-05-27
 */
@RestController
@RequestMapping("/web-api/wms/area/wms-warehouse-area")
public class WmsWarehouseAreaController {

    @Autowired
    private IWmsWarehouseAreaService iWmsWarehouseAreaService;

    @Autowired
    private ABWarehouseAreaService abWarehouseAreaService;

    @PostMapping("/queryWarehouseAreaList")
    @ApiOperation(value = "查询区域列表")
    @ApiImplicitParam(name = "wmsWarehouseArea" , value = "区域po")
    public Result queryWarehouseAreaList(@RequestParam(defaultValue = "1") int current,
                                         @RequestParam(defaultValue = "-1") int size,
                                         @RequestBody(required = false) WmsWarehouseArea wmsWarehouseArea) {
        try {
            QueryWrapper wrapper = new QueryWrapper();
            if (wmsWarehouseArea != null) {
                wrapper.eq(wmsWarehouseArea.getPlantCode() != null , "a.plant_code", wmsWarehouseArea.getPlantCode());
                wrapper.eq(wmsWarehouseArea.getWarehouseId() != null , "a.warehouse_id", wmsWarehouseArea.getWarehouseId());
                wrapper.eq(wmsWarehouseArea.getAreaCode() != null , "a.area_code", wmsWarehouseArea.getAreaCode());
                wrapper.eq(wmsWarehouseArea.getAreaName() != null , "a.area_name", wmsWarehouseArea.getAreaName());
                wrapper.eq(wmsWarehouseArea.getAreaType() != null , "a.area_type", wmsWarehouseArea.getAreaType());
                wrapper.eq(wmsWarehouseArea.getEnableFlag() != null , "a.enable_flag", wmsWarehouseArea.getEnableFlag());
                wrapper.eq(wmsWarehouseArea.getDescription() != null , "a.description", wmsWarehouseArea.getDescription());
            }
            wrapper.orderByAsc("a.plant_code");
            wrapper.orderByAsc("c.warehouse_code");
            wrapper.orderByAsc("a.area_code");
            return Result.success(iWmsWarehouseAreaService.findWarehouseAreaList(new Page(current, size), wrapper));
        }
        catch (Exception e) {
            return Result.failure(ResultCode.DATA_IS_WRONG);
        }
    }

    @PostMapping("/saveOrUpdateWarehouseAreaList")
    @ApiOperation(value = "新增或更新区域列表")
    @ApiImplicitParam(name = "wmsWarehouseArea" , value = "区域po")
    public Result saveOrUpdateWarehouseAreaList(@RequestBody WmsWarehouseArea wmsWarehouseArea) {
        if (wmsWarehouseArea.getCreationDate() == null) {
            wmsWarehouseArea.setCreationDate(new Date());
        } else {
            wmsWarehouseArea.setLastUpdateDate(new Date());
            wmsWarehouseArea.setLastUpdateBy(wmsWarehouseArea.getCreatedBy());
        }
        if (iWmsWarehouseAreaService.saveOrUpdate(wmsWarehouseArea)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

    @PostMapping("/deleteWarehouseAreaList")
    @ApiOperation(value = "批量删除区域列表")
    @ApiImplicitParam(name = "areaIdList" , value = "区域ID集合")
    public Result deleteWarehouseAreaList(@RequestBody List<Integer> areaIdList) {
        if (iWmsWarehouseAreaService.removeByIds(areaIdList)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

    @GetMapping("/deleteWarehouseAreaById/{id}")
    @ApiOperation(value = "批量删除区域列表")
    @ApiImplicitParam(name = "id" , value = "区域ID")
    public Result deleteWarehouseAreaById(@PathVariable int id) {
        if (iWmsWarehouseAreaService.removeById(id)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

    @GetMapping("/isWarehouseAreaDelete/{areaId}")
    @ApiOperation(value = "验证区域是否能删除")
    @ApiImplicitParam(name = "areaId", value = "区域Id")
    public Result isWarehouseAreaDelete(@PathVariable int areaId) {
        return Result.success(abWarehouseAreaService.isWarehouseAreaDelete(areaId));
    }


    @PostMapping("/isWarehouseAreaListDelete")
    @ApiOperation(value = "验证区域列表是否能删除")
    @ApiImplicitParam(name = "areaIdList", value = "区域Id")
    public Result isWarehouseAreaListDelete(@RequestBody List<Integer> areaIdList) {
        return Result.success(abWarehouseAreaService.isWarehouseAreaListDelete(areaIdList));
    }


    @PostMapping("/checkAreaCodeExits")
    @ApiOperation(value = "验证区域编码是否存在")
    @ApiImplicitParam(name = "wmsWarehouseArea", value = "区域po")
    public Result checkAreaCodeExits(@RequestBody WmsWarehouseArea wmsWarehouseArea) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("area_code", wmsWarehouseArea.getAreaCode());
        wrapper.eq("warehouse_id", wmsWarehouseArea.getWarehouseId());
        WmsWarehouseArea tmp = iWmsWarehouseAreaService.getById(wmsWarehouseArea.getAreaId());
        if (tmp == null) {
            if (iWmsWarehouseAreaService.count(wrapper) > 0) {
                return Result.success(false);
            }
            return Result.success(true);
        }
        if ((tmp.getAreaCode().equals(wmsWarehouseArea.getAreaCode())) && (tmp.getWarehouseId() == wmsWarehouseArea.getWarehouseId())) {
            return Result.success(true);
        }
        if (iWmsWarehouseAreaService.count(wrapper) > 0) {
            return Result.success(false);
        }
        return Result.success(true);
    }

}
