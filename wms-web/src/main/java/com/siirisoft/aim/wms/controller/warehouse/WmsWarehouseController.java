package com.siirisoft.aim.wms.controller.warehouse;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.siirisoft.aim.wms.entity.data.Result;
import com.siirisoft.aim.wms.entity.data.ResultCode;
import com.siirisoft.aim.wms.entity.warehouse.WmsWarehouse;
import com.siirisoft.aim.wms.entity.warehouse.ext.WmsWarehouseExt;
import com.siirisoft.aim.wms.service.warehouse.ABWarehouseService;
import com.siirisoft.aim.wms.service.warehouse.IWmsWarehouseService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 仓库设置; InnoDB free: 11264 kB 前端控制器
 * </p>
 *
 * @author DKY
 * @since 2020-05-18
 */
@RestController
@RequestMapping("/api/wms/warehouse/wms-warehouse")
public class WmsWarehouseController {

    @Autowired
    private IWmsWarehouseService iWmsWarehouseService;

    @Autowired
    private ABWarehouseService warehouseService;


    @PostMapping("/findWareHouseList")
    @ApiOperation("查询仓库list")
    @ApiImplicitParam(name = "wmsWarehouse", value = "仓库po")
    public Result findWareHouseList(@RequestParam(defaultValue = "1") int current,
                                    @RequestParam(defaultValue = "-1") int size,
                                    @RequestBody(required = false) WmsWarehouse wmsWarehouse) {
        QueryWrapper wrapper = new QueryWrapper(wmsWarehouse);
        if (wmsWarehouse != null) {
            wrapper.eq(wmsWarehouse.getWarehouseType() != null, "warehouse_type", wmsWarehouse.getWarehouseType());
            wrapper.eq(wmsWarehouse.getNegativeFlag() != null, "negative_flag", wmsWarehouse.getNegativeFlag());
            wrapper.eq(wmsWarehouse.getWarehouseCode() != null, "warehouse_code", wmsWarehouse.getWarehouseCode());
            wrapper.eq(wmsWarehouse.getPanrangeFlag() != null, "panrange_flag", wmsWarehouse.getPanrangeFlag());
            wrapper.eq(wmsWarehouse.getWarehouseName() != null, "warehouse_name", wmsWarehouse.getWarehouseName());
            wrapper.eq(wmsWarehouse.getEnableFlag() != null, "enable_flag", wmsWarehouse.getEnableFlag());
            wrapper.eq(wmsWarehouse.getShortName() != null, "short_name", wmsWarehouse.getShortName());
            wrapper.eq(wmsWarehouse.getDescription() != null, "description", wmsWarehouse.getDescription());
        }
        wrapper.orderByAsc("a.warehouse_code");
        IPage iPage = iWmsWarehouseService.queryWarehouseList(new Page<>(current, size), wrapper);
        List<WmsWarehouseExt> records = iPage.getRecords();
        for (WmsWarehouseExt m: records) {
            m.setPlantCode("WGQ1");
            m.setPlantName("上海外高桥一厂");
        }
        iPage.setRecords(records);
        return Result.success(iPage);
    }

    @PostMapping("/saveOrUpdateWarehouse")
    @ApiOperation("新增或更新仓库")
    @ApiImplicitParam(name = "wmsWarehouse", value = "仓库po")
    public Result saveOrUpdateWarehouse(@RequestBody WmsWarehouse wmsWarehouse) {
        QueryWrapper wrapper = new QueryWrapper(wmsWarehouse);
        if (wmsWarehouse.getCreationDate() != null) {
            wmsWarehouse.setLastUpdateDate(new Date());
        } else {
            wmsWarehouse.setCreationDate(new Date());
        }
        if (iWmsWarehouseService.saveOrUpdate(wmsWarehouse,wrapper)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }


    @PostMapping("/deleteWarehouseList")
    @ApiOperation("批量删除")
    @ApiImplicitParam(name = "warehouseIds", value = "id列表")
    public Result deleteWarehouseList(@RequestBody List<Integer> warehouseIds) {
        if (iWmsWarehouseService.removeByIds(warehouseIds)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

    @GetMapping("/deleteWarehouseById/{warehouseId}")
    @ApiOperation("逐条删除")
    @ApiImplicitParam(name = "warehouseId", value = "id")
    public Result deleteWarehouseById (@PathVariable int warehouseId) {
        if (iWmsWarehouseService.removeById(warehouseId)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

    @GetMapping("/isDeleteFlag/{warehouseId}")
    @ApiOperation("单条删除验证")
    @ApiImplicitParam(name = "warehouseId", value = "id")
    public Result isDeleteFlag(@PathVariable int warehouseId) {
        return Result.success(warehouseService.ifWarehouseDelete(warehouseId));
    }


    @PostMapping("/ifWarehouseListDelete")
    @ApiOperation("批量删除验证")
    @ApiImplicitParam(name = "warehouseIdList", value = "id列表")
    public Result ifWarehouseListDelete(@RequestBody List<Integer> warehouseIdList) {
        return Result.success(warehouseService.ifWarehouseListDelete(warehouseIdList));
    }

    @PostMapping("/checkWarehouseCodeExits")
    @ApiOperation("校验仓库编码是否存在")
    @ApiImplicitParam(name = "warehouseIdList", value = "id列表")
    public Result checkWarehouseCodeExits(@RequestBody WmsWarehouse wmsWarehouse) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("warehouse_code", wmsWarehouse.getWarehouseCode());
        if (iWmsWarehouseService.count(wrapper) > 0) {
            return Result.success(false);
        }
        return Result.success(true);
    }

}
