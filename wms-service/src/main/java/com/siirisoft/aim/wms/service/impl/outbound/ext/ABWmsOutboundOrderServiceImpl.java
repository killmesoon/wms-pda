package com.siirisoft.aim.wms.service.impl.outbound.ext;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.siirisoft.aim.wms.entity.outbound.WmsOutboundOrderDetail;
import com.siirisoft.aim.wms.entity.outbound.WmsOutboundOrderLine;
import com.siirisoft.aim.wms.mapper.outbound.ext.WmsOutboundOrderLineMapperExt;
import com.siirisoft.aim.wms.service.outbound.ABWmsOutboundOrderService;
import com.siirisoft.aim.wms.service.outbound.IWmsOutboundOrderDetailService;
import com.siirisoft.aim.wms.service.outbound.IWmsOutboundOrderHeadService;
import com.siirisoft.aim.wms.service.outbound.IWmsOutboundOrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @User DKY
 * @Date 2020/6/16
 * @Description ABWmsOutboundOrderService实现类
 */
@Service
public class ABWmsOutboundOrderServiceImpl implements ABWmsOutboundOrderService {

    @Autowired
    private IWmsOutboundOrderHeadService iWmsOutboundOrderHeadService;

    @Autowired
    private IWmsOutboundOrderDetailService iWmsOutboundOrderDetailService;

    @Autowired
    private IWmsOutboundOrderLineService iWmsOutboundOrderLineService;

    @Autowired
    private WmsOutboundOrderLineMapperExt wmsOutboundOrderLineMapperExt;


    @Override
    @Transactional
    public boolean deleteOutboundHeadOrder(int headId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("head_id", headId);
        if (iWmsOutboundOrderHeadService.removeById(headId)) {
            iWmsOutboundOrderLineService.remove(wrapper);
            iWmsOutboundOrderDetailService.remove(wrapper);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean deleteOutboundHeadOrder(List<Integer> headIdList) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.in("head_id", headIdList);
        if (iWmsOutboundOrderHeadService.removeByIds(headIdList)) {
            iWmsOutboundOrderLineService.remove(wrapper);
            iWmsOutboundOrderDetailService.remove(wrapper);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean saveWmsInboundOrderDetail(int lineId, List<WmsOutboundOrderDetail> list) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("line_id", lineId);
        int count = iWmsOutboundOrderDetailService.count(wrapper);
        if (count > 0) {
            iWmsOutboundOrderDetailService.remove(wrapper);
        }
        if (iWmsOutboundOrderDetailService.saveOrUpdateBatch(list)) {
            return true;
        }
        return  false;
    }


    @Override
    @Transactional
    public boolean deleteLineAndDetail(int lineId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("line_id", lineId);
        if (iWmsOutboundOrderLineService.removeById(lineId)) {
            iWmsOutboundOrderDetailService.remove(wrapper);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean deleteLineAndDetail(List<Integer> lineIdList) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.in("line_id", lineIdList);
        if (iWmsOutboundOrderLineService.removeByIds(lineIdList)) {
            iWmsOutboundOrderDetailService.remove(wrapper);
            return true;
        }
        return false;
    }

    @Override
    public int getMaxLineNumber(WmsOutboundOrderLine wmsOutboundOrderLine) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("head_id", wmsOutboundOrderLine.getHeadId());
        return wmsOutboundOrderLineMapperExt.getMaxLineNumber(wrapper);
    }
}
