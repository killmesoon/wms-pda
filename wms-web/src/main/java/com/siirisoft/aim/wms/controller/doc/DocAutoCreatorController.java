package com.siirisoft.aim.wms.controller.doc;


import com.siirisoft.aim.wms.entity.data.Result;
import com.siirisoft.aim.wms.service.doc.ABDocAutoNumberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @User DKY
 * @Date 2020/6/15
 * @Description 获取自动单据编码
 */
@RestController
@RequestMapping("/api/wms/doc")
public class DocAutoCreatorController {

    @Autowired
    private ABDocAutoNumberService abDocAutoNumberService;


    @GetMapping("/getDocNumber/{dicId}")
    @ApiOperation(value = "获取自动单据号")
    public Result getAutoDocNumber(@PathVariable int dicId) {
        return Result.success(abDocAutoNumberService.generateAutoNumber(dicId));
    }
}