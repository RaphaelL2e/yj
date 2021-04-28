package com.agricultural.service.impl;

import com.agricultural.constants.UserConstant;
import com.agricultural.dao.UserDetailMapper;
import com.agricultural.enums.ErrorCodeEnum;
import com.agricultural.form.LoginForm;
import com.agricultural.form.RegisterForm;
import com.agricultural.form.UpdatePasswordForm;
import com.agricultural.pojo.User;
import com.agricultural.dao.UserMapper;
import com.agricultural.pojo.UserDetail;
import com.agricultural.response.ServerResponse;
import com.agricultural.service.IUserService;
import com.agricultural.util.Md5PswdUtil;
import com.agricultural.util.SnowflakeIdWorker;
import com.agricultural.util.TokenUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lee
 * @since 2021-04-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private final UserMapper userMapper;
    private final UserDetailMapper userDetailMapper;



    public UserServiceImpl(UserMapper userMapper,
                           UserDetailMapper userDetailMapper) {
        this.userMapper = userMapper;
        this.userDetailMapper = userDetailMapper;
    }

    @Override
    public ServerResponse register(RegisterForm registerForm) {
        SnowflakeIdWorker sw = new SnowflakeIdWorker(1,1);
        User u = new User();
        u.setId(String.valueOf(sw.nextId()));
        u.setUsername(registerForm.getUsername());
        u.setPassword(Md5PswdUtil.md5Pswd(registerForm.getPassword()));
        try {
            userMapper.insert(u);
            userDetailMapper.insert(new UserDetail()
                    .setUserId(u.getId())
                    .setName("default_"+u.getId().toString())
                    .setAvatar(UserConstant.USER_IMAGE)
            );
            return ServerResponse.createBySuccess();
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("register err");
        }

    }

    @Override
    public ServerResponse login(LoginForm loginForm) {
        User u = new User();
        u.setUsername(loginForm.getUsername());
        u.setPassword(Md5PswdUtil.md5Pswd(loginForm.getPassword()));
        QueryWrapper<User> articleQueryWrapper = new QueryWrapper<>();
        articleQueryWrapper.setEntity(u);
        User u1 = userMapper.selectOne(articleQueryWrapper);
        if (null == u1) {
            return ServerResponse.createByErrorCodeMessage(ErrorCodeEnum.PASSWORD_ERROR);
        } else {
            System.out.println(111);
            String token;
            try {
                token = TokenUtils.createToken(Long.valueOf(u1.getId()),u1.getIndetity());
            } catch (Exception e) {
                System.out.println(e);
                return ServerResponse.createByErrorMessage(e.getMessage());
            }
            Map<String, String> map = new HashMap<>();
            map.put("token", token);
            map.put("user_id", String.valueOf(u1.getId()));
            map.put("indetity", String.valueOf(u1.getIndetity()));
            return ServerResponse.createBySuccess(map);
        }
    }

    @Override
    public ServerResponse updatePassword(UpdatePasswordForm updatePasswordForm) {
        User u = new User();
        u.setPassword(Md5PswdUtil.md5Pswd(updatePasswordForm.getPassword()));
        u.setUsername(updatePasswordForm.getUsername());
        QueryWrapper<User> articleQueryWrapper = new QueryWrapper<>();
        articleQueryWrapper.setEntity(u);
        User nu = new User();
        nu.setPassword(Md5PswdUtil.md5Pswd(updatePasswordForm.getNewPassword()));
        try {
            userMapper.update(nu,articleQueryWrapper);
        }catch (Exception e){
            return ServerResponse.createByErrorMessage(e.getMessage());
        }
        return ServerResponse.createBySuccess();
    }
}
