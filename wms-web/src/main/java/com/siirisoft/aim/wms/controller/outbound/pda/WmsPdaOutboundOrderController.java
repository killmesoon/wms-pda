package com.siirisoft.aim.wms.controller.outbound.pda;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.siirisoft.aim.wms.entity.data.Result;
import com.siirisoft.aim.wms.entity.locator.ext.pda.WmsPdaLocatorExt;
import com.siirisoft.aim.wms.entity.outbound.ext.pda.WmsPdaOutboundOrderDetail;
import com.siirisoft.aim.wms.service.locator.pda.ABPdaWmsLocatorService;
import com.siirisoft.aim.wms.service.outbound.pda.ABPdaWmsOutboundOrderService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @User DKY
 * @Date 2020/6/22
 * @Description 出库执行PDA模块
 */
@RestController
@RequestMapping("/api/wms/pda/outboundOrder")
public class WmsPdaOutboundOrderController {


    @Autowired
    private ABPdaWmsOutboundOrderService abPdaWmsOutboundOrderService;

    @Autowired
    private ABPdaWmsLocatorService wmsLocatorService;

    @GetMapping("/queryOutboundOrderDetail/{headId}")
    @ApiOperation(value = "查询pda服务，出库单接口")
    @ApiImplicitParam(name = "headId", value = "头ID")
    public Result queryOutboundOrderDetail(@RequestParam(defaultValue = "1") int current,
                                           @RequestParam(defaultValue = "-1") int size,
                                           @PathVariable int headId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("a.head_id", headId);
        return Result.success(abPdaWmsOutboundOrderService.queryOutboundOrderDetail(new Page(current,size), wrapper));
    }


    @PostMapping("/preparationExc")
    @ApiOperation(value = "生产备料 备料执行")
    public Result preparation(@RequestParam(defaultValue = "1") int current,
                              @RequestParam(defaultValue = "-1") int size,
                              @RequestBody WmsPdaOutboundOrderDetail wmsPdaOutboundOrderDetail) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("a.locator_id", wmsPdaOutboundOrderDetail.getAdvLocatorId());
        return Result.success(wmsLocatorService.queryLocatorDetail(new Page(current, size), wrapper));
    }


    @PostMapping("/commitPreparation")
    @ApiOperation(value = "备料执行")
    @ApiImplicitParam(name = "wmsPdaLocatorExt", value = "执行单po")
    public Result commitPreparation(@RequestBody WmsPdaLocatorExt wmsPdaLocatorExt) {

        //原货位到新货位，货位移动,不涉及现有量变化
//        abPdaWmsOutboundOrderService

        return Result.success();
    }

}
