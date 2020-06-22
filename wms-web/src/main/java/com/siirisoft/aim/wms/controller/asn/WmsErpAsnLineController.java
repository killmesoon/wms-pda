package com.siirisoft.aim.wms.controller.asn;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.siirisoft.aim.wms.entity.asn.WmsErpAsnLine;
import com.siirisoft.aim.wms.entity.data.Result;
import com.siirisoft.aim.wms.entity.data.ResultCode;
import com.siirisoft.aim.wms.service.asn.ABWmsAsnOrderService;
import com.siirisoft.aim.wms.service.asn.IWmsErpAsnLineService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 送货单行表; InnoDB free: 11264 kB 前端控制器
 * </p>
 *
 * @author DKY
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/api/wms/asnLine/wms-erp-asn-line")
public class WmsErpAsnLineController {

    @Autowired
    private IWmsErpAsnLineService iWmsErpAsnLineService;

    @Autowired
    private ABWmsAsnOrderService abWmsAsnOrderService;

    @GetMapping("/queryWmsErpAsnLineListByHeadId/{headId}")
    @ApiOperation(value = "根据HeadId查询关联行信息")
    @ApiImplicitParam(name = "headId", value = "头信息ID")
    public Result queryWmsErpAsnLineListByHeadId(@RequestParam(defaultValue = "1") int current,
                                                 @RequestParam(defaultValue = "-1") int size,
                                                 @PathVariable int headId) {
        QueryWrapper wrapper= new QueryWrapper();
        wrapper.eq("a.head_id", headId);
        return Result.success(iWmsErpAsnLineService.queryWmsErpAsnLineList(new Page(current, size),wrapper));
    }

    @PostMapping("/saveOrUpdateWmsErpAsnLine")
    @ApiOperation(value = "更新行信息")
    @ApiImplicitParam(name = "wmsErpAsnLine", value = "行信息po")
    public Result saveOrUpdateWmsErpAsnLine(@RequestBody WmsErpAsnLine wmsErpAsnLine) {
        if (iWmsErpAsnLineService.saveOrUpdate(wmsErpAsnLine)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

    @PostMapping("saveOrUpdateWmsErpAsnLineList")
    @ApiOperation(value = "批量更新行信息")
    @ApiImplicitParam(name = "lineList", value = "行信息列表")
    public Result saveOrUpdateWmsErpAsnLineList(@RequestBody List<WmsErpAsnLine> lineList) {
        if (iWmsErpAsnLineService.saveOrUpdateBatch(lineList)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

    @PostMapping("/saveOrUpdateWmsErpAsnLineByHeadId")
    @ApiOperation(value = "根据headId更新行信息")
    @ApiImplicitParam(name = "wmsErpAsnLine", value = "行信息po")
    public Result saveOrUpdateWmsErpAsnLineByHeadId(@RequestBody WmsErpAsnLine wmsErpAsnLine) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("head_id", wmsErpAsnLine.getHeadId());
        if (iWmsErpAsnLineService.saveOrUpdate(wmsErpAsnLine, wrapper)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }


    @PostMapping("/deleteWmsErpAsnLineList")
    @ApiOperation(value = "批量删除行信息")
    @ApiImplicitParam(name = "lineIdList", value = "行信息ID列表")
    public Result deleteWmsErpAsnLineList(@RequestBody List<Integer> lineIdList) {
        if (abWmsAsnOrderService.deleteLineOrderAndDetailList(lineIdList)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

    @GetMapping("deleteWmsErpAsnLineById/{lineId}")
    @ApiOperation(value = "逐条删除行信息")
    @ApiImplicitParam(name = "lineId", value = "行信息ID")
    public Result deleteWmsErpAsnLineById(@PathVariable int lineId) {
        if (abWmsAsnOrderService.deleteLineOrderAndDetail(lineId)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

    @GetMapping("deleteWmsErpAsnLineByHeadId/{headId}")
    @ApiOperation(value = "逐条删除行信息")
    @ApiImplicitParam(name = "headId", value = "行信息ID")
    public Result deleteWmsErpAsnLineByHeadId(@PathVariable int headId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("head_id", headId);
        int count = iWmsErpAsnLineService.count(wrapper);
        if (count == 0) {
            return Result.success(ResultCode.SUCCESS);
        } else {
            if (iWmsErpAsnLineService.remove(wrapper)) {
                return Result.success(ResultCode.SUCCESS);
            }
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }
}
