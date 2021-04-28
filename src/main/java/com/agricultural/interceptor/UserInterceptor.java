/**
 * FileName:UserInterceptor
 * Date:19-1-20 下午6:55
 * Description:用户模块拦截器
 */
package com.agricultural.interceptor;


import com.agricultural.constants.SysConstant;
import com.agricultural.redis.IRedisRepository;
import com.agricultural.response.ServerResponse;
import com.agricultural.util.JsonUtils;
import com.agricultural.util.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class UserInterceptor implements HandlerInterceptor {
    @Autowired
    private IRedisRepository redisRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*
         * 获取access_token
         */
        String accessToken = request.getHeader(SysConstant.HEADER_TOKEN);
        //未获取到token
        if (null == accessToken) {
            accessToken = request.getParameter(SysConstant.TOKEN_REQUEST_PARAM);
        }
        response.setHeader(SysConstant.HTTP_HEADER_CONTENT_TYPE, SysConstant.CONTENT_TYPE_APPLICATION_JSON);

        ServerResponse resultWrapper = null;
        if (null == accessToken) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resultWrapper = ServerResponse.createByErrorMessage("token认证失败");
            response.getWriter().println(JsonUtils.toJson(resultWrapper));
            return false;
        }
        Long userId = TokenUtils.getUID(accessToken);
        if (-1L == userId) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resultWrapper = ServerResponse.createByErrorMessage("token无效");
            response.getWriter().println(JsonUtils.toJson(resultWrapper));
            return false;
        }
        request.getSession().setAttribute("UserID", userId);
        return true;
    }

}
