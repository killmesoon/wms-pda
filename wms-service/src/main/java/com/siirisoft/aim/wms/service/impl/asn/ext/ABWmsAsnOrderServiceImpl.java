package com.siirisoft.aim.wms.service.impl.asn.ext;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.siirisoft.aim.wms.entity.asn.WmsErpAsnDetail;
import com.siirisoft.aim.wms.entity.asn.WmsErpAsnHead;
import com.siirisoft.aim.wms.entity.asn.WmsErpAsnLine;
import com.siirisoft.aim.wms.entity.asn.ext.WmsErpAsnCondition;
import com.siirisoft.aim.wms.entity.autonumber.WmsDocAutonumber;
import com.siirisoft.aim.wms.entity.data.Result;
import com.siirisoft.aim.wms.entity.inbound.WmsInboundOrderDetail;
import com.siirisoft.aim.wms.entity.inbound.WmsInboundOrderHead;
import com.siirisoft.aim.wms.entity.inbound.WmsInboundOrderLine;
import com.siirisoft.aim.wms.mapper.asn.WmsErpAsnDetailMapper;
import com.siirisoft.aim.wms.mapper.asn.WmsErpAsnHeadMapper;
import com.siirisoft.aim.wms.mapper.asn.WmsErpAsnLineMapper;
import com.siirisoft.aim.wms.mapper.asn.ext.WmsErpAsnLineMapperExt;
import com.siirisoft.aim.wms.service.asn.ABWmsAsnOrderService;
import com.siirisoft.aim.wms.service.asn.IWmsErpAsnDetailService;
import com.siirisoft.aim.wms.service.asn.IWmsErpAsnHeadService;
import com.siirisoft.aim.wms.service.asn.IWmsErpAsnLineService;
import com.siirisoft.aim.wms.service.autonumber.IWmsDocAutonumberService;
import com.siirisoft.aim.wms.service.inbound.IWmsInboundOrderDetailService;
import com.siirisoft.aim.wms.service.inbound.IWmsInboundOrderHeadService;
import com.siirisoft.aim.wms.service.inbound.IWmsInboundOrderLineService;
import com.siirisoft.aim.wms.utils.AutoDocNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private IWmsDocAutonumberService iWmsDocAutonumberService;

    @Autowired
    private IWmsInboundOrderHeadService inboundOrderHeadService;

    @Autowired
    private IWmsInboundOrderLineService iWmsInboundOrderLineService;

    @Autowired
    private IWmsInboundOrderDetailService iWmsInboundOrderDetailService;

    @Autowired
    private WmsErpAsnLineMapperExt wmsErpAsnLineMapperExt;


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
    @Transactional
    public boolean asnOrderCheck(List<WmsErpAsnHead> list) {
        //先加头
        for (WmsErpAsnHead asn: list) {
            WmsInboundOrderHead inboundHead = new WmsInboundOrderHead();
            inboundHead.setCreationDate(new Date());
            inboundHead.setSourceDocType("63");
            inboundHead.setSourceDocNum(asn.getAsnNumber());
            inboundHead.setDocStatus("51");
            inboundHead.setDocType("66");
            inboundHead.setSupplierId(asn.getSupplierId());
            inboundHead.setSourceDocId(asn.getHeadId());
            inboundHead.setPlantCode(asn.getPlantCode());
            inboundHead.setPlantName(asn.getPlantName());
            inboundHead.setPlanTime(asn.getPlanDeliverDate());
            inboundHead.setNote(asn.getNote());

            //生成自动订单号
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("lookupcode","wms_inbound_order_type");
            wrapper.eq("doc_type", "P");
            WmsDocAutonumber docAuto= iWmsDocAutonumberService.getOne(wrapper);
            if (docAuto != null) {
                String autoDocNumber = AutoDocNumberGenerator.getAutoDocNumber(docAuto);
                inboundHead.setDocNumber(autoDocNumber);
            }
            if (inboundOrderHeadService.saveOrUpdate(inboundHead)) {
                Integer headId = inboundHead.getHeadId();

                //查询 关联行
                QueryWrapper lineWrapper = new QueryWrapper();
                lineWrapper.eq("head_id", asn.getHeadId());
                List<WmsErpAsnLine> asnLineList = iWmsErpAsnLineService.list(lineWrapper);

                for (WmsErpAsnLine asnLine: asnLineList ) {
                    WmsInboundOrderLine inboundLine = new WmsInboundOrderLine();
                    inboundLine.setHeadId(headId);
                    inboundLine.setCreationDate(new Date());
                    inboundLine.setLineNum(asnLine.getLineNum());
                    inboundLine.setSourceDocId(asnLine.getLineId());
                    inboundLine.setSourceDocNum(asn.getAsnNumber());
                    inboundLine.setSourceDocType("63");
                    inboundLine.setItemId(asnLine.getItemId());
                    inboundLine.setWarehouseId(asnLine.getWarehouseId());
                    inboundLine.setNote(asnLine.getNote());

                    //保存
                    if (iWmsInboundOrderLineService.saveOrUpdate(inboundLine)) {
                        Integer lineId = inboundLine.getLineId();
                        //查明细
                        QueryWrapper detailWrapper = new QueryWrapper();
                        detailWrapper.eq("line_id", asnLine.getLineId());
                        List<WmsErpAsnDetail> asnDetailList = iWmsErpAsnDetailService.list(detailWrapper);
                        List<WmsInboundOrderDetail> inboundOrderDetailList = new ArrayList();
                        for (WmsErpAsnDetail asnDetail: asnDetailList) {
                            WmsInboundOrderDetail inboundDetail = new WmsInboundOrderDetail();
                            inboundDetail.setAdvBarcode(asnDetail.getDSequenceNum());
                            inboundDetail.setCreationDate(new Date());
                            inboundDetail.setLastUpdateDate(new Date());
                            inboundDetail.setHeadId(headId);
                            inboundDetail.setLineId(lineId);
                            inboundDetail.setNote(asnDetail.getNote());
                            inboundOrderDetailList.add(inboundDetail);
                        }
                        iWmsInboundOrderDetailService.saveOrUpdateBatch(inboundOrderDetailList);
                    }
                }

            }

        }
        return true;
    }

    @Override
    public Integer getMaxLineNumber(WmsErpAsnLine wmsErpAsnLine) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("head_id", wmsErpAsnLine.getHeadId());
        return wmsErpAsnLineMapperExt.findMaxLineNumber(wrapper);
    }
}
