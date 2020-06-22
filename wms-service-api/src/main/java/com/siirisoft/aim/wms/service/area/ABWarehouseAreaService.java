package com.siirisoft.aim.wms.service.area;

import java.util.List;

/**
 * @User DKY
 * @Date 2020/6/5
 * @Description 区域扩展service
 */
public interface ABWarehouseAreaService {
    boolean isWarehouseAreaDelete(int areaId);

    boolean isWarehouseAreaListDelete(List<Integer> areaIdList);
}
