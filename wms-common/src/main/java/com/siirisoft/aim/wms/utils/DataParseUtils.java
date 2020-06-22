package com.siirisoft.aim.wms.utils;

//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import org.apache.commons.io.FileUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * @User DKY
 * @Date 2020/5/22
 * @Description 格式解析工具
 */
public class DataParseUtils {
    /**
     * 读取json文件
     * @param
     * @return
     */
    public static JSONObject readJson() {
        String jsonStr = "";
        String fileName = "D:\\wms-svn\\wms\\wms-web\\src\\main\\resources\\data.json";
        try {
            File file = new File(fileName);
            if (file.exists()) {
                String content = FileUtils.readFileToString(file, "utf-8");
                JSONObject jsonObject = JSON.parseObject(content);
                return jsonObject;
            }
//            FileReader fileReader = new FileReader(jsonFile);
//            InputStreamReader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
//            int ch = 0;
//            StringBuffer sb = new StringBuffer();
//            while ((ch =reader.read())!= -1) {
//                sb.append(ch);
//            }
//            fileReader.close();
//            reader.close();
//            jsonStr = sb.toString();
//            JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
