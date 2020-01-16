package com.jc.performancetests;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * @author ChenJunChang
 * @date 2020/1/16 1:18 下午
 */
public interface Copier {

    /**
     * copy object
     *
     * @param source 源对象
     * @param clazz  目标对象
     * @return T
     */
    <E, T> T copy(E source, Class<? extends T> clazz);

    /**
     * copy list
     *
     * @param source 源对象
     * @param clazz  目标对象
     * @return List<T>
     */
    <E, T> List<T> copyList(List<E> source, Class<? extends T> clazz);

    /**
     * get constructor of clazz
     *
     * @param clazz class
     * @param <T>   type T
     * @return Constructor of clazz
     */
    default <T> Constructor<? extends T> getConstructor(Class<? extends T> clazz) {
        Constructor<? extends T> constructor;
        try {
            constructor = clazz.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            String msg = "getConstructor exception, clazz" + clazz;
            throw new ConverterException(msg);
        }
        return constructor;
    }
}
