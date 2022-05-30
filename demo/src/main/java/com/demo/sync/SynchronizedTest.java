package com.demo.sync;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynchronizedTest {

    public static ConcurrentHashMap<String, Order> orderArrayList = new ConcurrentHashMap<>();  //已有的订单列表

    static {
        orderArrayList.put("test1", new Order("test1"));
        orderArrayList.put("test2", new Order("test2"));
        orderArrayList.put("test3", new Order("test3"));
    }

    public static ConcurrentHashMap<String, String> blockingDeque = new ConcurrentHashMap<>();   //被用户预定的订单列表

    public static void main(String[] args) {
        SynchronizedTest synchronizedTest = new SynchronizedTest();

        ExecutorService executor = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 20; i++) {
            int finalI = i;
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    String orderName = "test" + finalI;
                    String companyName = "company" + finalI;
                    if (blockingDeque.containsValue(orderName)) {
                        System.out.println(orderName + "-- 该订单已经被预定");
                        return;
                    }
                    if (orderArrayList.get(orderName) == null) {
                        System.out.println(orderName + "-- 该订单已经被预定");
                    } else {
                        Order order = orderArrayList.remove(orderName);
                        blockingDeque.put(companyName, orderName);
                        System.out.println(orderName + "-- 该订单被预定 --" + companyName);

                        if (true) {
                            // todo  修改订单状态或存库,完成订单后删除
                            blockingDeque.remove(companyName);
                            order.setStatus("代付款");
                            order.setCompanyName(companyName);
                        } else {
                            // 若取消购买，则将数据重新加入抢购列表
                            order.setCompanyName(null);
                            order.setStatus(null);
                            blockingDeque.remove(companyName);
                            orderArrayList.put(orderName, order);
                        }
                    }
                }
            });
        }
        executor.shutdown();
    }

}
