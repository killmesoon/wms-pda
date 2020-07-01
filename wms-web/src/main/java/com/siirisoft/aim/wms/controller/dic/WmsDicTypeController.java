package com.siirisoft.aim.wms.controller.dic;


import com.siirisoft.aim.wms.entity.data.Result;
import com.siirisoft.aim.wms.entity.data.ResultCode;
import com.siirisoft.aim.wms.entity.dic.WmsDicType;
import com.siirisoft.aim.wms.service.dic.IWmsDicTypeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 字典类型表; InnoDB free: 11264 kB 前端控制器
 * </p>
 *
 * @author DKY
 * @since 2020-05-14
 */
@RestController
@RequestMapping("/web-api/wms/dictype/wms-dic-type")
public class WmsDicTypeController {

    @Autowired
    private IWmsDicTypeService wmsDicTypeService;

    @GetMapping("/list")
    @ApiOperation(value = "查询所有字典类型")
    public Result findAllDicTypeList() {
        return Result.success(wmsDicTypeService.list());
    }

    @PostMapping("/saveOrUpdateWmsDicType")
    @ApiOperation(value = "保存更新快码类型")
    public Result saveOrUpdateWmsDicType(@RequestBody WmsDicType wmsDicType) {
        if (wmsDicTypeService.saveOrUpdate(wmsDicType)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }
}
