package top.pdcasystem.pdcasystem.Util;

import org.springframework.stereotype.Component;
import top.pdcasystem.pdcasystem.Entity.User;

/**
 * 持有用户信息
 */
@Component
public class HostHolder {
    private  ThreadLocal<User> users = new ThreadLocal<User>();

    public void setUser(User user) {
        users.set(user);
    }

    public User getUser() {
        return users.get();
    }

    public void clear() {
        users.remove();
    }
}
