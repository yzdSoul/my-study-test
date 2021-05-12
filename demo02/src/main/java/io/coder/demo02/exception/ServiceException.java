package io.coder.demo02.exception;

import io.coder.demo02.constants.ServiceExceptionEnum;

/**
 * Created by yzd on 2020/11/19
 */
public final class ServiceException extends RuntimeException {

    /**
     * 错误码
     */
    private final Integer code;

    public ServiceException(ServiceExceptionEnum serviceExceptionEnum) {
        //使用父类的 message 字段
        super(serviceExceptionEnum.getMessage());
        //设置错误码
        this.code = serviceExceptionEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }
}
