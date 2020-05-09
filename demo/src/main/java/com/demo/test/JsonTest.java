package com.demo.test;

import com.alibaba.fastjson.JSONObject;
import java.util.List;

public class JsonTest {
    private final static String json = "["
        + "{\"sId\": \"dd879b76-3928-45e7-a167-9bb26cbc97c8\",\"keyBy\": \"male\",\"type\": \"SingleEvent\",\"action\": \"score\"},"
        + "{\"sId\": \"dd879b76-3928-45e7-a167-9bb26cbc97c8\",\"keyBy\": \"male\",\"type\": \"SingleEvent\",\"action\": \"score\"},"
        + "{\"sId\": \"dd879b76-3928-45e7-a167-9bb26cbc97c8\",\"keyBy\": \"male\",\"type\": \"SingleEvent\",\"action\": \"score\"}"
        + "]";

    public static void main(String[] args) {

        List<JSONObject> list = JSONObject.parseObject(json, List.class);
        list.forEach(ss -> {
            System.out.println(ss.get("sId"));
        });

    }
}
