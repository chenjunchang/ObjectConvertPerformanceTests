package com.baeldung.performancetests.gson;

import com.baeldung.performancetests.Converter;
import com.baeldung.performancetests.model.destination.DestinationCode;
import com.baeldung.performancetests.model.destination.Order;
import com.baeldung.performancetests.model.destination.OrderStatus;
import com.baeldung.performancetests.model.source.SourceCode;
import com.baeldung.performancetests.model.source.SourceOrder;
import com.google.gson.Gson;

/**
 * @author ChenJunChang
 * @date 2020/1/15 4:14 下午
 */
public class GsonConverter implements Converter {
    Gson gson = new Gson();

    @Override
    public Order convert(SourceOrder sourceOrder) {
        Order order = gson.fromJson(gson.toJson(sourceOrder), Order.class);
        order.setOrderStatus(OrderStatus.valueOf(sourceOrder.getStatus().name()));
        return order;
    }

    @Override
    public DestinationCode convert(SourceCode sourceCode) {
        return gson.fromJson(gson.toJson(sourceCode), DestinationCode.class);

    }
}
