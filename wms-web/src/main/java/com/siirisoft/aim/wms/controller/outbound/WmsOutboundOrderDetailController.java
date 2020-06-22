package com.siirisoft.aim.wms.controller.outbound;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.siirisoft.aim.wms.entity.data.Result;
import com.siirisoft.aim.wms.entity.outbound.WmsOutboundOrderDetail;
import com.siirisoft.aim.wms.service.outbound.ABWmsOutboundOrderService;
import com.siirisoft.aim.wms.service.outbound.IWmsOutboundOrderDetailService;
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
 * @since 2020-05-15
 */
@RestController
@RequestMapping("/api/wms/outboundDetail/wms-outbound-order-detail")
public class WmsOutboundOrderDetailController {
    @Autowired
    private IWmsOutboundOrderDetailService iWmsOutboundOrderDetailService;

    @Autowired
    private ABWmsOutboundOrderService abWmsOutboundOrderService;

    @GetMapping("/findOutboundOrderDetailListByHeadId/{headId}")
    public Result findOutboundOrderDetailListByHeadId(@PathVariable int headId,
                                                      @RequestParam(defaultValue = "1") int current,
                                                      @RequestParam(defaultValue = "-1") int size) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("a.head_id", headId);
        return Result.success(iWmsOutboundOrderDetailService.queryOutboundOrderDetail(new Page<>(current,size),wrapper));
    }

    @PostMapping("/findOutboundOrderDetailList")
    @ApiOperation(value = "查询详细信息")
    @ApiImplicitParam(name = "wmsOutboundOrderDetail", value = "出库单详情bo")
    public Result findOutboundOrderDetailList(@RequestParam(defaultValue = "1") int current,
                                              @RequestParam(defaultValue = "-1") int size,
                                              @RequestBody WmsOutboundOrderDetail wmsOutboundOrderDetail) {
        QueryWrapper wrapper = new QueryWrapper();
        if (wmsOutboundOrderDetail != null) {
            wrapper.eq("a.head_id", wmsOutboundOrderDetail.getHeadId());
            wrapper.eq("a.line_id", wmsOutboundOrderDetail.getLineId());
        }
        return Result.success(iWmsOutboundOrderDetailService.queryOutboundOrderDetail(new Page(current,size), wrapper));
    }

    @PostMapping("/saveOrUpdateDetailList/{lineId}")
    @ApiOperation("批量插入明细信息")
    @ApiImplicitParam(name = "list", value = "明细信息list")
    public Result saveOrUpdateDetailList(@PathVariable int lineId, @RequestBody List<WmsOutboundOrderDetail> list) {
        return Result.success(abWmsOutboundOrderService.saveWmsInboundOrderDetail(lineId, list));
    }
}
