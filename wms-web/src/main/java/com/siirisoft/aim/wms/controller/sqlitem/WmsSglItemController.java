package com.siirisoft.aim.wms.controller.sqlitem;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.siirisoft.aim.wms.entity.data.Result;
import com.siirisoft.aim.wms.entity.sqlitem.WmsSglItem;
import com.siirisoft.aim.wms.service.sqlitem.IWmsSglItemService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 条码无聊表详情; InnoDB free: 11264 kB 前端控制器
 * </p>
 *
 * @author DKY
 * @since 2020-06-15
 */
@RestController
@RequestMapping("/web-api/wms/sqlItem/wms-sgl-item")
public class WmsSglItemController {

    @Autowired
    private IWmsSglItemService iWmsSglItemService;

    @PostMapping("/queryWmsSglItemList")
    @ApiOperation(value = "查询条码物料表")
    @ApiImplicitParam(name = "wmsSglItem", value = "条码物料bo")
    public Result queryWmsSglItemList(@RequestParam(defaultValue = "1") int current,
                                      @RequestParam(defaultValue = "-1") int size,
                                      @RequestBody(required = false) WmsSglItem wmsSglItem) {
        QueryWrapper wrapper = new QueryWrapper();
        if (wmsSglItem != null) {
            String keyWord = wmsSglItem.getNote();
            if (keyWord != null) {
                wrapper.like("a.d_sequence_num", keyWord);
                wrapper.or();
                wrapper.like("a.plant_code", keyWord);
                wrapper.or();
                wrapper.like("d.locator_code", keyWord);
                wrapper.or();
                wrapper.like("c.warehouse_code", keyWord);
                wrapper.or();
                wrapper.like("b.item_code", keyWord);
            }
        }

        return Result.success(iWmsSglItemService.queryWmsSglItemList(new Page(current, size), wrapper));
    }
}
