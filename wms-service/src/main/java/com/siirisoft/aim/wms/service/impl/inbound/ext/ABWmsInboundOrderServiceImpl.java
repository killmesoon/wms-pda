package com.siirisoft.aim.wms.service.impl.inbound.ext;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.siirisoft.aim.wms.entity.inbound.WmsInboundOrderDetail;
import com.siirisoft.aim.wms.entity.inbound.WmsInboundOrderHead;
import com.siirisoft.aim.wms.service.inbound.ABWmsInboundOrderService;
import com.siirisoft.aim.wms.service.inbound.IWmsInboundOrderDetailService;
import com.siirisoft.aim.wms.service.inbound.IWmsInboundOrderHeadService;
import com.siirisoft.aim.wms.service.inbound.IWmsInboundOrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @User DKY
 * @Date 2020/6/16
 * @Description ABWmsInboundOrderDetailService实现类
 */
@Service
public class ABWmsInboundOrderServiceImpl implements ABWmsInboundOrderService {

    @Autowired
    private IWmsInboundOrderDetailService iWmsInboundOrderDetailService;

    @Autowired
    private IWmsInboundOrderLineService iWmsInboundOrderLineService;

    @Autowired
    private IWmsInboundOrderHeadService inboundOrderHeadService;

    @Override
    @Transactional
    public boolean saveWmsInboundOrderDetail(int lineId, List<WmsInboundOrderDetail> list) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("line_id", lineId);
        int count = iWmsInboundOrderDetailService.count(wrapper);
        if (count > 0) {
            iWmsInboundOrderDetailService.remove(wrapper);
        }
        if (iWmsInboundOrderDetailService.saveOrUpdateBatch(list)) {
            return true;
        }
        return false;
    }


    @Override
    @Transactional
    public boolean deleteLineAndDetailByLineId(int lineId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("line_id", lineId);
        iWmsInboundOrderLineService.removeById(lineId);
        iWmsInboundOrderDetailService.remove(wrapper);
        return true;
    }


    @Override
    @Transactional
    public boolean deleteHeadAndAll(int headId) {
        //除了删除入库单头表，还要删除line表和detail 因为headId关联
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("head_id", headId);
        inboundOrderHeadService.removeById(headId);
        iWmsInboundOrderLineService.remove(queryWrapper); //删除行表关联
        iWmsInboundOrderDetailService.remove(queryWrapper); //删除明细表关联
        return true;
    }


    @Override
    public boolean deleteHeadAndAllList(List<Integer> headIdList) {
        //除了删除入库单头表，还要删除line表和detail 因为headId关联
        QueryWrapper queryWrapper = new QueryWrapper();
        inboundOrderHeadService.removeByIds(headIdList);
        queryWrapper.in("head_id", headIdList);
        iWmsInboundOrderLineService.remove(queryWrapper); //删除行表关联
        iWmsInboundOrderDetailService.remove(queryWrapper); //删除明细表关联
        return true;
    }
}
