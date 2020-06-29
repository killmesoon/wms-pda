package com.siirisoft.aim.wms.controller.outbound.pda;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.siirisoft.aim.wms.entity.data.Result;
import com.siirisoft.aim.wms.entity.data.ResultCode;
import com.siirisoft.aim.wms.entity.locator.ext.pda.WmsPdaLocatorExt;
import com.siirisoft.aim.wms.entity.outbound.ext.WmsOutboundOrderHeadExt;
import com.siirisoft.aim.wms.entity.outbound.ext.pda.WmsOutboundCondition;
import com.siirisoft.aim.wms.entity.outbound.ext.pda.WmsPdaOutboundOrderDetail;
import com.siirisoft.aim.wms.service.locator.pda.ABPdaWmsLocatorService;
import com.siirisoft.aim.wms.service.outbound.IWmsOutboundOrderHeadService;
import com.siirisoft.aim.wms.service.outbound.pda.ABPdaWmsOutboundOrderService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @User DKY
 * @Date 2020/6/22
 * @Description 出库执行PDA模块
 */
@RestController
@RequestMapping("/m-api/wms/pda/outboundOrder")
public class WmsPdaOutboundOrderController {


    @Autowired
    private ABPdaWmsOutboundOrderService abPdaWmsOutboundOrderService;

    @Autowired
    private ABPdaWmsLocatorService wmsLocatorService;

    @Autowired
    private IWmsOutboundOrderHeadService iWmsOutboundOrderHeadService;


    @PostMapping("/findOutboundHeadList")
    @ApiOperation(value = "出库单头表list")
    @ApiImplicitParam(name = "wmsOutboundOrderHead", value = "出库单po")
    public Result findOutboundHeadList(@RequestParam(defaultValue = "1") int current,
                                       @RequestParam(defaultValue = "-1") int size,
                                       @RequestBody(required = false) WmsOutboundOrderHeadExt wmsOutboundOrderHead) {
        QueryWrapper<WmsOutboundOrderHeadExt> wrapper = new QueryWrapper<WmsOutboundOrderHeadExt>();

        if (wmsOutboundOrderHead != null) {
            //遍历属性 添加筛选条件
            wrapper.eq(wmsOutboundOrderHead.getPlantId() != null, "a.plant_id", wmsOutboundOrderHead.getPlantId());
            wrapper.eq(wmsOutboundOrderHead.getDocStatus() != null, "a.doc_status", wmsOutboundOrderHead.getDocStatus());
            wrapper.eq(wmsOutboundOrderHead.getDocNumber() != null, "a.doc_number", wmsOutboundOrderHead.getDocNumber());
            wrapper.eq(wmsOutboundOrderHead.getDocType() != null, "a.doc_type", wmsOutboundOrderHead.getDocType());
            wrapper.eq(wmsOutboundOrderHead.getErpFlag() != null, "a.erp_flag", wmsOutboundOrderHead.getErpFlag());
            wrapper.eq(wmsOutboundOrderHead.getRfidFlag() != null, "a.rfid_flag", wmsOutboundOrderHead.getRfidFlag());
            wrapper.eq(wmsOutboundOrderHead.getSourceDocType() != null, "a.source_doc_type", wmsOutboundOrderHead.getSourceDocType());
            wrapper.eq(wmsOutboundOrderHead.getSourceDocNum() != null, "a.source_doc_num", wmsOutboundOrderHead.getSourceDocNum());
            if (wmsOutboundOrderHead.getCreationDateRange() != null) {
                wrapper.between(wmsOutboundOrderHead.getCreationDateRange().size() > 0,
                        "a.creation_date", wmsOutboundOrderHead.getCreationDateRange().get(0),
                        wmsOutboundOrderHead.getCreationDateRange().get(1));
            }
            if (wmsOutboundOrderHead.getPlanDateRange() != null) {
                wrapper.between(wmsOutboundOrderHead.getPlanDateRange().size() > 0,
                        "a.plan_time", wmsOutboundOrderHead.getPlanDateRange().get(0),
                        wmsOutboundOrderHead.getPlanDateRange().get(1));
            }
        }
        wrapper.orderByDesc("creation_date");
        return Result.success(iWmsOutboundOrderHeadService.findOutboundOrderList(new Page(current, size), wrapper));
    }


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
        int locatorId = wmsLocatorService.queryLocatorByCode(wmsPdaOutboundOrderDetail.getAdvBarcode());
        wrapper.eq("a.locator_id", locatorId);
        return Result.success(wmsLocatorService.queryLocatorDetail(new Page(current, size), wrapper));
    }


    @PostMapping("/commitPreparation")
    @ApiOperation(value = "备料执行")
    @ApiImplicitParam(name = "wmsPdaLocatorExt", value = "执行单po")
    public Result commitPreparation(@RequestBody WmsOutboundCondition wmsOutboundCondition) {

        //原货位到新货位，货位移动
        return Result.success(abPdaWmsOutboundOrderService.commitPreparation(wmsOutboundCondition));
    }


    @PostMapping("/outboundOrderExc")
    @ApiOperation(value = "出库执行")
    @ApiImplicitParam(name = "wmsPdaOutboundOrderDetail", value = "出库单详情表PO")
    public Result outboundOrderExc(@RequestBody List<WmsPdaOutboundOrderDetail> detailList) {
        if (abPdaWmsOutboundOrderService.outboundOrderExc(detailList)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

}
