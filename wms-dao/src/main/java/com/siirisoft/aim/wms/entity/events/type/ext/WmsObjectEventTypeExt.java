package com.siirisoft.aim.wms.entity.events.type.ext;

import com.siirisoft.aim.wms.entity.events.type.WmsObjectEventType;
import lombok.Data;

/**
 * @User DKY
 * @Date 2020/6/17
 * @Description WmsObjectEventType扩展
 */
@Data
public class WmsObjectEventTypeExt extends WmsObjectEventType {
    private String calculatorTypeDic; //库存变化类型名称
}
