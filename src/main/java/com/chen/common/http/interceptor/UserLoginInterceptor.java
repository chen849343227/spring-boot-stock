package com.chen.common.http.interceptor;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * author long
 * <p>
 * date 17-9-6
 * <p>
 * desc
 */
public class UserLoginInterceptor implements HandlerInterceptor{
    public static Logger logger = Logger.getLogger(UserLoginInterceptor.class);
    /**
     * 用来存储不拦截的路径
     */
    private static final String[] IGNORE_URI = {"/nothing"};

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        logger.info("开始preHandle,判断请求是否需要拦截");
        boolean flag = false;
        String servletPath = httpServletRequest.getServletPath();
        logger.info("请求路径是: "+servletPath);
        // 检测是否为需要拦截的请求
        for (String s : IGNORE_URI) {
            if (servletPath.contains(s)) {
                logger.info("该请求不需要拦截");
                flag = true;
            }

        }
        // 需要拦截处理的请求
        if (!flag) {
            // 获取存储在session域的用户
            String phoneNumber = (String) httpServletRequest.getSession().getAttribute("user");
            if (phoneNumber == null) {
                logger.warn("拦截请求:用户未登录，禁止直接访问，返回登录页面");
                httpServletRequest.setAttribute("message", "请先登录");
                // 服务器内部转发，可以带回request
                httpServletRequest.getRequestDispatcher("/index").forward(httpServletRequest, httpServletResponse);
            }
            else {
                logger.info("拦截请求:用户已经登录，可以成功浏览网页");
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        logger.info("拦截请求之后");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        logger.info("控制器处理完成之后");
    }
}
