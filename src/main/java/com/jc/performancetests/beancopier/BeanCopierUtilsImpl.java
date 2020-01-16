package com.jc.performancetests.beancopier;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.jc.performancetests.ConverterException;
import com.jc.performancetests.Copier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanCopier;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author ChenJunChang
 */
public class BeanCopierUtilsImpl implements Copier {
    static Logger log = LoggerFactory.getLogger(BeanCopierUtilsImpl.class);

    private static ConcurrentHashMap<String, BeanCopier> cache = new ConcurrentHashMap<>();

    @SuppressWarnings({"rawtypes"})
    private static BeanCopier createBeanCopier(Class sourceClass, Class targetClass, String cacheKey) {
        BeanCopier copier = BeanCopier.create(sourceClass, targetClass, false);
        cache.putIfAbsent(cacheKey, copier);
        return copier;
    }

    @Override
    public <E, T> T copy(E source, Class<? extends T> clazz) {
        if (source == null || clazz == null) {
            return null;
        }
        String key = source.getClass().getSimpleName() + clazz.getSimpleName();
        BeanCopier copier = cache.get(key);
        Constructor<? extends T> constructor = getConstructor(clazz);
        T dest;
        try {
            dest = constructor.newInstance();
        } catch (Exception e) {
            String msg = "copy exception, src = " + JSON.toJSONString(source) + ", clazz" + clazz;
            log.error(msg, e);
            throw new ConverterException(msg);
        }
        if (copier == null) {
            copier = createBeanCopier(source.getClass(), clazz, key);
        }
        copier.copy(source, dest, null);
        return dest;
    }

    @Override
    public <E, T> List<T> copyList(List<E> source, Class<? extends T> clazz) {
        if (source == null || clazz == null) {
            return null;
        }
        if (source.isEmpty()) {
            return Lists.newArrayList();
        }
        String key = source.getClass().getSimpleName() + clazz.getSimpleName();
        Constructor<? extends T> constructor = getConstructor(clazz);
        BeanCopier copier = cache.get(key);
        if (copier == null) {
            copier = createBeanCopier(source.get(0).getClass(), clazz, key);
        }
        List<T> dest = Lists.newArrayList();
        try {
            for (Object e : source) {
                T t = constructor.newInstance();
                copier.copy(e, t, null);
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
