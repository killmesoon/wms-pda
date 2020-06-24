package com.siirisoft.aim.wms.service.impl.asn.ext;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.siirisoft.aim.wms.entity.asn.WmsErpAsnDetail;
import com.siirisoft.aim.wms.entity.asn.WmsErpAsnHead;
import com.siirisoft.aim.wms.entity.asn.WmsErpAsnLine;
import com.siirisoft.aim.wms.entity.asn.ext.WmsErpAsnCondition;
import com.siirisoft.aim.wms.entity.data.Result;
import com.siirisoft.aim.wms.mapper.asn.WmsErpAsnDetailMapper;
import com.siirisoft.aim.wms.mapper.asn.WmsErpAsnHeadMapper;
import com.siirisoft.aim.wms.mapper.asn.WmsErpAsnLineMapper;
import com.siirisoft.aim.wms.service.asn.ABWmsAsnOrderService;
import com.siirisoft.aim.wms.service.asn.IWmsErpAsnDetailService;
import com.siirisoft.aim.wms.service.asn.IWmsErpAsnHeadService;
import com.siirisoft.aim.wms.service.asn.IWmsErpAsnLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @User DKY
 * @Date 2020/6/13
 * @Description ABWmsAsnOrderService实现类
 */
@Service
public class ABWmsAsnOrderServiceImpl  implements ABWmsAsnOrderService {


    @Autowired
    private IWmsErpAsnHeadService iWmsErpAsnHeadService;

    @Autowired
    private IWmsErpAsnLineService iWmsErpAsnLineService;

    @Autowired
    private IWmsErpAsnDetailService iWmsErpAsnDetailService;


    @Override
    @Transactional
    public boolean deleteAsnOrder(int headId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("head_id", headId);
        if(iWmsErpAsnHeadService.remove(wrapper)) {
            iWmsErpAsnLineService.remove(wrapper);
            iWmsErpAsnDetailService.remove(wrapper);
            return true;
        } else {
          throw new IllegalArgumentException("参数错误");
        }
    }

    @Override
    @Transactional
    public boolean deleteAsnOrderList(List<Integer> headIdList) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.in("head_id", headIdList);
        if(iWmsErpAsnHeadService.remove(wrapper)) {
            iWmsErpAsnLineService.remove(wrapper);
            iWmsErpAsnDetailService.remove(wrapper);
            return true;
        } else {
            throw new IllegalArgumentException("参数错误");
        }
    }

    @Override
    @Transactional
    public boolean saveWmsErpAsnOrder(WmsErpAsnCondition wmsErpAsnCondition) {
        QueryWrapper wrapper = new QueryWrapper();
        WmsErpAsnHead wmsErpAsnHead = wmsErpAsnCondition.getWmsErpAsnHead();
        List<WmsErpAsnLine> lineList = wmsErpAsnCondition.getWmsErpAsnLines();
        if (iWmsErpAsnHeadService.saveOrUpdate(wmsErpAsnHead)) {
            Integer headId = wmsErpAsnHead.getHeadId();
            for(WmsErpAsnLine line : lineList) {
                line.setHeadId(headId);
            }
            wrapper.eq("head_id", headId);
            List<WmsErpAsnLine> list = iWmsErpAsnLineService.list(wrapper);
            if (list.size() > 0) {
                QueryWrapper detailWrapper = new QueryWrapper();
                List<Integer> lineIdList = new ArrayList<>();
                for (WmsErpAsnLine w: list) {
                    lineIdList.add(w.getLineId());
                }
                //删除行时删除对应明细
                iWmsErpAsnLineService.remove(wrapper);
                detailWrapper.in("line_id", lineIdList);
                iWmsErpAsnDetailService.remove(detailWrapper);
            }
            iWmsErpAsnLineService.saveOrUpdateBatch(lineList);
            return true;
        }

        return false;
    }


    @Override
    @Transactional
    public boolean deleteLineOrderAndDetail(int lineId) {
        QueryWrapper wrapper = new QueryWrapper();
        if(iWmsErpAsnLineService.removeById(lineId)) {
            wrapper.eq("line_id", lineId);
            iWmsErpAsnDetailService.remove(wrapper);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean deleteLineOrderAndDetailList(List<Integer> lineIdList) {
        QueryWrapper wrapper = new QueryWrapper();
        if (iWmsErpAsnLineService.removeByIds(lineIdList)) {
            wrapper.in("line_id", lineIdList);
            iWmsErpAsnDetailService.remove(wrapper);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean saveWmsErpOrderLineList(int lineId, List<WmsErpAsnDetail> list) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("line_id", lineId);
        int count = iWmsErpAsnDetailService.count(wrapper);
        if (count > 0) {
            iWmsErpAsnDetailService.remove(wrapper);
        }
        if (iWmsErpAsnDetailService.saveOrUpdateBatch(list)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean asnOrderCheck(List<WmsErpAsnHead> list) {
        return false;
    }
}
