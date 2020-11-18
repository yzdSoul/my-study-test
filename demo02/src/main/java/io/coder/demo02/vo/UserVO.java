package io.coder.demo02.vo;

/**
 * Created by yzd on 2020/11/18
 */
public class UserVO {

    private Integer id;

    private String username;

    public Integer getId() {
        return id;
    }

    public UserVO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserVO setUsername(String username) {
        this.username = username;
        return this;
    }
}
