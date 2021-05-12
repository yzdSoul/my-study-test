package cn.coder.yzd.dubbo.demo.xml.provider.service;
import cn.coder.yzd.dubbo.demo.api.UserRpcService;
import cn.coder.yzd.dubbo.demo.dto.UserAddDTO;
import cn.coder.yzd.dubbo.demo.dto.UserDTO;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;

/**
 * Created by yzd on 2020/11/26
 */
@Service
public class UserRpcServiceImpl implements UserRpcService  {
    @Override
    public UserDTO get(Integer id) {
        return new UserDTO().setId(id)
                .setName("没有昵称：" + id)
                .setGender(id % 2 + 1); // 1 - 男；2 - 女
    }

    @Override
    public Integer add(UserAddDTO addDTO) throws ConstraintViolationException {
        return (int) (System.currentTimeMillis() / 1000);
    }
}
