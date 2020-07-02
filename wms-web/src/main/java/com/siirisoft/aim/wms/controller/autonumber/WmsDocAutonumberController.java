package com.siirisoft.aim.wms.controller.autonumber;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.siirisoft.aim.wms.entity.autonumber.WmsDocAutonumber;
import com.siirisoft.aim.wms.entity.data.Result;
import com.siirisoft.aim.wms.entity.data.ResultCode;
import com.siirisoft.aim.wms.entity.dic.WmsDicList;
import com.siirisoft.aim.wms.entity.dic.WmsDicType;
import com.siirisoft.aim.wms.service.autonumber.IWmsDocAutonumberService;
import com.siirisoft.aim.wms.service.dic.IWmsDicListService;
import com.siirisoft.aim.wms.service.dic.IWmsDicTypeService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 单据自动编码设置; InnoDB free: 11264 kB 前端控制器
 * </p>
 *
 * @author DKY
 * @since 2020-06-09
 */
@RestController
@RequestMapping("/web-api/wms/docAutonumber/wms-doc-autonumber")
public class WmsDocAutonumberController {

    @Autowired
    private IWmsDocAutonumberService iWmsDocAutonumberService;

    @Autowired
    private IWmsDicTypeService iWmsDicTypeService;

    @Autowired
    private IWmsDicListService iWmsDicListService;

    @PostMapping("/queryDocAutoNumber")
    @ApiOperation("查询单据编码list")
    @ApiImplicitParam(name = "wmsDocAutonumber", value = "单据po")
    public Result queryDocAutoNumber(@RequestParam(defaultValue = "1") int current,
                                     @RequestParam(defaultValue = "-1") int size,
                                     @RequestBody(required = false) WmsDocAutonumber wmsDocAutonumber) {
        QueryWrapper wrapper = new QueryWrapper(wmsDocAutonumber);
        return Result.success(iWmsDocAutonumberService.page(new Page<>(current,size), wrapper));
    }


    @PostMapping("/saveOrUpdateDocAutoNumber")
    @ApiOperation("新增编辑Autonumberlist")
    @ApiImplicitParam(name = "wmsDocAutonumber", value = "单据po")
    public Result saveOrUpdateDocAutoNumber(@RequestBody WmsDocAutonumber wmsDocAutonumber) {
        if (iWmsDocAutonumberService.saveOrUpdate(wmsDocAutonumber)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

    @PostMapping("/deleteDocAutoNumberList")
    @ApiOperation("批量删除Autonumberlist")
    @ApiImplicitParam(name = "idList", value = "单据ID列表")
    public Result deleteDocAutoNumberList(@RequestBody List<Integer> idList) {
        if (iWmsDocAutonumberService.removeByIds(idList)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

    @GetMapping("/deleteDocAutoNumberById/{id}")
    @ApiOperation("逐条删除Autonumber")
    @ApiImplicitParam(name = "id", value = "单据ID")
    public Result deleteDocAutoNumberById(@PathVariable int id) {
        WmsDocAutonumber doc = iWmsDocAutonumberService.getById(id);
        if (iWmsDocAutonumberService.removeById(id)) {
            //删除之后要修改docFlag
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("dic_type_code",doc.getLookupcode());
            WmsDicType dicType = iWmsDicTypeService.getOne(wrapper);
            if (dicType != null) {
                QueryWrapper resultWrapper = new QueryWrapper();
                resultWrapper.eq("dic_type_id", dicType.getDicTypeId());
                resultWrapper.eq("dic_code", doc.getDocType());
                WmsDicList wmsDicList = new WmsDicList();
                wmsDicList.setDocFlag(false);
                if (iWmsDicListService.update(wmsDicList,resultWrapper)) {
                    return Result.success(ResultCode.SUCCESS);
                }
            }
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

}
