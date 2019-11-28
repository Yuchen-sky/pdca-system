package top.pdcasystem.pdcasystem.Controller.intercepter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import top.pdcasystem.pdcasystem.Util.HostHolder;
import top.pdcasystem.pdcasystem.annotation.LoginRequired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.logging.Handler;

@Component
public class LoginRequireIntercepter implements HandlerInterceptor {
    @Autowired
    HostHolder hostHolder;

    // 在controller之前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
        if (handler instanceof HandlerMethod) {
           HandlerMethod handlerMethod =  (HandlerMethod) handler;
           Method method = handlerMethod.getMethod();
           LoginRequired loginRequired = method.getAnnotation(LoginRequired.class);
           if (loginRequired != null && hostHolder.getUser() == null) {
               response.sendRedirect(request.getContextPath() + "/login");
               return false;
           }
        }
        return true;
    }
}
