package com.siirisoft.aim.wms.controller.inbound;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.siirisoft.aim.wms.entity.data.Result;
import com.siirisoft.aim.wms.entity.data.ResultCode;
import com.siirisoft.aim.wms.entity.inbound.WmsInboundOrderHead;
import com.siirisoft.aim.wms.entity.inbound.ext.WmsInboundOrderHeadExt;
import com.siirisoft.aim.wms.service.inbound.ABWmsInboundOrderService;
import com.siirisoft.aim.wms.service.inbound.IWmsInboundOrderDetailService;
import com.siirisoft.aim.wms.service.inbound.IWmsInboundOrderHeadService;
import com.siirisoft.aim.wms.service.inbound.IWmsInboundOrderLineService;
import com.siirisoft.aim.wms.service.inbound.ext.IWmsInboundOrderHeadServiceExt;
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
 * 入库单头表; InnoDB free: 11264 kB 前端控制器
 * </p>
 *
 * @author DKY
 * @since 2020-05-14
 */
@RestController
@RequestMapping("/web-api/wms/inboundHead/wms-inbound-order-head")
public class WmsInboundOrderHeadController {

    @Autowired
    private IWmsInboundOrderHeadService inboundOrderHeadService;

    @Autowired
    private IWmsInboundOrderHeadServiceExt iWmsInboundOrderHeadServiceExt;

    @Autowired
    private ABWmsInboundOrderService abWmsInboundOrderService;

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

    @PostMapping("/saveOrUpdateInboundHead")
    @ApiOperation(value = "新增入库单头信息")
    @ApiImplicitParam(name = "wmsInboundOrderHead", value = "入库单head po")
    @Transactional
    public Result insertInboundHead(@RequestBody WmsInboundOrderHead wmsInboundOrderHead) {
        if (wmsInboundOrderHead.getCreationDate() == null) {
            wmsInboundOrderHead.setCreationDate(new Date());
        } else {
            wmsInboundOrderHead.setLastUpdateDate(new Date());
        }
        if (inboundOrderHeadService.saveOrUpdate(wmsInboundOrderHead)) {
            Integer headId = wmsInboundOrderHead.getHeadId();
            return Result.success(headId);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }


    @GetMapping("/deleteInboundHead/{headId}")
    @ApiOperation(value = "删除头表（单个）以及关联表信息")
    @Transactional
    @ApiImplicitParam(name = "headId", value = "头id")
    public Result deleteInboundHead(@PathVariable int headId) {
        //除了删除入库单头表，还要删除line表和detail 因为headId关联
        if (abWmsInboundOrderService.deleteHeadAndAll(headId)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

    @PostMapping("/deleteInboundHeadList")
    @ApiOperation(value = "批量删除入库单头表信息")
    public Result deleteInboundHeadList(@RequestBody List<Integer> headIdList) {
        QueryWrapper wrapper = new QueryWrapper();
        if (abWmsInboundOrderService.deleteHeadAndAllList(headIdList)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }
}
