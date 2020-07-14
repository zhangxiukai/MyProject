package com.demo.map;

import java.util.IdentityHashMap;
import java.util.Map.Entry;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

public class CommonsKeyMap {

    public static void main(String[] args) {
//        MultiValuedMap<String, String> map = new ArrayListValuedHashMap<>();
//        map.put("key1", "value1");
//        map.put("key1", "value2");
//        map.put("key1", "value2");
//        System.out.println(map);

        IdentityHashMap<String, String> hashMap = new IdentityHashMap<>();
        hashMap.put(new String("key1"), "value1");
        hashMap.put(new String("key1"), "test2");
        hashMap.put(new String("key1"), "value3");
        hashMap.forEach((k, v) -> {
            System.out.println(k + "------" + v);
        });
    }
}
