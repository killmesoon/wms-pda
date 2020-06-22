package com.siirisoft.aim.wms.utils;

import com.siirisoft.aim.wms.entity.autonumber.WmsDocAutonumber;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @User DKY
 * @Date 2020/6/15
 * @Description 单据编码自动生成器
 */
public class AutoDocNumberGenerator {
    public synchronized static String getAutoDocNumber(WmsDocAutonumber wmsDocAutonumber) {

        String result = "";

        //时间字符串
//        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

        //获得位数
        Integer sequenceNum = wmsDocAutonumber.getSequenceNum();

        //获得前缀
        String prefix = wmsDocAutonumber.getPrefix();

        //生成指定位数
        String number = AutoDocNumberGenerator.getNumber(sequenceNum);

        result = prefix + number;

        //组合流水号前一部分
//        String uid_prefix = format.format(new Date());

//        if (docNumber !=null && docNumber.contains(uid_prefix) && docNumber.contains(wmsDocAutonumber.getPrefix())) {
//            String uid_end = docNumber.substring(10, 14);
//            int endNum = Integer.parseInt(uid_end);
//            int tmpNum = 10000 + endNum + 1;
//            result = wmsDocAutonumber.getPrefix()+ uid_prefix +  AutoDocNumberGenerator.subStr("" + tmpNum, 1);
//        } else {
//            result = wmsDocAutonumber.getPrefix() +  uid_prefix + "0001";
//        }
//        result = wmsDocAutonumber.getPrefix() + uid_prefix + Math.round((Math.random()+1) * 1000);
        return result;
    }


    public static String subStr(String str, int start) {
        if (str == null || str.equals("") || str.length() == 0)
            return "";
        if (start < str.length()) {
            return str.substring(start);
        } else {
            return "";
        }
    }

    public static String getNumber(int count) {
        StringBuffer sb = new StringBuffer();
        String str = "0123456789";
        Random r = new Random();
        for(int i=0;i<count;i++){
            int num = r.nextInt(str.length());
            sb.append(str.charAt(num));
            str = str.replace((str.charAt(num)+""), "");
        }
        return sb.toString();
    }
}
