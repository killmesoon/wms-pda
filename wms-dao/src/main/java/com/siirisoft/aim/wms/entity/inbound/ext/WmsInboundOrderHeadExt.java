package com.siirisoft.aim.wms.entity.inbound.ext;

import com.siirisoft.aim.wms.entity.inbound.WmsInboundOrderHead;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @User DKY
 * @Date 2020/5/15
 * @Description WmsInboundOrderHead扩展类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class WmsInboundOrderHeadExt extends WmsInboundOrderHead implements Serializable {
    private static final long serialVersionUID = 1L;
    private String realDocType;
    private String realSourceDocType;
    private String realDocStatus;
    private List<Date> creationDateRange;
    private List<Date> planDateRange;
}
