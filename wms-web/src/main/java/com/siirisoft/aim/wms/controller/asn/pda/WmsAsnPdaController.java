package com.siirisoft.aim.wms.controller.asn.pda;

import com.siirisoft.aim.wms.entity.asn.WmsErpAsnDetail;
import com.siirisoft.aim.wms.entity.asn.ext.WmsErpAsnDetailExt;
import com.siirisoft.aim.wms.entity.data.Result;
import com.siirisoft.aim.wms.service.asn.pda.ABPdaWmsAsnOrderService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @User DKY
 * @Date 2020/6/16
 * @Description PDA ASN模块服务提供
 */
@RestController
@RequestMapping("/api/wms/pda/asn")
public class WmsAsnPdaController {

    @Autowired
    private ABPdaWmsAsnOrderService abPdaWmsAsnOrderService;

    @PostMapping("/commitAsnOrder")
    @ApiOperation(value = "采购接受执行")
    @ApiImplicitParam(name = "list", value = "需要接收的列表")
    public Result commitAsnOrder(@RequestBody List<WmsErpAsnDetailExt> list) {
        return Result.success(abPdaWmsAsnOrderService.commitAsnOrderList(list));
    }
}
