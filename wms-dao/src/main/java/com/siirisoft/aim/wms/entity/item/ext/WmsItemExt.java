package com.siirisoft.aim.wms.entity.item.ext;

import com.siirisoft.aim.wms.entity.item.WmsItem;
import lombok.Data;

/**
 * @User DKY
 * @Date 2020/5/21
 * @Description WmsItemExt扩展
 */
@Data
public class WmsItemExt extends WmsItem {
    private String itemTypeDic; //物料类型
    private String primaryUomDic; //主单位
    private String receiveWarehouse; //默认接收仓库
    private String inboundWarehouse; //默认入库仓
    private String codeTypeDic; //管理模式
}
