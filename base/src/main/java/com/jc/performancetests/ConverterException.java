package com.jc.performancetests;

/**
 * @author ChenJunChang
 * @date 2020/1/16 10:46 上午
 */
public class ConverterException extends RuntimeException {

    public ConverterException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConverterException(String message) {
        super(message);
    }
}
