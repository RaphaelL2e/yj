package com.agricultural.service.impl;

import com.agricultural.form.GetUserDetailForm;
import com.agricultural.form.UpdateUserDetailForm;
import com.agricultural.pojo.UserDetail;
import com.agricultural.dao.UserDetailMapper;
import com.agricultural.response.ServerResponse;
import com.agricultural.service.IUserDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lee
 * @since 2021-04-09
 */
@Service
public class UserDetailServiceImpl extends ServiceImpl<UserDetailMapper, UserDetail> implements IUserDetailService {
    private UserDetailMapper userDetailMapper;

    public UserDetailServiceImpl(UserDetailMapper userDetailMapper){
        this.userDetailMapper = userDetailMapper;
    }

    @Override
    public ServerResponse updateUserDetail(UserDetail ud) {
        try {
            userDetailMapper.updateById(ud);
        }catch (Exception e){
            return ServerResponse.createByErrorMessage(e.getMessage());
        }
        return ServerResponse.createBySuccess(userDetailMapper.selectById(ud.getUserId()));
    }

    @Override
    public ServerResponse getUserDetail(String userId) {
        UserDetail nud;
        try {
            nud = userDetailMapper.selectById(userId);
        }catch (Exception e){
            return ServerResponse.createByErrorMessage(e.getMessage());
        }
        return ServerResponse.createBySuccess(nud);
    }
}
