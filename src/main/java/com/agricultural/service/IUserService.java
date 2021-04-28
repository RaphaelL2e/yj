package com.agricultural.service;

import com.agricultural.form.LoginForm;
import com.agricultural.form.RegisterForm;
import com.agricultural.form.UpdatePasswordForm;
import com.agricultural.pojo.User;
import com.agricultural.response.ServerResponse;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lee
 * @since 2021-04-09
 */
public interface IUserService extends IService<User> {

    ServerResponse register(RegisterForm registerForm);

    ServerResponse login(LoginForm loginForm);

    ServerResponse updatePassword(UpdatePasswordForm updatePasswordForm);
}
