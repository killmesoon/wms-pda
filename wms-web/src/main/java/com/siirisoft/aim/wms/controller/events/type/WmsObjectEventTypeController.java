package com.siirisoft.aim.wms.controller.events.type;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.siirisoft.aim.wms.entity.data.Result;
import com.siirisoft.aim.wms.entity.data.ResultCode;
import com.siirisoft.aim.wms.entity.events.type.WmsObjectEventType;
import com.siirisoft.aim.wms.service.events.type.IWmsObjectEventTypeService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 事件类型设置; InnoDB free: 11264 kB 前端控制器
 * </p>
 *
 * @author DKY
 * @since 2020-06-17
 */
@RestController
@RequestMapping("/web-api/wms/eventsType/wms-object-event-type")
public class WmsObjectEventTypeController {


    @Autowired
    private IWmsObjectEventTypeService iWmsObjectEventTypeService;


    @PostMapping("/queryWmsObjectEventTypeList")
    @ApiOperation(value = "查询事件类型list")
    @ApiImplicitParam(name = "wmsObjectEventType", value = "事件类型po")
    public Result queryWmsObjectEventTypeList(@RequestParam(defaultValue = "1") int current,
                                              @RequestParam(defaultValue = "-1") int size,
                                              @RequestBody(required = false) WmsObjectEventType wmsObjectEventType) {
        QueryWrapper wrapper = new QueryWrapper();
        if (wmsObjectEventType != null) {
            wrapper.eq(wmsObjectEventType.getEventTypeCode() != null , "event_type_code", wmsObjectEventType.getEventTypeCode());
            wrapper.eq(wmsObjectEventType.getEventTypeName() != null , "event_type_name", wmsObjectEventType.getEventTypeName());
            wrapper.eq(wmsObjectEventType.getCalculatorFlag() != null , "calculator_flag", wmsObjectEventType.getCalculatorFlag());
            wrapper.eq(wmsObjectEventType.getCalculatorType() != null , "calculator_type", wmsObjectEventType.getCalculatorType());
            wrapper.eq(wmsObjectEventType.getEnableFlag() != null , "enable_flag", wmsObjectEventType.getEnableFlag());
            wrapper.eq(wmsObjectEventType.getDescription() != null , "description", wmsObjectEventType.getDescription());
        }
        return Result.success(iWmsObjectEventTypeService.queryObjectEventTypeList(new Page<>(current, size), wrapper));
    }

    @PostMapping("/saveOrUpdateWmsObjectEventType")
    @ApiOperation(value = "保存或更新事件类型")
    @ApiImplicitParam(name = "wmsObjectEventType", value = "事件类型po")
    public Result saveOrUpdateWmsObjectEventType(@RequestBody WmsObjectEventType wmsObjectEventType) {
        if (iWmsObjectEventTypeService.saveOrUpdate(wmsObjectEventType)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

    @GetMapping("/deleteWmsObjectEventTypeById/{typeId}")
    @ApiOperation(value = "根据ID逐条删除事件类型")
    @ApiImplicitParam(name = "typeId", value = "事件类型ID")
    public Result deleteWmsObjectEventTypeById(@PathVariable int typeId) {
        if (iWmsObjectEventTypeService.removeById(typeId)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

    @PostMapping("/deleteWmsObjectEventType")
    @ApiOperation(value = "根据ID批量删除事件类型")
    @ApiImplicitParam(name = "typeId", value = "事件类型ID集合")
    public Result deleteWmsObjectEventType(@RequestBody List<Integer> typeIdList) {
        if (iWmsObjectEventTypeService.removeByIds(typeIdList)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

}
