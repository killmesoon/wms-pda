package com.siirisoft.aim.wms.controller.plant;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.siirisoft.aim.wms.entity.data.Result;
import com.siirisoft.aim.wms.service.warehouse.IWmsWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @User DKY
 * @Date 2020/6/29
 * @Description 工厂controller
 */
@RestController
@RequestMapping("/web-api/wms/plant")
public class WmsPlantController {

    @Autowired
    private IWmsWarehouseService iWmsWarehouseService;

    @GetMapping("/checkDelete/{code}")
    public Result checkDelete(@PathVariable String code) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("plant_code", code);
        if (iWmsWarehouseService.count(wrapper) > 0) {
            return Result.success(false);
        }
        return Result.success(true);
    }

}
