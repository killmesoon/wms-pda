package com.siirisoft.aim.wms.service.impl.asn.pda;
import com.siirisoft.aim.wms.entity.asn.WmsErpAsnDetail;
import com.siirisoft.aim.wms.entity.asn.WmsErpAsnLine;
import com.siirisoft.aim.wms.entity.asn.ext.WmsErpAsnDetailExt;
import com.siirisoft.aim.wms.entity.events.WmsObjectEvents;
import com.siirisoft.aim.wms.entity.quantity.WmsItemOnhandQuantity;
import com.siirisoft.aim.wms.entity.sqlitem.WmsSglItem;
import com.siirisoft.aim.wms.mapper.events.WmsObjectEventsMapper;
import com.siirisoft.aim.wms.service.asn.IWmsErpAsnDetailService;
import com.siirisoft.aim.wms.service.asn.IWmsErpAsnLineService;
import com.siirisoft.aim.wms.service.asn.pda.ABPdaWmsAsnOrderService;
import com.siirisoft.aim.wms.service.quantity.IWmsItemOnhandQuantityService;
import com.siirisoft.aim.wms.service.sqlitem.IWmsSglItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @User DKY
 * @Date 2020/6/16
 * @Description ABPdaWmsAsnOrderService实现类
 */
@Service
public class ABPdaWmsAsnOrderServiceImpl implements ABPdaWmsAsnOrderService {

    @Autowired
    private IWmsSglItemService iWmsSglItemService;

    @Autowired
    private IWmsErpAsnDetailService iWmsErpAsnDetailService;

    @Autowired
    private IWmsItemOnhandQuantityService iWmsItemOnhandQuantityService;

    @Autowired
    private IWmsErpAsnLineService iWmsErpAsnLineService;

    @Autowired
    private WmsObjectEventsMapper wmsObjectEventsMapper;


    @Override
    @Transactional
    public synchronized boolean commitAsnOrderList(List<WmsErpAsnDetailExt> list) {
        boolean detailFlag = false;
        boolean sglItemFlag = false;
        boolean quantityFlag = false;
        boolean lineUpdateFlag = false;
        List<WmsSglItem> sglList = new ArrayList<>();
        List<WmsErpAsnDetail> updateList = new ArrayList<>();
        for (WmsErpAsnDetailExt asn : list) {

            //更新明细表
            WmsErpAsnDetail wmsErpAsnDetail = new WmsErpAsnDetail();
            wmsErpAsnDetail.setReceiveFlag(true);
            wmsErpAsnDetail.setDetailId(asn.getDetailId());
            updateList.add(wmsErpAsnDetail);


            //插入条码物料表
            WmsSglItem wmsSglItem = new WmsSglItem();
            wmsSglItem.setPlantCode(asn.getPlantCode());
            wmsSglItem.setPlantName(asn.getPlantName());
            wmsSglItem.setDSequenceNum(asn.getDSequenceNum());
            wmsSglItem.setItemId(asn.getItemId());
            wmsSglItem.setWarehouseId(asn.getWarehouseId());
            wmsSglItem.setLocatorId(asn.getLocatorId());
            wmsSglItem.setLayerNumber(asn.getLayerNumber());
            wmsSglItem.setSize(asn.getSize());
            wmsSglItem.setQcStatus(asn.getQcStatus());
            wmsSglItem.setPrimaryUom(asn.getPrimaryUom());
            wmsSglItem.setQuantity(asn.getQuantity());
            wmsSglItem.setSizeUom(asn.getSizeUom());
            wmsSglItem.setLength(asn.getLength());
            wmsSglItem.setWidth(asn.getWidth());
            wmsSglItem.setThickness(asn.getThickness());
            wmsSglItem.setWeightUom(asn.getWeightUom());
            wmsSglItem.setWeight(asn.getWeight());
            wmsSglItem.setSupplierId(asn.getSupplierId());
            wmsSglItem.setHeatNumber(asn.getHeatNumber());
            wmsSglItem.setSupplierLotNumber(asn.getSupplierLotNumber());
            wmsSglItem.setNote(asn.getNote());
            wmsSglItem.setItemTexture(asn.getItemTexture());
            wmsSglItem.setShipNumber(asn.getShipNumber());
            wmsSglItem.setSectionNum(asn.getSectionNum());
            wmsSglItem.setQcReport(asn.getQcReport());
            wmsSglItem.setCss(asn.getCcs());
            sglList.add(wmsSglItem);


            //更新现有量
            //批次号 船号+分段
            String lotNumber = asn.getShipNumber() + "-" + asn.getSectionNum();
            WmsItemOnhandQuantity wmsItemOnhandQuantity = new WmsItemOnhandQuantity();
            wmsItemOnhandQuantity.setItemId(asn.getItemId());
            wmsItemOnhandQuantity.setLocatorId(asn.getLocatorId());
            wmsItemOnhandQuantity.setLotNumber(lotNumber);
            wmsItemOnhandQuantity.setWarehouseId(asn.getWarehouseId());
            wmsItemOnhandQuantity.setLoctOnhand(1);
            wmsItemOnhandQuantity.setUomCode(asn.getPrimaryUom());
            wmsItemOnhandQuantity.setMergeFlag(true);
            quantityFlag = iWmsItemOnhandQuantityService.save(wmsItemOnhandQuantity);
            if (quantityFlag) {
                //现有量增加后 更新行信息的接收数量
                WmsErpAsnLine line = iWmsErpAsnLineService.getById(asn.getLineId());
                int receivedNumber = 1;
                if (line.getReceiveQty() != null) {
                    receivedNumber = line.getReceiveQty() + 1;
                }

                line.setReceiveQty(receivedNumber);
                //更新行的收货数量
                lineUpdateFlag = iWmsErpAsnLineService.saveOrUpdate(line);
            }

            //插入事件
            WmsObjectEvents wmsObjectEvents = new WmsObjectEvents();
            wmsObjectEvents.setItemId(asn.getItemId());
            wmsObjectEvents.setEventQty(1);
            wmsObjectEvents.setEventTime(new Date());
            wmsObjectEvents.setEventTypeId(1);
            wmsObjectEvents.setEventTypeCode("GD_PO_RCV");
            wmsObjectEventsMapper.insert(wmsObjectEvents);

        }
        detailFlag = iWmsErpAsnDetailService.saveOrUpdateBatch(updateList);
        sglItemFlag = iWmsSglItemService.saveBatch(sglList);

        if (sglItemFlag && quantityFlag && detailFlag && lineUpdateFlag) {
            return true;
        }
        return false;
    }
}
