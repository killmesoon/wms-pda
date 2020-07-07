package com.siirisoft.aim.wms.service.impl.dic.ext;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.siirisoft.aim.wms.service.dic.ABWmsDicTypeService;
import com.siirisoft.aim.wms.service.dic.IWmsDicListService;
import com.siirisoft.aim.wms.service.dic.IWmsDicTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @User DKY
 * @Date 2020/7/7
 * @Description ABWmsDicTypeService实现类
 */
@Service
public class ABWmsDicTypeServiceImpl implements ABWmsDicTypeService {

    @Autowired
    private IWmsDicTypeService iWmsDicTypeService;

    @Autowired
    private IWmsDicListService iWmsDicListService;

    @Override
    @Transactional
    public boolean deleteDicType(Integer typeId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("dic_type_id", typeId);
        iWmsDicListService.remove(wrapper);
        iWmsDicTypeService.removeById(typeId);
        return true;
    }
}
