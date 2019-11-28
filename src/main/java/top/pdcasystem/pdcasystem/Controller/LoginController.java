package top.pdcasystem.pdcasystem.Controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.pdcasystem.pdcasystem.Entity.User;
import top.pdcasystem.pdcasystem.Service.UserService;
import top.pdcasystem.pdcasystem.Util.CommonUtil;
import top.pdcasystem.pdcasystem.Util.PdcaSystemConstant;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;


@Controller
public class LoginController implements PdcaSystemConstant {
    public static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public  String getRegister(Model model){
        return "register";
    }


    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public  String setRegister(Model model, User user, String repassword){
        Map<String, Object> map = userService.register(user, repassword);
        if (map == null || map.isEmpty()) {
            model.addAttribute("msg", "注册成功，我们已经成功发送了一封注册邮件，请查收");
            model.addAttribute("redirect", "/getplan");
            model.addAttribute("redirectinfo", "查看目前进展");
            return "registerResponse";
        } else {
            model.addAttribute("usernamemsg",map.get("usernamemsg"));
            model.addAttribute("emailmsg",map.get("emailmsg"));;
            model.addAttribute("passwordmsg",map.get("passwordmsg"));;
            return "register";
        }
    }


    @RequestMapping(path = "/activation/{userid}/{code}", method = RequestMethod.GET)
    @ResponseBody
    public  String activation(Model model, @PathVariable("userid") int userid, @PathVariable("code") String code){
        int result = userService.activation(userid, code);
        String response = "";
        if (result == ACTIVATION_SUCCESS) {
            response = "激活成功";
            model.addAttribute("msg", "激活成功");
            model.addAttribute("redirect", "/getplan");
            model.addAttribute("redirectinfo", "查看目前进展");
        } else if (result == ACTIVATION_REPEAT) {
            response = "您已经激活过了";
            model.addAttribute("msg", "已经激活过了");

        } else {
            response = "激活失败";
            model.addAttribute("msg", "激活失败");
            model.addAttribute("redirect", "/getplan");
            model.addAttribute("redirectinfo", "查看目前进展");
        }

        return response;
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public  String getLogin(Model model){
        return "login";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public  String setLogin(HttpServletResponse response, Model model, User user, String validate, HttpSession httpSession){
        String kaptcha = (String)httpSession.getAttribute("kaptcha");
        Map<String, Object> map = userService.login(user.getUsername(), user.getPassword(), validate, kaptcha);

        if (map.get("cookie") != null) {
            model.addAttribute("msg", "登陆成功");
            model.addAttribute("redirect", "/getplan");
            model.addAttribute("redirectinfo", "查看目前进展");
            response.addCookie((Cookie) map.get("cookie"));
            return "registerResponse";
        } else {
            httpSession.removeAttribute("kaptcha");// 注意该刷新方法在高并发下会更加降低效率
            model.addAttribute("usernamemsg",map.get("usernamemsg"));
            model.addAttribute("validatemsg",map.get("validatemsg"));;
            model.addAttribute("passwordmsg",map.get("passwordmsg"));;
            return "login";
        }
    }


}

