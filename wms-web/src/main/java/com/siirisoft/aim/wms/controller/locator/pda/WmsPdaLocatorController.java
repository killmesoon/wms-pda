package com.siirisoft.aim.wms.controller.locator.pda;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.siirisoft.aim.wms.entity.area.WmsWarehouseArea;
import com.siirisoft.aim.wms.entity.data.Result;

import com.siirisoft.aim.wms.entity.data.ResultCode;
import com.siirisoft.aim.wms.entity.data.TreeDataWrapper;
import com.siirisoft.aim.wms.entity.locator.WmsLocator;
import com.siirisoft.aim.wms.entity.locator.ext.WmsLocatorExt;
import com.siirisoft.aim.wms.entity.locator.ext.pda.WmsPdaLocatorExt;
import com.siirisoft.aim.wms.entity.warehouse.WmsWarehouse;
import com.siirisoft.aim.wms.service.area.IWmsWarehouseAreaService;
import com.siirisoft.aim.wms.service.locator.IWmsLocatorService;
import com.siirisoft.aim.wms.service.locator.pda.ABPdaWmsLocatorService;
import com.siirisoft.aim.wms.service.warehouse.IWmsWarehouseService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * @User DKY
 * @Date 2020/6/22
 * @Description PDA执行controller
 */
@RestController
@RequestMapping("/m-api/wms/pda/locator")
public class WmsPdaLocatorController {

    @Autowired
    private ABPdaWmsLocatorService wmsLocatorService;

    @Autowired
    private IWmsWarehouseService iWmsWarehouseService;

    @Autowired
    private IWmsLocatorService iWmsLocatorService;

    @Autowired
    private IWmsWarehouseAreaService iWmsWarehouseAreaService;

    @PostMapping("/queryLocatorList")
    @ApiOperation(value = "查询货位列表")
    @ApiImplicitParam(name = "wmsLocator", value = "货位po")
    public Result queryLocatorList(@RequestParam(defaultValue = "1") int current,
                                   @RequestParam(defaultValue = "-1") int size,
                                   @RequestBody(required = false) WmsLocator wmsLocator) {
        QueryWrapper wrapper = new QueryWrapper();
        if (wmsLocator != null) {
            wrapper.eq(wmsLocator.getWarehouseId() != null , "a.warehouse_id", wmsLocator.getWarehouseId());
            wrapper.eq(wmsLocator.getAreaId() != null , "a.area_id", wmsLocator.getAreaId());
            wrapper.eq(wmsLocator.getLocatorCode() != null , "a.locator_code", wmsLocator.getLocatorCode());
            wrapper.eq(wmsLocator.getLocatorName() != null , "a.locator_name", wmsLocator.getLocatorName());
            wrapper.eq(wmsLocator.getLocatorType() != null , "a.locator_type", wmsLocator.getLocatorType());
            wrapper.eq(wmsLocator.getGpsInfo() != null , "a.gps_info", wmsLocator.getGpsInfo());
            wrapper.eq(wmsLocator.getEnableFlag() != null , "a.enable_flag", wmsLocator.getEnableFlag());
            wrapper.eq(wmsLocator.getDescription() != null , "a.description", wmsLocator.getDescription());
        }
//        wrapper.orderByAsc("a.locator_code");
        IPage iPage = iWmsLocatorService.queryLocatorList(new Page(current, size), wrapper);
        List<WmsLocatorExt> records = iPage.getRecords();
        for (WmsLocatorExt m: records) {
            m.setPlantCode("WGQ1");
            m.setPlantName("上海外高桥一厂");
        }
        iPage.setRecords(records);
        return Result.success(iPage);
    }


    @GetMapping("/queryLocatorDetail/{locatorId}")
    @ApiOperation(value = "查询货位详情")
    @ApiImplicitParam(name = "locatorId", value = "货位ID")
    public Result queryLocatorDetail(@RequestParam(defaultValue = "1") int current,
                                     @RequestParam(defaultValue = "-1") int size,
                                     @PathVariable int locatorId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("a.locator_id", locatorId);
        return Result.success(wmsLocatorService.queryLocatorDetail(new Page(current, size), wrapper));
    }

    //生成级联字段
    @GetMapping("/queryWholePosition")
    public Result queryWholePosition() {
        List<WmsWarehouse> warehouses = iWmsWarehouseService.list();
        List<TreeDataWrapper> resultList = new ArrayList<>();
        for (WmsWarehouse w : warehouses) {
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("warehouse_id", w.getWarehouseId());
            List<WmsWarehouseArea> list = iWmsWarehouseAreaService.list(wrapper);
            if (list.size() == 0) {
                continue;
            }
            TreeDataWrapper warehouseTree = new TreeDataWrapper();
            warehouseTree.setWarehouseId(w.getWarehouseId());
            warehouseTree.setCode(w.getWarehouseCode());
            warehouseTree.setName(w.getWarehouseName());
            List<TreeDataWrapper> areaList = new ArrayList<>();
            for (WmsWarehouseArea area : list) {
                QueryWrapper locatorWrapper = new QueryWrapper();
                locatorWrapper.eq("area_id", area.getAreaId());
                List<WmsLocator> lList = iWmsLocatorService.list(locatorWrapper);
                if (lList.size() == 0) {
                    continue;
                }
                TreeDataWrapper areaTree = new TreeDataWrapper();
                areaTree.setName(area.getAreaName());
                areaTree.setAreaId(area.getAreaId());
                areaTree.setCode(area.getAreaCode());
                areaTree.setWarehouseId(area.getWarehouseId());
                List<TreeDataWrapper> locatorList = new ArrayList<>();
                for (WmsLocator locator: lList) {
                    TreeDataWrapper locatorTree = new TreeDataWrapper();
                    locatorTree.setLocatorId(locator.getLocatorId());
                    locatorTree.setCode(locator.getLocatorCode());
                    locatorTree.setName(locator.getLocatorName());
                    locatorTree.setWarehouseId(locator.getWarehouseId());
                    locatorTree.setAreaId(locator.getAreaId());
                    locatorList.add(locatorTree);
                }
                areaTree.setChildren(locatorList);
                areaList.add(areaTree);
            }
            if (areaList.size() == 0) {
                continue;
            }
            warehouseTree.setChildren(areaList);
            resultList.add(warehouseTree);
        }
        return Result.success(resultList);
    }



    @PostMapping("/transformLocator/{locatorId}")
    @ApiOperation(value = "货位移动执行")
    @ApiImplicitParam(name = "locatorId", value = "目标货位id")
    public Result transformLocator(@PathVariable int locatorId, @RequestBody WmsPdaLocatorExt wmsPdaLocatorExt) {
        //参数locatorId是要移动去的货位
        if (wmsLocatorService.transformLocator(locatorId, wmsPdaLocatorExt)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.INTERFACE_INNER_INVOKE_ERROR);
    }

}
