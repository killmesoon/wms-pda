package com.siirisoft.aim.wms.entity.outbound.ext;

import com.siirisoft.aim.wms.entity.outbound.WmsOutboundOrderHead;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @User DKY
 * @Date 2020/5/19
 * @Description WmsOutboundOrderHead扩展类
 */
@Data
public class WmsOutboundOrderHeadExt extends WmsOutboundOrderHead {
    private static final long serialVersionUID = 1L;
    private String realDocType;
    private String realSourceDocType;
    private String realDocStatus;
    private List<Date> creationDateRange;
    private List<Date> planDateRange;
}
