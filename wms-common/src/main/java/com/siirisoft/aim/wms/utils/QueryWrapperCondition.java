package com.siirisoft.aim.wms.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @User DKY
 * @Date 2020/5/18
 * @Description 查询条件封装工具
 */
public class QueryWrapperCondition {

    //创建缓存池
    private static final Map<String, List<Field>> CACHE = new HashMap<String, List<Field>>();

//    private List<Field> getFieldOfClass(Class<?> clazz) {
//        List<Field> fields = CACHE.get(clazz.getName());
//        if (fields == null) {
//            Field[] fieldsArr = clazz.getDeclaredFields();
//            fields = new ArrayList<Field>(fieldsArr.length);
//            for (Field each: fieldsArr) {
//                each
//            }
//        }
//    }
}
