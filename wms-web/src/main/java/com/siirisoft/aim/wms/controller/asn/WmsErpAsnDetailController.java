package com.siirisoft.aim.wms.controller.asn;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.siirisoft.aim.wms.entity.asn.WmsErpAsnDetail;
import com.siirisoft.aim.wms.entity.data.Result;
import com.siirisoft.aim.wms.entity.data.ResultCode;
import com.siirisoft.aim.wms.service.asn.ABWmsAsnOrderService;
import com.siirisoft.aim.wms.service.asn.IWmsErpAsnDetailService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 送货单明细表; InnoDB free: 11264 kB 前端控制器
 * </p>
 *
 * @author DKY
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/web-api/wms/asnDetail/wms-erp-asn-detail")
public class WmsErpAsnDetailController {

    @Autowired
    private IWmsErpAsnDetailService iWmsErpAsnDetailService;

    @Autowired
    private ABWmsAsnOrderService abWmsAsnOrderService;


    @GetMapping("/queryWmsErpAsnDetailByHeadId/{headId}")
    @ApiOperation("根据HeadId查询详情列表")
    @ApiImplicitParam(name = "headId", value = "头ID")
    public Result queryWmsErpAsnDetailByHeadId(@RequestParam(defaultValue = "1") int current,
                                               @RequestParam(defaultValue = "-1") int size,
                                               @PathVariable int headId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("head_id", headId);
        return Result.success(iWmsErpAsnDetailService.queryWmsErpAsnDetailListByHeadId(new Page<>(current, size),wrapper));
    }

    @PostMapping("/queryWmsErpAsnDetailList")
    @ApiOperation("查询详情列表")
    @ApiImplicitParam(name = "wmsErpAsnDetail", value = "详情PO")
    public Result queryWmsErpAsnDetailList(@RequestParam(defaultValue = "1") int current,
                                           @RequestParam(defaultValue = "-1") int size,
                                           @RequestBody(required = false) WmsErpAsnDetail wmsErpAsnDetail) {
        QueryWrapper wrapper = new QueryWrapper();
        if (wmsErpAsnDetail != null) {
            wrapper.eq(wmsErpAsnDetail.getHeadId() != null ,"a.head_id", wmsErpAsnDetail.getHeadId());
            wrapper.eq(wmsErpAsnDetail.getLineId() != null ,"a.line_id", wmsErpAsnDetail.getLineId());
        }
        wrapper.orderByAsc("d_sequence_num");
        return Result.success(iWmsErpAsnDetailService.queryWmsErpAsnDetailListByHeadId(new Page(current, size), wrapper));
    }

    @PostMapping("/saveOrUpdateDetailList/{lineId}")
    @ApiOperation("批量插入明细信息")
    @ApiImplicitParam(name = "list", value = "明细信息list")
    public Result saveOrUpdateDetailList(@PathVariable int lineId,@RequestBody List<WmsErpAsnDetail> list) {
        if (abWmsAsnOrderService.saveWmsErpOrderLineList(lineId ,list)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

    @PostMapping("/saveOrUpdateDetail")
    @ApiOperation("逐条更新")
    @ApiImplicitParam(name = "wmsErpAsnDetail", value = "明细信息po")
    public Result saveOrUpdateDetail(@RequestBody WmsErpAsnDetail wmsErpAsnDetail) {
        if (iWmsErpAsnDetailService.saveOrUpdate(wmsErpAsnDetail)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.PARAM_IS_INVALID);
    }

    @GetMapping("/isdSequenceNumExits/{dSequenceNum}")
    @ApiOperation("校验钢板号是否存在")
    @ApiImplicitParam(name = "dSequenceNum", value = "钢板号")
    public Result isdSequenceNumExits(@PathVariable String dSequenceNum) {
        QueryWrapper wrapper = new QueryWrapper();
        if (iWmsErpAsnDetailService.count(wrapper) > 0) {
            return Result.success(false);
        }
        return Result.success(true);
    }
}
