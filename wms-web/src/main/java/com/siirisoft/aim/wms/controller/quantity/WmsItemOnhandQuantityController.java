package com.siirisoft.aim.wms.controller.quantity;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.siirisoft.aim.wms.entity.data.Result;
import com.siirisoft.aim.wms.entity.quantity.WmsItemOnhandQuantity;
import com.siirisoft.aim.wms.service.quantity.IWmsItemOnhandQuantityService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 库存现有量; InnoDB free: 11264 kB 前端控制器
 * </p>
 *
 * @author DKY
 * @since 2020-06-05
 */
@RestController
@RequestMapping("/api/wms/onhand/wms-item-onhand-quantity")
public class WmsItemOnhandQuantityController {

    @Autowired
    private IWmsItemOnhandQuantityService iWmsItemOnhandQuantityService;

    @PostMapping("/queryQuantityList")
    @ApiOperation("现有量列表查询")
    @ApiImplicitParam(name = "库存po", value = "wmsItemOnhandQuantity")
    public Result queryQuantityList(@RequestParam(defaultValue = "1") int current,
                                    @RequestParam(defaultValue = "-1") int size,
                                    @RequestBody(required = false) WmsItemOnhandQuantity wmsItemOnhandQuantity) {
        QueryWrapper wrapper = new QueryWrapper(wmsItemOnhandQuantity);
        return Result.success(iWmsItemOnhandQuantityService.page(new Page<>(current,size), wrapper));
    }
}
