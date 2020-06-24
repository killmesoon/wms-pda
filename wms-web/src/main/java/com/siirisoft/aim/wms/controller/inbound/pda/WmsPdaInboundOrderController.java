package com.siirisoft.aim.wms.controller.inbound.pda;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.siirisoft.aim.wms.entity.data.Result;
import com.siirisoft.aim.wms.entity.inbound.ext.pda.WmsPdaInboundOrderDetail;
import com.siirisoft.aim.wms.service.inbound.pda.ABPdaWmsInboundOrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @User DKY
 * @Date 2020/6/16
 * @Description 入库单PDA模块功能
 */
@RestController
@RequestMapping("/m-api/wms/pda/inboundOrder")
public class WmsPdaInboundOrderController {

    @Autowired
    private ABPdaWmsInboundOrderService abPdaWmsInboundOrderService;

    @GetMapping("/queryInboundOrderDetail/{headId}")
    @ApiOperation(value = "入库单模块")
    public Result queryInboundOrderDetail(@RequestParam(defaultValue = "1") int current,
                                          @RequestParam(defaultValue = "-1") int size,
                                          @PathVariable int headId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("a.head_id", headId);
        //通过ID查询具体信息
        return Result.success(abPdaWmsInboundOrderService.queryInboundOrderDetail(new Page(current,size), wrapper));
    }

    @PostMapping("/commitInboundOrder")
    @ApiOperation("提交入库单请求")
    public Result commitInboundOrder(@RequestBody WmsPdaInboundOrderDetail wmsPdaInboundOrderDetail) {
        return Result.success(abPdaWmsInboundOrderService.commitInboundOrder(wmsPdaInboundOrderDetail));
    }
}
