package top.pdcasystem.pdcasystem.Service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import top.pdcasystem.pdcasystem.Dao.LoginTicketMapper;
import top.pdcasystem.pdcasystem.Dao.UserMapper;
import top.pdcasystem.pdcasystem.Entity.LoginTicket;
import top.pdcasystem.pdcasystem.Entity.User;
import top.pdcasystem.pdcasystem.Util.CommonUtil;
import top.pdcasystem.pdcasystem.Util.MailClient;
import top.pdcasystem.pdcasystem.Util.PdcaSystemConstant;

import javax.rmi.CORBA.Util;
import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static java.lang.System.currentTimeMillis;


@Service
public class UserService implements PdcaSystemConstant {
    public static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserMapper userMapper;

    @Autowired
    LoginTicketMapper loginTicketMapper;

    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${pdcasystem.path.domain}")
    private String domain;

    @Value("${pdcasystem.auth.user}")
    private String authUser;

    public User findUserById(int id){
        return userMapper.selectById(id);
    }

    public Map<String, Object> login(String username, String password, String validate, String kaptcha){
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isBlank(username)) {
            map.put("usernamemsg","用户名不能为空");
            return map;
        }
        if (StringUtils.isBlank(password) ){
            map.put("passwordmsg","密码不能为空");
            return map;
        }
        if (StringUtils.isBlank(validate)){
            map.put("validatemsg","验证码不能为空");
            return map;
        }
        if (!(validate.equals(kaptcha))){
            map.put("validatemsg","验证码不正确");
            return map;
        }

        // 验证账号
        User user = userMapper.selectByName(username);
        if (user == null) {
            map.put("usernamemsg","该账号不存在");
            return map;
        }

        // 验证密码
        if (!(user.getPassword().equals(CommonUtil.md5(password + user.getSalt())))) {
            map.put("passwordmsg","密码不正确");
            return map;
        }

        userMapper.updateLoginTime(user.getId());
        logger.debug("登录成功");

        Cookie cookie = new Cookie("daydayup", CommonUtil.generateUUID());
        /**UUID应该已经解决了此问题
        while (loginTicketMapper.selectByTicket(cookie.getValue()) != null) {
            cookie = new Cookie("daydayup", CommonUtil.generateUUID());
        }**/
        // cookie默认在内存里，得加生效时间，设置cookie 生效时间
        cookie.setMaxAge(60 * 60 * 24 * 30);// 30天
        // 不让它所有子域名都发,该路径和子路径下
        //cookie.setPath("/cookie");


        long timenow = System.currentTimeMillis();
        long onehour = 60 * 60 * 1000;
        long expire = timenow + onehour * 24 * 30;

        LoginTicket loginTicket =new LoginTicket();
        loginTicket.setUserid(user.getId());
        loginTicket.setExpire((int)(expire/1000));
        logger.debug("过期时间" + (expire/1000));
        loginTicket.setTicket(cookie.getValue());
        LoginTicket loginTicketBefore = loginTicketMapper.selectByUserId(user.getId());
        if (loginTicketBefore == null ){
           loginTicketMapper.insertTicket(loginTicket);
        } else {
            loginTicketMapper.updateTicket(user.getId(), loginTicket.getTicket());
            loginTicketMapper.updateExpire(loginTicket.getTicket(),loginTicket.getExpire());
        }

        map.put("cookie", cookie);
        return map;
    }


    public Map<String, Object> register(User user, String repassword){
        Map<String, Object> map = new HashMap<>();
        if (user == null) {
            throw new IllegalArgumentException("参数不能为空");
        }
        if (StringUtils.isBlank(user.getUsername())) {
            map.put("usernamemsg","账号不能为空");
            return map;
        }
        if (StringUtils.isBlank(user.getPassword())) {
            map.put("passwordmsg","密码不能为空");
            return map;
        }
        if (StringUtils.isBlank(user.getEmail())){
            map.put("emailmsg","邮箱不能为空");
            return map;
        }
        if (!(user.getPassword().equals(repassword))){
            map.put("passwordmsg","密码两次输入不同");
            return map;
        }

        // 验证账号
        User userReccord = userMapper.selectByName(user.getUsername());
        if (userReccord != null) {
            map.put("usernamemsg","该账号已存在");
            return map;
        }

        // 验证邮箱
        User userReccord2 = userMapper.selectByEmail(user.getEmail());
        if (userReccord2 != null) {
            map.put("emailmsg","该邮箱已被注册");
            return map;
        }


        if (!user.getUsername().equals(authUser)) {
            map.put("usernamemsg","暂不允许注册其它账号");
            logger.debug(authUser);
            logger.debug(user.getUsername());
            return map;
        }

        user.setSalt(CommonUtil.generateUUID().substring(0,5));
        user.setPassword(CommonUtil.md5(user.getPassword() + user.getSalt()));
        user.setType(0);
        user.setActivationCode(CommonUtil.generateUUID());
        //user.setHeaderUrl(String.format("%dt.png",new Random().nextInt(1000)));

        logger.debug(user.toString());
        userMapper.insertUser(user);
        logger.debug("插入成功");

        // 激活邮件
        Context context = new Context();
        context.setVariable("email", user.getEmail());

        // contextpath +
        String url = domain +  "/activation/" + user.getId() + "/" + user.getActivationCode();
        context.setVariable("url", url);
        String content = templateEngine.process("/mail/activate", context);
        mailClient.sendMail(user.getEmail(), "参与计划App", content);

        return map;
    }


    public int activation(int userid, String code){
        User user = userMapper.selectById(userid);
        if (user.getStatus() == 1) {
            return ACTIVATION_REPEAT;
        } else if (user.getActivationCode().equals(code)) {
            userMapper.updateStatus(userid, 1);
            return ACTIVATION_SUCCESS;
        } else {
            return ACTIVATION_FAILARE;
        }
    }

    public void logout(String ticket){
        loginTicketMapper.updateStatus(ticket,1);
    }

    public LoginTicket findLoginTicket(String ticket){
        return  loginTicketMapper.selectByTicket(ticket);
    }

    public int updateHeader(int userId, String headerUrl){
        return userMapper.updateHeader(userId, headerUrl);
    }
}
