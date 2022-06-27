package com.codingdood.webapisecurity.config;
import com.codingdood.webapisecurity.utils.JWTUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Component
public class BookInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean isAllowReq = false;
        String token = request.getHeader("token");
        if(Objects.nonNull(token)) {
            try {
                JWTUtils.verifyToken(token);
                isAllowReq = true;
            } catch (Exception e) {
                response.setStatus(401);
                response.getWriter().println("Unauthorized");
            }
        }
        return isAllowReq;
    }
}
