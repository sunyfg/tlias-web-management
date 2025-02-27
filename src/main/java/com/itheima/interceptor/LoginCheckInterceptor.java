package com.itheima.interceptor;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override // 目标资源方法运行前运行，返回true：放行，放回false 不放行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("LoginCheckInterceptor preHandle");

        // 1.获取请求url
        String url = request.getRequestURL().toString();
        log.info("请求的url：{}", url);

        // 2.判断请求url中是否包含login，如果包含，说明是登录操作，放行
        if (url.contains("login")) {
            log.info("登录操作，直接放行...");
            return true;
        }

        // 3.获取请求头中的令牌（token）
        String token = request.getHeader("token");

        // 4.判断令牌是否存在，如果不存在，返回错误结果（未登录）
        if (!StringUtils.hasLength(token)) {
            log.info("请求头token为空，返回未登录的信息");
            Result error = Result.error("NOT_LOGIN");
            // 手动转换 对象->json
            String notJson = JSON.toJSONString(error);
            response.getWriter().write(notJson);
            return false;
        }

        // 5.解析token，如果解析失败，返回错误结果（未登录）
        try {
            JwtUtils.parseJwt(token);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析令牌失败，返回未登录错误信息");
            Result error = Result.error("NOT_LOGIN");
            String notJson = JSON.toJSONString(error);
            response.getWriter().write(notJson);
            return false;
        }

        // 6.放行
        log.info("令牌合法，放行");
        return true;
    }

    @Override // 目标资源方法运行后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("LoginCheckInterceptor postHandle");
    }

    @Override // 视图渲染完毕后运行，最后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("LoginCheckInterceptor afterCompletion");
    }
}
