package com.siirisoft.aim.wms.controller.asn;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.siirisoft.aim.wms.entity.asn.WmsErpAsnHead;
import com.siirisoft.aim.wms.entity.asn.ext.WmsErpAsnCondition;
import com.siirisoft.aim.wms.entity.asn.ext.WmsErpAsnHeadExt;
import com.siirisoft.aim.wms.entity.data.Result;
import com.siirisoft.aim.wms.entity.data.ResultCode;
import com.siirisoft.aim.wms.service.asn.ABWmsAsnOrderService;
import com.siirisoft.aim.wms.service.asn.IWmsErpAsnHeadService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 送货单头表; InnoDB free: 11264 kB 前端控制器
 * </p>
 *
 * @author DKY
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/web-api/wms/asnHead/wms-erp-asn-head")
public class WmsErpAsnHeadController {

    @Autowired
    private IWmsErpAsnHeadService iWmsErpAsnHeadService;

    @Autowired
    private ABWmsAsnOrderService abWmsAsnOrderService;

    @PostMapping("/queryWmsErpAsnHeadList")
    @ApiOperation(value = "查询送货单头表list")
    @ApiImplicitParam(name = "wmsErpAsnHead", value = "送货单头表po")
    public Result queryWmsErpAsnHeadList(@RequestParam(defaultValue = "1") int current,
                                         @RequestParam(defaultValue = "-1") int size,
                                         @RequestBody(required = false) WmsErpAsnHeadExt wmsErpAsnHead) {
        QueryWrapper wrapper = new QueryWrapper(wmsErpAsnHead);
        if (wmsErpAsnHead != null) {
            wrapper.eq(wmsErpAsnHead.getPlantId() != null , "a.plant_id" , wmsErpAsnHead.getPlantId());
            wrapper.eq(wmsErpAsnHead.getAsnType() != null , "a.asn_type" , wmsErpAsnHead.getAsnType());
            wrapper.apply(wmsErpAsnHead.getAsnNumber() != null, "a.asn_number like {0}", wmsErpAsnHead.getAsnNumber());
            wrapper.in(wmsErpAsnHead.getAsnStatusList() != null && wmsErpAsnHead.getAsnStatusList().size() > 0 , "a.asn_status" , wmsErpAsnHead.getAsnStatusList());
            wrapper.apply(wmsErpAsnHead.getSourceDocNum() != null , "a.source_doc_num like {0}" , wmsErpAsnHead.getSourceDocNum());
            wrapper.apply(wmsErpAsnHead.getCreatedName() != null , "a.created_name like {0}" , wmsErpAsnHead.getCreatedName());
            wrapper.eq(wmsErpAsnHead.getSupplierId() != null , "a.supplier_id" , wmsErpAsnHead.getSupplierId());
            if (wmsErpAsnHead.getPlanStartDate() != null && wmsErpAsnHead.getPlanEndDate() != null) {
                wrapper.between("a.plan_deliver_date", wmsErpAsnHead.getPlanStartDate(), wmsErpAsnHead.getPlanEndDate());
            }
            if (wmsErpAsnHead.getCreatedStartDate() != null && wmsErpAsnHead.getCreatedEndDate() != null) {
                wrapper.between("a.creation_date", wmsErpAsnHead.getCreatedStartDate(), wmsErpAsnHead.getCreatedEndDate());
            }
        }
        wrapper.orderByDesc("a.creation_date");
        return Result.success(iWmsErpAsnHeadService.queryWmsErpAsnHeadList(new Page(current, size), wrapper));
    }


    @PostMapping("/saveOrUpdateWmsErpAsnHead")
    @ApiOperation(value = "更新送货单头信息")
    @ApiImplicitParam(name = "wmsErpAsnHead", value = "送货单头表po")
    public Result saveOrUpdateWmsErpAsnHead(@RequestBody WmsErpAsnHead wmsErpAsnHead) {
        if (wmsErpAsnHead.getCreationDate() == null) {
            wmsErpAsnHead.setCreationDate(new Date());
        } else {
            wmsErpAsnHead.setLastUpdateDate(new Date());
            wmsErpAsnHead.setLastUpdateBy(wmsErpAsnHead.getCreatedBy());
        }
        if (iWmsErpAsnHeadService.saveOrUpdate(wmsErpAsnHead)) {
            return Result.success(wmsErpAsnHead.getHeadId());
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

    @GetMapping("/deleteWmsErpAsnHeadById/{headId}")
    @ApiOperation(value = "删除送货单头信息")
    @ApiImplicitParam(name = "headId", value = "送货单头id")
    public Result deleteWmsErpAsnHeadById(@PathVariable int headId) {
        if (abWmsAsnOrderService.deleteAsnOrder(headId)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }



    @PostMapping("/deleteWmsErpAsnHeadList")
    @ApiOperation(value = "批量删除送货单头信息")
    @ApiImplicitParam(name = "idList", value = "送货单头id列表")
    public Result deleteWmsErpAsnHeadList(@RequestBody List<Integer> idList) {
        if (abWmsAsnOrderService.deleteAsnOrderList(idList)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }


    @PostMapping("/saveWmsErpAsnOrder")
    @ApiOperation(value = "保存送货单整体信息")
    @ApiImplicitParam(name = "wmsErpAsnCondition", value = "送货单BO")
    public Result saveWmsErpAsnOrder(@RequestBody WmsErpAsnCondition wmsErpAsnCondition) {
        if (abWmsAsnOrderService.saveWmsErpAsnOrder(wmsErpAsnCondition)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

    @GetMapping("/checkAsnOrderExits/{asnNumber}")
    @ApiOperation(value = "校验送货单号是否存在")
    @ApiImplicitParam(name = "asnNumber", value = "送货单号")
    public Result checkAsnOrderExits(@PathVariable String asnNumber) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("asn_number", asnNumber);
        if (iWmsErpAsnHeadService.count(wrapper) == 0) {
            return Result.success(true);
        }
        return Result.success(false);
    }


    @PostMapping("/asnOrderCheck")
    @ApiOperation(value = "出库审核")
    public Result asnOrderCheck(@RequestBody List<WmsErpAsnHead> list) {
        if (abWmsAsnOrderService.asnOrderCheck(list)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

}
