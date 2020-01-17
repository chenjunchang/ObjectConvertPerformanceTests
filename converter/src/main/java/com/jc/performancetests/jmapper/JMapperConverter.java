package com.jc.performancetests.jmapper;

import com.googlecode.jmapper.JMapper;
import com.googlecode.jmapper.api.JMapperAPI;
import com.jc.performancetests.Converter;
import com.jc.performancetests.model.destination.DestinationCode;
import com.jc.performancetests.model.destination.Order;
import com.jc.performancetests.model.source.SourceCode;
import com.jc.performancetests.model.source.SourceOrder;

/**
 * @author ChenJunChang
 */
public class JMapperConverter implements Converter {
    JMapper<Order, SourceOrder> realLifeMapper;
    JMapper<DestinationCode, SourceCode> simpleMapper;

    public JMapperConverter() {
        JMapperAPI api = new JMapperAPI().add(JMapperAPI.mappedClass(Order.class));
        realLifeMapper = new JMapper<>(Order.class, SourceOrder.class, api);
        JMapperAPI simpleApi = new JMapperAPI().add(JMapperAPI.mappedClass(DestinationCode.class));
        simpleMapper = new JMapper<>(DestinationCode.class, SourceCode.class, simpleApi);
    }

    @Override
    public Order convert(SourceOrder sourceOrder) {
        return realLifeMapper.getDestination(sourceOrder);
    }

    @Override
    public DestinationCode convert(SourceCode sourceCode) {
        return simpleMapper.getDestination(sourceCode);
    }
}
