package top.pdcasystem.pdcasystem.Controller.intercepter;

import org.apache.catalina.Host;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import top.pdcasystem.pdcasystem.Entity.LoginTicket;
import top.pdcasystem.pdcasystem.Entity.User;
import top.pdcasystem.pdcasystem.Service.UserService;
import top.pdcasystem.pdcasystem.Util.CookieUtil;
import top.pdcasystem.pdcasystem.Util.HostHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;

@Component
public class LoginIntercepter implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    @Autowired
    private HostHolder hostHolder;

    // 在controller之前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
        // 从cookie中获取
        String ticket = CookieUtil.getValue(request, "daydayup");

        if (ticket != null) {
            LoginTicket loginTicket = userService.findLoginTicket(ticket);
            // 检查凭证
            long time = System.currentTimeMillis();
            int timenow = (int) time / 1000;
            if (loginTicket != null && loginTicket.getStatus() == 0 && loginTicket.getExpire() > timenow) {
                User user = userService.findUserById(loginTicket.getUserid());
                // 线程问题
                hostHolder.setUser(user);

            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        User user = hostHolder.getUser();
        if (user != null && modelAndView != null) {
            modelAndView.addObject("loginUser",user);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        hostHolder.clear();
    }
}
