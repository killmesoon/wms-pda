package com.siirisoft.aim.wms.service.impl.dic;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.siirisoft.aim.wms.entity.dic.WmsDicList;
import com.siirisoft.aim.wms.entity.dic.ext.WmsDicListExt;
import com.siirisoft.aim.wms.mapper.dic.WmsDicListMapper;
import com.siirisoft.aim.wms.mapper.dic.ext.WmsDicListMapperExt;
import com.siirisoft.aim.wms.service.dic.IWmsDicListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * wms字典表; InnoDB free: 11264 kB; (`dic_type_id`) REFER `wms/wms_dic_type`(`dic_typ 服务实现类
 * </p>
 *
 * @author DKY
 * @since 2020-05-14
 */
@Service
public class WmsDicListServiceImpl extends ServiceImpl<WmsDicListMapper, WmsDicList> implements IWmsDicListService {

    @Autowired
    private WmsDicListMapperExt wmsDicListMapperExt;

    @Override
    public List<WmsDicListExt> queryWmsDicListExtList(Wrapper wrapper) {
        return wmsDicListMapperExt.findWmsDicListExtList(wrapper);
    }
}
