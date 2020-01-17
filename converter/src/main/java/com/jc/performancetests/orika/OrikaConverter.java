package com.jc.performancetests.orika;

import com.jc.performancetests.Converter;
import com.jc.performancetests.model.destination.DestinationCode;
import com.jc.performancetests.model.destination.Order;
import com.jc.performancetests.model.source.SourceCode;
import com.jc.performancetests.model.source.SourceOrder;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class OrikaConverter implements Converter {
    private MapperFacade mapperFacade;

    public OrikaConverter() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(Order.class, SourceOrder.class).field("orderStatus", "status").byDefault().register();
        mapperFacade = mapperFactory.getMapperFacade();
    }

    @Override
    public Order convert(SourceOrder sourceOrder) {
        return mapperFacade.map(sourceOrder, Order.class);
    }

    @Override
    public DestinationCode convert(SourceCode sourceCode) {
        return mapperFacade.map(sourceCode, DestinationCode.class);
    }
}
