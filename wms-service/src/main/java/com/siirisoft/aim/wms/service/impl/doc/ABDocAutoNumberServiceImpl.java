package com.siirisoft.aim.wms.service.impl.doc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.siirisoft.aim.wms.entity.autonumber.WmsDocAutonumber;
import com.siirisoft.aim.wms.entity.dic.ext.WmsDicListExt;
import com.siirisoft.aim.wms.service.autonumber.IWmsDocAutonumberService;
import com.siirisoft.aim.wms.service.dic.IWmsDicListService;
import com.siirisoft.aim.wms.service.doc.ABDocAutoNumberService;
import com.siirisoft.aim.wms.utils.AutoDocNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @User DKY
 * @Date 2020/6/15
 * @Description ABDocAutoNumberService实现类
 */
@Service
public class ABDocAutoNumberServiceImpl implements ABDocAutoNumberService {

    @Autowired
    private IWmsDicListService iWmsDicListService;

    @Autowired
    private IWmsDocAutonumberService wmsDocAutonumberService;

    @Override
    public String generateAutoNumber(int dicId) {
        QueryWrapper dicWrapper = new QueryWrapper();
        dicWrapper.eq("dic_id", dicId);
        WmsDicListExt wmsDoc = null;
        List<WmsDicListExt> list = iWmsDicListService.queryWmsDicListExtList(dicWrapper);
        if (list.size() > 0) {
            wmsDoc = list.get(0);
        }
        QueryWrapper docWrapper = new QueryWrapper();
        docWrapper.eq("doc_type", wmsDoc.getDicCode());
        docWrapper.eq("lookupcode",wmsDoc.getDicTypeCode());
        WmsDocAutonumber docPo = wmsDocAutonumberService.getOne(docWrapper);
        String autoDocNumber = AutoDocNumberGenerator.getAutoDocNumber(docPo);
        //生成订单号后校验

        return autoDocNumber;
    }
}
