package com.baeldung.performancetests.fastjson;

import com.alibaba.fastjson.JSON;
import com.baeldung.performancetests.Converter;
import com.baeldung.performancetests.model.destination.DestinationCode;
import com.baeldung.performancetests.model.destination.Order;
import com.baeldung.performancetests.model.destination.OrderStatus;
import com.baeldung.performancetests.model.source.SourceCode;
import com.baeldung.performancetests.model.source.SourceOrder;

/**
 * @author ChenJunChang
 * @date 2020/1/15 4:14 下午
 */
public class FastjsonConverter implements Converter {
    @Override
    public Order convert(SourceOrder sourceOrder) {
        Order order = JSON.parseObject(JSON.toJSONString(sourceOrder), Order.class);
        order.setOrderStatus(OrderStatus.valueOf(sourceOrder.getStatus().name()));
        return order;
    }

    @Override
    public DestinationCode convert(SourceCode sourceCode) {
        return JSON.parseObject(JSON.toJSONString(sourceCode), DestinationCode.class);
    }
}
