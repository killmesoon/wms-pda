package com.siirisoft.aim.wms.controller.dic;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.siirisoft.aim.wms.entity.data.Result;
import com.siirisoft.aim.wms.entity.data.ResultCode;
import com.siirisoft.aim.wms.entity.dic.WmsDicList;
import com.siirisoft.aim.wms.entity.dic.ext.WmsDic;
import com.siirisoft.aim.wms.entity.dic.ext.WmsDicListExt;
import com.siirisoft.aim.wms.service.dic.IWmsDicListService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * wms字典表; InnoDB free: 11264 kB; (`dic_type_id`) REFER `wms/wms_dic_type`(`dic_typ 前端控制器
 * </p>
 *
 * @author DKY
 * @since 2020-05-14
 */
@RestController
@RequestMapping("/web-api/wms/dic/wms-dic-list")
public class WmsDicListController {
    @Autowired
    private IWmsDicListService wmsDicListService;

    @GetMapping("/list")
    @ApiOperation(value = "查询字典List")
    public Result findDicList() {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("enable_flag", true);
        return Result.success(wmsDicListService.list(wrapper));
    }

    @GetMapping("/queryWmsDicListExtList")
    @ApiOperation(value = "查询所有字典类型及字典类型具体信息")
    public Result queryWmsDicListExtList() {
        QueryWrapper wrapper = new QueryWrapper();
//        List<WmsDicListExt> wmsDicListExts = wmsDicListService.queryWmsDicListExtList(wrapper);
//        Map result = new HashMap();
//        for (WmsDicListExt w : wmsDicListExts) {
//            if (result.containsKey(w.getDicTypeId())) {
//              WmsDic wmsDic = (WmsDic) result.get(w.getDicTypeId());
//              w.setTreeName(w.getDicName());
//              wmsDic.getChildren().add(w);
//            } else {
//                WmsDic wmsDic = new WmsDic();
//                List<WmsDicListExt> list = new ArrayList<>();
//                w.setTreeName(w.getDicName());
//                list.add(w);
//                wmsDic.setChildren(list);
//                wmsDic.setDicTypeId(w.getDicTypeId());
//                wmsDic.setDicTypeCode(w.getDicTypeCode());
//                wmsDic.setDicTypeName(w.getDicTypeName());
//                wmsDic.setTreeName(w.getDicTypeName());
//                result.put(w.getDicTypeId(), wmsDic);
//            }
//        }
//        List<WmsDic> list = new ArrayList<>(result.values());
        return Result.success(wmsDicListService.queryWmsDicListExtList(wrapper));
    }


    @PostMapping("/saveOrUpdateWmsDic")
    @ApiOperation(value = "更新快码状态")
    public Result saveOrUpdateWmsDic(@RequestBody WmsDicList wmsDicList) {
        UpdateWrapper wrapper = new UpdateWrapper(wmsDicList);
        if (wmsDicListService.saveOrUpdate(wmsDicList,wrapper)) {
            return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_IS_WRONG);

    }


}
