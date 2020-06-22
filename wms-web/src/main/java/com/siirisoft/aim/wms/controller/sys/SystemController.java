package com.siirisoft.aim.wms.controller.sys;

import com.siirisoft.aim.wms.utils.DataParseUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @User DKY
 * @Date 2020/5/22
 * @Description 系统controller
 */
@RestController
@RequestMapping("/api/wms")
public class SystemController {
    @GetMapping("/menu/list")
    public Object getMenu() {
        return DataParseUtils.readJson();
    }
}
