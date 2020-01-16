package com.baeldung.performancetests.spring;

import com.alibaba.fastjson.JSON;
import com.baeldung.performancetests.ConverterException;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ChenJunChang
 */
public class SpringBeanUtils {
    static Logger log = LoggerFactory.getLogger(SpringBeanUtils.class);

    private SpringBeanUtils() {
    }

    public static <E, T> T copy(E source, Class<? extends T> clazz) {
        T t;
        try {
            t = clazz.newInstance();
            BeanUtils.copyProperties(source, t);
        } catch (Exception e) {
            String msg = "[SpringBeanUtils.copy] exception, src = " + JSON.toJSONString(source) + ", clazz" + clazz;
            log.warn(msg, e);
            throw new ConverterException(msg);
        }
        return t;
    }

    public static <E, T> List<T> copyList(List<E> source, Class<? extends T> clazz) {

        if (CollectionUtils.isEmpty(source)) {
            return Lists.newArrayList();
        }

        return source.stream().map(bean -> copy(bean, clazz)).collect(Collectors.toList());
    }

}
