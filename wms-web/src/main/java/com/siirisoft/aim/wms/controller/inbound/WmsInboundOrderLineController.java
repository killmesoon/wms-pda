package com.siirisoft.aim.wms.controller.inbound;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.siirisoft.aim.wms.entity.data.Result;
import com.siirisoft.aim.wms.entity.data.ResultCode;
import com.siirisoft.aim.wms.entity.inbound.WmsInboundOrderLine;
import com.siirisoft.aim.wms.service.inbound.ABWmsInboundOrderService;
import com.siirisoft.aim.wms.service.inbound.IWmsInboundOrderLineService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 入库单行表; InnoDB free: 11264 kB 前端控制器
 * </p>
 *
 * @author DKY
 * @since 2020-05-14
 */
@RestController
@RequestMapping("/api/wms/inboundLine/wms-inbound-order-line")
public class WmsInboundOrderLineController {

    @Autowired
    private IWmsInboundOrderLineService iWmsInboundOrderLineService;


    @Autowired
    private ABWmsInboundOrderService abWmsInboundOrderService;

    @PostMapping("/findInboundOrderLineList")
    @ApiOperation(value = "查询入库单行列表")
    @ApiImplicitParam(name = "wmsInboundOrderHead", value = "查询条件")
    public Result findInboundOrderList(@RequestParam(defaultValue = "1") int current,
                                       @RequestParam(defaultValue = "-1") int size,
                                       @RequestBody(required = false) WmsInboundOrderLine wmsInboundOrderLine) {
        QueryWrapper<WmsInboundOrderLine> wrapper = new QueryWrapper(wmsInboundOrderLine);
        return Result.success(iWmsInboundOrderLineService.page(new Page<>(current, size), wrapper));
    }

    @GetMapping("/findInboundOrderLineListByHeadId/{headId}")
    @ApiOperation(value = "查询入库单行列表")
    @ApiImplicitParam(name = "wmsInboundOrderHead", value = "查询条件")
    public Result findInboundOrderLineListByHeadId(@PathVariable int headId, @RequestParam(defaultValue = "1") int current, @RequestParam(defaultValue = "-1") int size) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("head_id", headId);
        return Result.success(iWmsInboundOrderLineService.wmsInboundLineOrderList(new Page<>(current, size), wrapper));
    }

    @PostMapping("/insertInboundOrderLineList")
    @ApiOperation(value = "批量插入入库单行信息")
    @ApiImplicitParam(name = "lineList", value = "列表信息")
    public Result insertInboundOrderLineList(@RequestBody List<WmsInboundOrderLine> lineList) {
        if (iWmsInboundOrderLineService.saveOrUpdateBatch(lineList)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

    @PostMapping("/updateInboundOrderLineList")
    @ApiOperation(value = "批量更新入库单行信息")
    @ApiImplicitParam(name = "lineList", value = "列表信息")
    public Result updateInboundOrderLineList(@RequestBody List<WmsInboundOrderLine> lineList) {
        if (iWmsInboundOrderLineService.saveOrUpdateBatch(lineList)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

    @PostMapping
    @ApiOperation(value = "批量删除行信息")
    @ApiImplicitParam(name = "lineList", value = "列表信息")
    public Result deleteInboundOrderLineList(@RequestBody List<Integer> lineList) {
        if (iWmsInboundOrderLineService.removeByIds(lineList)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

    @GetMapping("/deleteOrderLinesByHeadId/{headId}")
    @ApiOperation("根据headId删除关联行信息")
    @ApiImplicitParam(name = "headId", value = "头信息")
    @Transactional
    public Result deleteOrderLinesByHeadId(@PathVariable int headId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("head_id", headId);
        if (iWmsInboundOrderLineService.count(queryWrapper) == 0) {
            return Result.success(ResultCode.SUCCESS);
        } else {
            if (iWmsInboundOrderLineService.remove(queryWrapper)) {
                return Result.success(ResultCode.SUCCESS);
            }
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

    @GetMapping("/deleteLineById/{lineId}")
    @ApiOperation("根据lineId删除关联行信息")
    @ApiImplicitParam(name = "lineId", value = "行id")
    @Transactional
    public Result deleteLineById(@PathVariable int lineId) {
        if (abWmsInboundOrderService.deleteLineAndDetailByLineId(lineId)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

    @PostMapping("/saveOrUpdateOrderLine")
    @ApiOperation("更新或插入行信息")
    @ApiImplicitParam(name = "wmsInboundOrderLine", value = "行信息po")
    @Transactional
    public Result saveOrUpdateOrderLine(@RequestBody WmsInboundOrderLine wmsInboundOrderLine) {
        if (iWmsInboundOrderLineService.saveOrUpdate(wmsInboundOrderLine)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

}
