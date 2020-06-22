package com.siirisoft.aim.wms.controller.inbound;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.siirisoft.aim.wms.entity.data.Result;
import com.siirisoft.aim.wms.entity.inbound.WmsInboundOrderDetail;
import com.siirisoft.aim.wms.service.inbound.ABWmsInboundOrderService;
import com.siirisoft.aim.wms.service.inbound.IWmsInboundOrderDetailService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 入库单明细表; InnoDB free: 11264 kB 前端控制器
 * </p>
 *
 * @author DKY
 * @since 2020-05-14
 */
@RestController
@RequestMapping("/api/wms/inboundDetail/wms-inbound-order-detail")
public class WmsInboundOrderDetailController {

    @Autowired
    private IWmsInboundOrderDetailService iWmsInboundOrderDetailService;

    @Autowired
    private ABWmsInboundOrderService abWmsInboundOrderService;

    @PostMapping("/findInboundOrderDetailList")
    @ApiOperation(value = "查询入库单明细列表")
    @ApiImplicitParam(name = "wmsInboundOrderDetail", value = "查询条件")
    public Result findInboundOrderDetailList(@RequestParam(defaultValue = "1") int current,
                                             @RequestParam(defaultValue = "-1") int size,
                                             @RequestBody(required = false) WmsInboundOrderDetail wmsInboundOrderDetail) {
        QueryWrapper<WmsInboundOrderDetail> wrapper = new QueryWrapper<>(wmsInboundOrderDetail);
        if (wmsInboundOrderDetail != null) {
            wrapper.eq("a.head_id", wmsInboundOrderDetail.getHeadId());
            wrapper.eq("a.line_id", wmsInboundOrderDetail.getLineId());
        }
        return Result.success(iWmsInboundOrderDetailService.queryInboundOrderDetail(new Page<>(current,size),wrapper));
    }

    @GetMapping("/findInboundOrderDetailListByHeadId/{headId}")
    @ApiOperation(value = "根据headId查询明细列表")
    @ApiImplicitParam(name = "headId", value = "入库单头信息")
    public Result findInboundOrderDetailList(@PathVariable int headId , @RequestParam(defaultValue = "1") int current, @RequestParam(defaultValue = "-1") int size) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("a.head_id", headId);
        return Result.success(iWmsInboundOrderDetailService.queryInboundOrderDetail(new Page<>(current,size), wrapper));
    }


    @PostMapping("/saveOrUpdateDetailList/{lineId}")
    @ApiOperation("批量插入明细信息")
    @ApiImplicitParam(name = "list", value = "明细信息list")
    public Result saveOrUpdateDetailList(@PathVariable int lineId, @RequestBody List<WmsInboundOrderDetail> list) {
        return Result.success(abWmsInboundOrderService.saveWmsInboundOrderDetail(lineId, list));
    }

}
