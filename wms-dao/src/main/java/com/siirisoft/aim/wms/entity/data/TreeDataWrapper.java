package com.siirisoft.aim.wms.entity.data;

import lombok.Data;

import java.util.List;

@Data
public class TreeDataWrapper {
    private String name;
    private String code;
    private Integer warehouseId;
    private Integer areaId;
    private Integer locatorId;
    private Integer layerNumber;
    private List<TreeDataWrapper> children;
}
