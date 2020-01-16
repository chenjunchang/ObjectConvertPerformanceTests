package com.baeldung.performancetests.beancopier;

import com.alibaba.fastjson.JSON;
import com.baeldung.performancetests.ConverterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanCopier;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author ChenJunChang
 */
public class BeanCopierUtils {
    static Logger log = LoggerFactory.getLogger(BeanCopierUtils.class);

    private static ConcurrentHashMap<String, BeanCopier> cache = new ConcurrentHashMap<>();

    private BeanCopierUtils() {
    }

    /**
     * @param source 源对象
     * @param dest    目标对象
     * @return
     * @throws ConverterException
     */
    public static <T> T copyBeanProperties(Object source, T dest) {
        if (source == null || dest == null) {
            return null;
        }
        String key = source.getClass().getSimpleName() + dest.getClass().getSimpleName();
        BeanCopier copier = cache.get(key);
        if (copier == null) {
            copier = createBeanCopier(source.getClass(), dest.getClass(), key);
        }
        copier.copy(source, dest, null);
        return dest;
    }

    public static <T> List<T> copyListBeanPropertiesToList(List<?> source, List<T> dest, Class<T> clazz) {
        if (source == null || dest == null || clazz == null) {
            return null;
        }
        T t;
        for (Object o : source) {
            try {
                t = clazz.newInstance();
                dest.add(copyBeanProperties(o, t));
            } catch (InstantiationException | IllegalAccessException e) {
                String msg = "[SpringBeanUtils.copy] exception, src = " + JSON.toJSONString(source) + ", clazz" + clazz;
                log.warn(msg, e);
                throw new ConverterException(msg);
            }
        }
        return dest;
    }

    @SuppressWarnings({"rawtypes"})
    private static BeanCopier createBeanCopier(Class sourceClass, Class targetClass, String cacheKey) {
        BeanCopier copier = BeanCopier.create(sourceClass, targetClass, false);
        cache.putIfAbsent(cacheKey, copier);
        return copier;
    }
}
