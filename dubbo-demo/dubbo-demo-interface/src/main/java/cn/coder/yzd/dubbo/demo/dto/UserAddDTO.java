package cn.coder.yzd.dubbo.demo.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by yzd on 2020/11/27
 */
public class UserAddDTO implements Serializable {

    /**
     * 昵称
     */
    @NotEmpty(message = "昵称不能为空")
    @Length(min = 5, max = 16, message = "账号长度为 5-16 位")
    private String name;
    /**
     * 性别
     */
    @NotNull(message = "性别不能为空")
    private Integer gender;

    public String getName() {
        return name;
    }

    public UserAddDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getGender() {
        return gender;
    }

    public UserAddDTO setGender(Integer gender) {
        this.gender = gender;
        return this;
    }

    @Override
    public String toString() {
        return "UserAddDTO{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                '}';
    }
}
