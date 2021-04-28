package com.agricultural.controller;


import com.agricultural.form.LoginForm;
import com.agricultural.form.RegisterForm;
import com.agricultural.form.UpdatePasswordForm;
import com.agricultural.pojo.User;
import com.agricultural.response.ServerResponse;
import com.agricultural.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.agricultural.controller.support.BaseController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lee
 * @since 2021-04-09
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    private final IUserService userService;

    public UserController(
            IUserService userService
    ) {
        this.userService = userService;
    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public ServerResponse register(@RequestBody RegisterForm registerForm, BindingResult bindingResult){
        validateParams(bindingResult);

        return userService.register(registerForm);
    }

    @ApiOperation("用户登陆")
    @PostMapping("/login")
    public ServerResponse login(@RequestBody LoginForm loginForm){
        return userService.login(loginForm);
    }

    @ApiOperation("修改密码")
    @PutMapping("/updatePassword")
    public ServerResponse updatePassword(@RequestBody UpdatePasswordForm updatePasswordForm){
        return userService.updatePassword(updatePasswordForm);
    }
}
