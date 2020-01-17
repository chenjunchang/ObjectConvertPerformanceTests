package com.jc.performancetests.spring;

import com.jc.performancetests.Converter;
import com.jc.performancetests.Copier;
import com.jc.performancetests.model.destination.DestinationCode;
import com.jc.performancetests.model.destination.Order;
import com.jc.performancetests.model.source.SourceCode;
import com.jc.performancetests.model.source.SourceOrder;

/**
 * @author ChenJunChang
 * @date 2020/1/15 6:56 下午
 */
public class BeanUtilsConverter implements Converter {

    static final Copier COPIER = new SpringBeanUtilsImpl();

    @Override
    public Order convert(SourceOrder sourceOrder) {
        return convert(COPIER, sourceOrder);
    }

    @Override
    public DestinationCode convert(SourceCode sourceCode) {
        return COPIER.copy(sourceCode, DestinationCode.class);
    }
}
