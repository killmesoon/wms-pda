package com.siirisoft.aim.wms.service.dic;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.siirisoft.aim.wms.entity.dic.WmsDicList;
import com.siirisoft.aim.wms.entity.dic.ext.WmsDicListExt;

import java.util.List;

/**
 * <p>
 * wms字典表; InnoDB free: 11264 kB; (`dic_type_id`) REFER `wms/wms_dic_type`(`dic_typ 服务类
 * </p>
 *
 * @author DKY
 * @since 2020-05-14
 */
public interface IWmsDicListService extends IService<WmsDicList> {
    List<WmsDicListExt> queryWmsDicListExtList(Wrapper wrapper);
}
