package com.jc.performancetests.spring;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.jc.performancetests.ConverterException;
import com.jc.performancetests.Copier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author ChenJunChang
 */
public class SpringBeanUtilsImpl implements Copier {
    Logger log = LoggerFactory.getLogger(SpringBeanUtilsImpl.class);

    @Override
    public <E, T> T copy(E source, Class<? extends T> clazz) {
        Constructor<? extends T> constructor = getConstructor(clazz);
        T t;
        try {
            t = constructor.newInstance();
            BeanUtils.copyProperties(source, t);
        } catch (Exception e) {
            String msg = "copy exception, src = " + JSON.toJSONString(source) + ", clazz" + clazz;
            log.error(msg, e);
            throw new ConverterException(msg);
        }
        return t;
    }

    @Override
    public <E, T> List<T> copyList(List<E> source, Class<? extends T> clazz) {
        if (source == null) {
            return null;
        }
        if (source.isEmpty()) {
            return Lists.newArrayList();
        }
        Constructor<? extends T> constructor = getConstructor(clazz);
        List<T> dest = Lists.newArrayList();
        try {
            for (Object o : source) {
                T t = constructor.newInstance();
                BeanUtils.copyProperties(o, t);
                dest.add(t);
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            String msg = "copyList exception, src = " + JSON.toJSONString(source) + ", clazz" + clazz;
            log.error(msg, e);
            throw new ConverterException(msg);
        }
        return dest;
    }

}
