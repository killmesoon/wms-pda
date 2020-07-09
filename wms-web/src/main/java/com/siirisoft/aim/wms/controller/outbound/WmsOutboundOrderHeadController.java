package com.siirisoft.aim.wms.controller.outbound;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.siirisoft.aim.wms.entity.data.Result;
import com.siirisoft.aim.wms.entity.data.ResultCode;
import com.siirisoft.aim.wms.entity.outbound.WmsOutboundOrderHead;
import com.siirisoft.aim.wms.entity.outbound.ext.WmsOutboundOrderHeadExt;
import com.siirisoft.aim.wms.service.outbound.ABWmsOutboundOrderService;
import com.siirisoft.aim.wms.service.outbound.IWmsOutboundOrderDetailService;
import com.siirisoft.aim.wms.service.outbound.IWmsOutboundOrderHeadService;
import com.siirisoft.aim.wms.service.outbound.IWmsOutboundOrderLineService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 出库单头表; InnoDB free: 11264 kB 前端控制器
 * </p>
 *
 * @author DKY
 * @since 2020-05-15
 */
@RestController
@RequestMapping("/web-api/wms/outboundHead/wms-outbound-order-head")
public class WmsOutboundOrderHeadController {

    @Autowired
    private IWmsOutboundOrderHeadService iWmsOutboundOrderHeadService;

    @Autowired
    private ABWmsOutboundOrderService abWmsOutboundOrderService;

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

    @PostMapping("/saveOrUpdateOutboundHead")
    @ApiOperation(value = "新增或更新出库单头")
    @ApiImplicitParam(name = "wmsOutboundOrderHead", value = "出库单po")
    @Transactional
    public Result saveOrUpdateOutboundHead(@RequestBody WmsOutboundOrderHead wmsOutboundOrderHead) {
        if (wmsOutboundOrderHead.getCreationDate() == null) {
            wmsOutboundOrderHead.setCreationDate(new Date());
        } else {
            wmsOutboundOrderHead.setLastUpdateDate(new Date());
            wmsOutboundOrderHead.setLastUpdateBy(wmsOutboundOrderHead.getLastUpdateBy());
        }
        if (iWmsOutboundOrderHeadService.saveOrUpdate(wmsOutboundOrderHead)) {
            Integer headId = wmsOutboundOrderHead.getHeadId();
            return Result.success(headId);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }


    @GetMapping("/deleteOutboundHeadByHeadId/{headId}")
    @ApiOperation(value = "删除出库单头")
    @ApiImplicitParam(name = "headId", value = "出库单headId")
    public Result deleteOutboundHeadByHeadId(@PathVariable int headId) {
            if (abWmsOutboundOrderService.deleteOutboundHeadOrder(headId)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

    @PostMapping("/deleteOutboundHeadList")
    @ApiOperation(value = "批量删除出库单头")
    @ApiImplicitParam(name = "headList", value = "出库单headId列表")
    public Result deleteOutboundHeadList(@RequestBody List<Integer> headList) {
        if (abWmsOutboundOrderService.deleteOutboundHeadOrder(headList)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }
}
