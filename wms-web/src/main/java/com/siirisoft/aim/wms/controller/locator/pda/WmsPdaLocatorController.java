package com.siirisoft.aim.wms.controller.locator.pda;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.siirisoft.aim.wms.entity.area.WmsWarehouseArea;
import com.siirisoft.aim.wms.entity.data.Result;

import com.siirisoft.aim.wms.entity.warehouse.WmsWarehouse;
import com.siirisoft.aim.wms.service.area.IWmsWarehouseAreaService;
import com.siirisoft.aim.wms.service.locator.IWmsLocatorService;
import com.siirisoft.aim.wms.service.locator.pda.ABPdaWmsLocatorService;
import com.siirisoft.aim.wms.service.warehouse.IWmsWarehouseService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/**
 * @User DKY
 * @Date 2020/6/22
 * @Description PDA执行controller
 */
@RestController
@RequestMapping("/api/wms/pda/locator")
public class WmsPdaLocatorController {

    @Autowired
    private ABPdaWmsLocatorService wmsLocatorService;

    @Autowired
    private IWmsWarehouseService iWmsWarehouseService;

    @Autowired
    private IWmsLocatorService iWmsLocatorService;

    @Autowired
    private IWmsWarehouseAreaService iWmsWarehouseAreaService;


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

        for (WmsWarehouse w : warehouses) {
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("warehouse_id", w.getWarehouseId());
            List<WmsWarehouseArea> list = iWmsWarehouseAreaService.list(wrapper);
            for (WmsWarehouseArea area: list) {
                QueryWrapper qw = new QueryWrapper();
                qw.eq("area_id", area.getAreaId());
                List locatorList = iWmsLocatorService.list(wrapper);
            }
        }

        return Result.success();
    }

}
