package com.jc.performancetests.gson;

import com.google.gson.Gson;
import com.jc.performancetests.Converter;
import com.jc.performancetests.model.destination.DestinationCode;
import com.jc.performancetests.model.destination.Order;
import com.jc.performancetests.model.destination.OrderStatus;
import com.jc.performancetests.model.source.SourceCode;
import com.jc.performancetests.model.source.SourceOrder;

/**
 * @author ChenJunChang
 * @date 2020/1/15 4:14 下午
 */
public class GsonConverter implements Converter {
    private static final Gson GSON = new Gson();

    @Override
    public Order convert(SourceOrder sourceOrder) {
        Order order = GSON.fromJson(GSON.toJson(sourceOrder), Order.class);
        order.setOrderStatus(OrderStatus.valueOf(sourceOrder.getStatus().name()));
        return order;
    }

    @Override
    public DestinationCode convert(SourceCode sourceCode) {
        return GSON.fromJson(GSON.toJson(sourceCode), DestinationCode.class);

    }
}
