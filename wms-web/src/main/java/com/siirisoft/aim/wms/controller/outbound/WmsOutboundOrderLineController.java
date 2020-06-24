package com.siirisoft.aim.wms.controller.outbound;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.siirisoft.aim.wms.entity.data.Result;
import com.siirisoft.aim.wms.entity.data.ResultCode;
import com.siirisoft.aim.wms.entity.outbound.WmsOutboundOrderLine;
import com.siirisoft.aim.wms.service.outbound.ABWmsOutboundOrderService;
import com.siirisoft.aim.wms.service.outbound.IWmsOutboundOrderLineService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 出库单行表; InnoDB free: 11264 kB 前端控制器
 * </p>
 *
 * @author DKY
 * @since 2020-05-15
 */
@RestController
@RequestMapping("/web-api/wms/outboundLine/wms-outbound-order-line")
public class WmsOutboundOrderLineController {

    @Autowired
    private IWmsOutboundOrderLineService iWmsOutboundOrderLineService;

    @Autowired
    private ABWmsOutboundOrderService abWmsOutboundOrderService;

    @PostMapping("/findOutboundOrderLineList")
    @ApiOperation(value = "出库单行查询")
    @ApiImplicitParam(name = "wmsOutboundOrderLine", value = "入库单行表")
    public Result findOutboundOrderLineList(@RequestParam(defaultValue = "1") int current,
                                            @RequestParam(defaultValue = "-1") int size,
                                            @RequestBody(required = false) WmsOutboundOrderLine wmsOutboundOrderLine) {
        QueryWrapper<WmsOutboundOrderLine> wrapper = new QueryWrapper<>(wmsOutboundOrderLine);
        return Result.success(iWmsOutboundOrderLineService.page(new Page<>(current,size),wrapper));
    }

    @GetMapping("/findOutboundOrderLineListByHeadId/{headId}")
    @ApiOperation(value = "出库单行查询")
    @ApiImplicitParam(name = "headId", value = "入库单headId")
    public Result findOutboundOrderLineListByHeadId(@PathVariable int headId, @RequestParam(defaultValue = "1") int current, @RequestParam(defaultValue = "-1") int size) {
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("head_id",headId);
        return Result.success(iWmsOutboundOrderLineService.findWmsOutboundOrderLineListExt(new Page<>(current,size),wrapper));
    }


    @PostMapping("/saveOrUpdateOutboundOrderLineList")
    @ApiOperation(value = "出库单行信息批量插入")
    @ApiImplicitParam(name = "lines", value = "出库单行表list")
    public Result saveOrUpdateOutboundOrderLineList(@RequestBody List<WmsOutboundOrderLine> lines) {
        if (iWmsOutboundOrderLineService.saveOrUpdateBatch(lines)) {
         return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

    @PostMapping("/saveOrUpdateOutboundOrderLine")
    @ApiOperation(value = "出库单行信息单条插入")
    @ApiImplicitParam(name = "wmsOutboundOrderLine", value = "出库单po")
    public Result saveOrUpdateOutboundOrderLine(@RequestBody WmsOutboundOrderLine wmsOutboundOrderLine) {
        if (iWmsOutboundOrderLineService.saveOrUpdate(wmsOutboundOrderLine)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }


    @PostMapping("/deleteOutboundOrderLineList")
    @ApiOperation(value = "出库单行信息批量删除")
    @ApiImplicitParam(name = "lines", value = "出库单行表lineIdList")
    public Result deleteOutboundOrderLineList(@RequestBody List<Integer> lines) {
        if (abWmsOutboundOrderService.deleteLineAndDetail(lines)) {
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
        if (iWmsOutboundOrderLineService.count(queryWrapper) == 0) {
            return Result.success(ResultCode.SUCCESS);
        } else {
            if (iWmsOutboundOrderLineService.remove(queryWrapper)) {
                return Result.success(ResultCode.SUCCESS);
            }
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

    @GetMapping("/deleteOutboundLineById/{lineId}")
    @ApiOperation("根据lineId删除关联行信息")
    @ApiImplicitParam(name = "lineId", value = "行id")
    public Result deleteOutboundLineById(@PathVariable int lineId) {
        if (abWmsOutboundOrderService.deleteLineAndDetail(lineId)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }
}
