package cn.coder.yzd.dubbo.demo.core;

/**
 * Created by yzd on 2020/11/27
 */
public class ServiceException extends RuntimeException {
    /**
     * 错误码
     */
    private Integer code;

    public ServiceException() { // 创建默认构造方法，用于反序列化的场景。
    }

    public ServiceException(ServiceExceptionEnum serviceExceptionEnum) {
        // 使用父类的 message 字段
        super(serviceExceptionEnum.getMessage());
        // 设置错误码
        this.code = serviceExceptionEnum.getCode();
    }

    public ServiceException(ServiceExceptionEnum serviceExceptionEnum, String message) {
        // 使用父类的 message 字段
        super(message);
        // 设置错误码
        this.code = serviceExceptionEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }
}
