package com.jc.performancetests.dozer;

import com.jc.performancetests.Converter;
import com.jc.performancetests.model.destination.DestinationCode;
import com.jc.performancetests.model.destination.Order;
import com.jc.performancetests.model.source.SourceCode;
import com.jc.performancetests.model.source.SourceOrder;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

/**
 * @author ChenJunChang
 */
public class DozerConverter implements Converter {
    private final Mapper mapper;

    public DozerConverter() {
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        dozerBeanMapper.addMapping(DozerConverter.class.getResourceAsStream("/dozer-mapping.xml"));
        this.mapper = dozerBeanMapper;
    }

    @Override
    public Order convert(SourceOrder sourceOrder) {
        return mapper.map(sourceOrder, Order.class);
    }

    @Override
    public DestinationCode convert(SourceCode sourceCode) {
        return mapper.map(sourceCode, DestinationCode.class);
    }
}
