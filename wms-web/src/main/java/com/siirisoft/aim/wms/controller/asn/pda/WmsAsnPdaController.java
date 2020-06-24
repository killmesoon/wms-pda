package com.siirisoft.aim.wms.controller.asn.pda;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.siirisoft.aim.wms.entity.asn.WmsErpAsnDetail;
import com.siirisoft.aim.wms.entity.asn.ext.WmsErpAsnDetailExt;
import com.siirisoft.aim.wms.entity.asn.ext.WmsErpAsnHeadExt;
import com.siirisoft.aim.wms.entity.data.Result;
import com.siirisoft.aim.wms.service.asn.IWmsErpAsnHeadService;
import com.siirisoft.aim.wms.service.asn.pda.ABPdaWmsAsnOrderService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @User DKY
 * @Date 2020/6/16
 * @Description PDA ASN模块服务提供
 */
@RestController
@RequestMapping("/m-api/wms/pda/asn")
public class WmsAsnPdaController {

    @Autowired
    private ABPdaWmsAsnOrderService abPdaWmsAsnOrderService;

    @Autowired
    private IWmsErpAsnHeadService iWmsErpAsnHeadService;


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
            wrapper.eq(wmsErpAsnHead.getAsnNumber() != null , "a.asn_number" , wmsErpAsnHead.getAsnNumber());
            wrapper.eq(wmsErpAsnHead.getAsnStatus() != null , "a.asn_status" , wmsErpAsnHead.getAsnStatus());
            wrapper.eq(wmsErpAsnHead.getSourceDocNum() != null , "a.source_doc_num" , wmsErpAsnHead.getSourceDocNum());
            wrapper.eq(wmsErpAsnHead.getSupplierId() != null , "a.supplier_id" , wmsErpAsnHead.getSupplierId());
            if (wmsErpAsnHead.getPlanStartDate() != null && wmsErpAsnHead.getPlanEndDate() != null) {
                wrapper.between("a.plan_deliver_date", wmsErpAsnHead.getPlanStartDate(), wmsErpAsnHead.getPlanEndDate());
            }
        }
        return Result.success(iWmsErpAsnHeadService.queryWmsErpAsnHeadList(new Page(current, size), wrapper));
    }

    @PostMapping("/commitAsnOrder")
    @ApiOperation(value = "采购接受执行")
    @ApiImplicitParam(name = "list", value = "需要接收的列表")
    public Result commitAsnOrder(@RequestBody List<WmsErpAsnDetailExt> list) {
        return Result.success(abPdaWmsAsnOrderService.commitAsnOrderList(list));
    }
}
