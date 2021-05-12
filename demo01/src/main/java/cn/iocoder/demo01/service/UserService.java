package cn.iocoder.demo01.service;

import cn.iocoder.demo01.VO.UserVO;
import org.springframework.stereotype.Service;

/**
 * Created by yzd on 2020/11/18
 */
@Service
public class UserService {

    public UserVO get(Integer id) {
        return new UserVO().setId(id).setUsername("test");
    }
}
