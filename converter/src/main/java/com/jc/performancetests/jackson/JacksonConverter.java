package com.jc.performancetests.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jc.performancetests.Converter;
import com.jc.performancetests.ConverterException;
import com.jc.performancetests.model.destination.DestinationCode;
import com.jc.performancetests.model.destination.Order;
import com.jc.performancetests.model.destination.OrderStatus;
import com.jc.performancetests.model.source.SourceCode;
import com.jc.performancetests.model.source.SourceOrder;

/**
 * @author ChenJunChang
 * @date 2020/1/15 4:14 下午
 */
public class JacksonConverter implements Converter {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public Order convert(SourceOrder sourceOrder) {
        Order order;
        try {
            String json = OBJECT_MAPPER.writeValueAsString(sourceOrder);
            order = OBJECT_MAPPER.readValue(json, Order.class);
        } catch (JsonProcessingException e) {
            throw new ConverterException(e.getMessage(), e);
        }
        order.setOrderStatus(OrderStatus.valueOf(sourceOrder.getStatus().name()));
        return order;
    }

    @Override
    public DestinationCode convert(SourceCode sourceCode) {
        try {
            String json = OBJECT_MAPPER.writeValueAsString(sourceCode);
            return OBJECT_MAPPER.readValue(json, DestinationCode.class);
        } catch (JsonProcessingException e) {
            throw new ConverterException(e.getMessage(), e);
        }
    }
}
