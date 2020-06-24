package com.siirisoft.aim.wms.controller.uom;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.siirisoft.aim.wms.entity.data.Result;
import com.siirisoft.aim.wms.entity.data.ResultCode;
import com.siirisoft.aim.wms.entity.uom.WmsUom;
import com.siirisoft.aim.wms.service.uom.IWmsUomService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 单位设置; InnoDB free: 11264 kB 前端控制器
 * </p>
 *
 * @author DKY
 * @since 2020-05-18
 */
@RestController
@RequestMapping("/web-api/wms/uom/wms-uom")
public class WmsUomController {


    @Autowired
    private IWmsUomService iWmsUomService;

    @PostMapping("/findUomList")
    @ApiOperation("查询单位表")
    @ApiImplicitParam(name = "wmsUom", value = "单位po")
    public Result getUomList(@RequestParam(defaultValue = "1") int current,
                             @RequestParam(defaultValue = "-1") int size,
                             @RequestBody(required = false) WmsUom wmsUom) {
        QueryWrapper queryWrapper = new QueryWrapper(wmsUom);
        if (wmsUom != null) {
            queryWrapper.eq(wmsUom.getUomCode() != null, "a.uom_code", wmsUom.getUomCode());
            queryWrapper.eq(wmsUom.getUomName() != null, "a.uom_name", wmsUom.getUomName());
            queryWrapper.eq(wmsUom.getUomType() != null, "a.uom_type", wmsUom.getUomType());
            queryWrapper.eq(wmsUom.getEnableFlag() != null, "a.enable_flag", wmsUom.getEnableFlag());
            queryWrapper.eq(wmsUom.getPrimaryFlag() != null, "a.primary_flag", wmsUom.getPrimaryFlag());
        }
        return Result.success(iWmsUomService.findUomList(new Page<>(current,size),queryWrapper));
    }

    @PostMapping("saveOrUpdateUom")
    @ApiOperation(value = "删除或更新单位")
    @ApiImplicitParam(name = "wmsUom", value = "单位po")
    public Result saveOrUpdateUom(@RequestBody WmsUom wmsUom) {
        if (wmsUom.getCreationDate() == null) {
            wmsUom.setCreationDate(new Date());
        } else {
            wmsUom.setLastUpdateDate(new Date());
        }
        if (iWmsUomService.saveOrUpdate(wmsUom)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }


    @GetMapping("deleteUomById/{uomId}")
    @ApiOperation(value = "单条删除uom")
    @ApiImplicitParam(name = "uomId", value = "单位id")
    public Result deleteUomById(@PathVariable int uomId) {
        if (iWmsUomService.removeById(uomId)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

    @PostMapping("/deleteUomByIdList")
    @ApiOperation(value = "批量删除uom")
    @ApiImplicitParam(name = "list", value = "单位id列表")
    public Result deleteUomByIdList(@RequestBody List<Integer> list) {
        if (iWmsUomService.removeByIds(list)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

    @GetMapping("/queryPrimaryUomTypeList")
    @ApiOperation(value = "获取是否可以选择主单位的标识")
    @ApiImplicitParam(name = "uomType", value = "单位类型")
    public Result queryPrimaryUomTypeList(@RequestParam String uomType) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("primary_flag", 1);
        wrapper.eq("uom_type", uomType);
        int count = iWmsUomService.count(wrapper);
        if (count == 0) {
            return Result.success(false);
        }
        return Result.success(true);
    }
}
