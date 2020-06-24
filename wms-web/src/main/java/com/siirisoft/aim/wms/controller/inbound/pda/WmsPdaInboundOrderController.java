package com.siirisoft.aim.wms.controller.inbound.pda;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.siirisoft.aim.wms.entity.data.Result;
import com.siirisoft.aim.wms.entity.inbound.ext.WmsInboundOrderHeadExt;
import com.siirisoft.aim.wms.entity.inbound.ext.pda.WmsPdaInboundOrderDetail;
import com.siirisoft.aim.wms.service.inbound.ext.IWmsInboundOrderHeadServiceExt;
import com.siirisoft.aim.wms.service.inbound.pda.ABPdaWmsInboundOrderService;
import io.swagger.annotations.ApiImplicitParam;
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

    @Autowired
    private IWmsInboundOrderHeadServiceExt iWmsInboundOrderHeadServiceExt;


    @PostMapping("/findInboundOrderHeadList")
    @ApiOperation(value = "查询入库单头列表")
    @ApiImplicitParam(name = "wmsInboundOrderHead", value = "查询条件")
    public Result findInboundOrderList(@RequestParam(defaultValue = "1") int current,
                                       @RequestParam(defaultValue = "-1") int size,
                                       @RequestBody(required = false) WmsInboundOrderHeadExt wmsInboundOrderHeadExt) {
        QueryWrapper<WmsInboundOrderHeadExt> queryWrapper = new QueryWrapper<>(wmsInboundOrderHeadExt);
        if (wmsInboundOrderHeadExt != null) {
            //遍历属性 添加筛选条件
            queryWrapper.eq(wmsInboundOrderHeadExt.getPlantId() != null, "a.plant_id", wmsInboundOrderHeadExt.getPlantId());
            queryWrapper.eq(wmsInboundOrderHeadExt.getDocStatus() != null , "a.doc_status", wmsInboundOrderHeadExt.getDocStatus());
            queryWrapper.eq(wmsInboundOrderHeadExt.getDocNumber() != null , "a.doc_number", wmsInboundOrderHeadExt.getDocNumber());
            queryWrapper.eq(wmsInboundOrderHeadExt.getDocType() != null, "a.doc_type", wmsInboundOrderHeadExt.getDocType());
            queryWrapper.eq(wmsInboundOrderHeadExt.getErpFlag() != null, "a.erp_flag", wmsInboundOrderHeadExt.getErpFlag());
            queryWrapper.eq(wmsInboundOrderHeadExt.getRfidFlag() != null, "a.rfid_flag", wmsInboundOrderHeadExt.getRfidFlag());
            queryWrapper.eq(wmsInboundOrderHeadExt.getSourceDocType() != null , "a.source_doc_type", wmsInboundOrderHeadExt.getSourceDocType());
            queryWrapper.eq(wmsInboundOrderHeadExt.getSourceDocNum() != null, "a.source_doc_num", wmsInboundOrderHeadExt.getSourceDocNum());
            if (wmsInboundOrderHeadExt.getCreationDateRange() != null) {
                queryWrapper.between(wmsInboundOrderHeadExt.getCreationDateRange().size() > 0,
                        "a.creation_date", wmsInboundOrderHeadExt.getCreationDateRange().get(0),
                        wmsInboundOrderHeadExt.getCreationDateRange().get(1));
            }
            if (wmsInboundOrderHeadExt.getPlanDateRange() != null) {
                queryWrapper.between(wmsInboundOrderHeadExt.getPlanDateRange().size() > 0,
                        "a.plan_time", wmsInboundOrderHeadExt.getPlanDateRange().get(0),
                        wmsInboundOrderHeadExt.getPlanDateRange().get(1));
            }
        }
        queryWrapper.orderByDesc("creation_date");
        return Result.success(iWmsInboundOrderHeadServiceExt.wmsInboundHeadOrderList(new Page<>(current, size), queryWrapper));
    }

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
