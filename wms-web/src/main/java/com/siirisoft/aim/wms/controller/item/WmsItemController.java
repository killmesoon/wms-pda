package com.siirisoft.aim.wms.controller.item;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.siirisoft.aim.wms.entity.data.Result;
import com.siirisoft.aim.wms.entity.data.ResultCode;
import com.siirisoft.aim.wms.entity.item.WmsItem;
import com.siirisoft.aim.wms.service.item.IWmsItemService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 物料主数据; InnoDB free: 11264 kB 前端控制器
 * </p>
 *
 * @author DKY
 * @since 2020-05-18
 */
@RestController
@RequestMapping("/web-api/wms/item/wms-item")
public class WmsItemController {

    @Autowired
    private IWmsItemService iWmsItemService;

    @PostMapping("/queryItemList")
    @ApiOperation(value = "查询物料列表")
    @ApiImplicitParam(name = "wmsItem", value = "物料po")
    public Result findItemList(@RequestParam(defaultValue = "1") int current,
                               @RequestParam(defaultValue = "-1") int size,
                               @RequestBody(required = false) WmsItem wmsItem) {
        QueryWrapper wrapper = new QueryWrapper(wmsItem);
        if (wmsItem != null) {
            wrapper.eq(wmsItem.getPlantId() != null, "a.plant_id" , wmsItem.getPlantId());
            wrapper.eq(wmsItem.getItemCode() != null, "a.item_code" , wmsItem.getItemCode());
            wrapper.eq(wmsItem.getDescription() != null, "a.description" , wmsItem.getDescription());
            wrapper.eq(wmsItem.getOldCode() != null, "a.old_code" , wmsItem.getOldCode());
            wrapper.eq(wmsItem.getItemType() != null, "a.item_type" , wmsItem.getItemType());
            wrapper.eq(wmsItem.getEnableFlag() != null, "a.enable_flag" , wmsItem.getEnableFlag());
            wrapper.eq(wmsItem.getIqcFlag() != null, "a.iqc_flag" , wmsItem.getIqcFlag());
            wrapper.eq(wmsItem.getCodeType() != null, "a.code_type" , wmsItem.getCodeType());
            wrapper.eq(wmsItem.getItemTexture() != null, "a.item_texture" , wmsItem.getItemTexture());
        }
        wrapper.orderByAsc("a.item_code");
        return Result.success(iWmsItemService.findItemAllList(new Page<>(current,size),wrapper));
    }


    @PostMapping("/saveOrUpdateItem")
    @ApiOperation(value = "录入或更新物料信息")
    @ApiImplicitParam(name = "wmsItem", value = "物料po")
    public Result saveOrUpdateItem(@RequestBody WmsItem wmsItem) {
        if (wmsItem.getCreationDate() == null) {
            wmsItem.setCreationDate(new Date());
        } else {
            wmsItem.setLastUpdateDate(new Date());
        }
        //插入前校验
//        String itemCode = wmsItem.getItemCode();
//        QueryWrapper queryWrapper = new QueryWrapper();
//        queryWrapper.eq("item_code", itemCode);
//        if (iWmsItemService.count(queryWrapper) > 0) {
//            return Result.failure(ResultCode.DATA_ALREADY_EXISTED);
//        }
        if (iWmsItemService.saveOrUpdate(wmsItem)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

    @GetMapping("/deleteItemById/{itemId}")
    @ApiOperation(value = "删除物料信息")
    @ApiImplicitParam(name = "itemId", value = "物料id")
    public Result deleteItemById(@PathVariable int itemId) {
        if (iWmsItemService.removeById(itemId)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }


    @PostMapping("/deleteByIdList")
    @ApiOperation(value = "删除物料信息")
    @ApiImplicitParam(name = "list", value = "物料id集合")
    public Result deleteByIdList(@RequestBody List<Integer> list) {
        if (iWmsItemService.removeByIds(list)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);
    }

    @PostMapping("/checkItemCodeExits")
    @ApiOperation(value = "校验物料编码是否存在")
    @ApiImplicitParam(name = "wmsItem", value = "物料po")
    public Result checkItemCodeExits(@RequestBody WmsItem wmsItem) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("item_code", wmsItem.getItemCode());
        if (iWmsItemService.count(wrapper) > 0) {
            return Result.success(false);
        }
        return Result.success(true);
    }

}
