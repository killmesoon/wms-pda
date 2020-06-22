package com.siirisoft.aim.wms.controller.erp;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.siirisoft.aim.wms.entity.data.Result;
import com.siirisoft.aim.wms.entity.data.ResultCode;
import com.siirisoft.aim.wms.entity.erp.WmsErpPoHead;
import com.siirisoft.aim.wms.service.erp.IWmsErpPoHeadService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 采购订单头表; InnoDB free: 11264 kB 前端控制器
 * </p>
 *
 * @author DKY
 * @since 2020-05-27
 */
@RestController
@RequestMapping("/api/wms/erpHead/wms-erp-po-head")
public class WmsErpPoHeadController {
    @Autowired
    private IWmsErpPoHeadService iWmsErpPoHeadService;

    @PostMapping("queryErpPoHeadOrderList")
    @ApiOperation(value = "查询采购单头表")
    @ApiImplicitParam(name = "wmsErpPoHead", value = "采购单头po")
    public Result queryErpPoHeadOrderList(@RequestParam(defaultValue = "1") int current,
                                          @RequestParam(defaultValue = "-1") int size,
                                          @RequestBody(required = false) WmsErpPoHead wmsErpPoHead) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.orderByDesc("creation_date");
        return Result.success(iWmsErpPoHeadService.queryErpPoHeadOrder(new Page(current, size), wrapper));
    }

    @PostMapping("/saveOrUpdateErpPoHeadOrder")
    @ApiOperation(value = "新增采购单头")
    @ApiImplicitParam(name = "wmsErpPoHead", value = "采购单头po")
    public Result saveOrUpdateErpPoHeadOrder(@RequestBody WmsErpPoHead wmsErpPoHead) {
        if (iWmsErpPoHeadService.saveOrUpdate(wmsErpPoHead)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

    @PostMapping("/deleteErpPoHeadOrderList")
    @ApiOperation(value = "批量删除采购单头")
    @ApiImplicitParam(name = "headIdList", value = "采购单头列表po")
    public Result deleteErpPoHeadOrderList(@RequestBody List<Integer> headIdList) {
        if (iWmsErpPoHeadService.removeByIds(headIdList)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

    @GetMapping("/deleteErpPoHeadOrderById/{id}")
    @ApiOperation(value = "逐条删除采购单头")
    @ApiImplicitParam(name = "id", value = "采购单头ID")
    public Result deleteErpPoHeadOrderById(@PathVariable int id) {
        if (iWmsErpPoHeadService.removeById(id)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }
}
