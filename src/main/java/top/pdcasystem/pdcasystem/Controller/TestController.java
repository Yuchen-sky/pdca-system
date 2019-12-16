package top.pdcasystem.pdcasystem.Controller;


import com.google.code.kaptcha.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import top.pdcasystem.pdcasystem.Entity.PlanLog;
import top.pdcasystem.pdcasystem.Service.PlanPostService;
import top.pdcasystem.pdcasystem.Util.CommonUtil;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class TestController {
    @Autowired
    PlanPostService planPostService;

    @Autowired
    Producer captcheProducer;

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping("/")
    @ResponseBody
    public String test(){
        return "love me";
    }

    @RequestMapping("/http")//最复杂的方法
    public  void http(HttpServletRequest request, HttpServletResponse response){
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());

       response.setContentType("text/html;charset-utf-8");
       try(PrintWriter writer=response.getWriter();){

           writer.write("<h1>牛客网</h1>");
       }catch (IOException e){
           e.printStackTrace();
       }
    }


    @RequestMapping(path="/getstu",method = RequestMethod.GET)
    @ResponseBody
    public String getstu(
            @RequestParam(name="currect",required=false,defaultValue = "1") int current
    ){
        return "number is "+current;
    }


    @RequestMapping(path="/getstu/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getstu2(
            @PathVariable("id") int id
    ){
        return "number is "+id;
    }


    @RequestMapping(path="/setstu",method = RequestMethod.POST)
    @ResponseBody
    public String getstu3(
            String name ,int age
    ){
        return "name "+name+" age "+age;
    }


    @RequestMapping(path="/getstu2/{id}",method = RequestMethod.GET)//默认返回html
    public ModelAndView getstu4(
            @PathVariable("id") int id
    ){
       ModelAndView mav=new ModelAndView();
       mav.addObject("i","wo");
        mav.addObject("love","ai");
        mav.addObject("you",id);
        mav.setViewName("demo/demo.html");
        return mav;
    }


    @RequestMapping(path="/getstu3/{id}",method = RequestMethod.GET)//默认返回html
    public String getstu5(
            @PathVariable("id") int id, Model model
    ){
        model.addAttribute("i","wo");
        model.addAttribute("love","laohu");
        model.addAttribute("you",id);
      return  "demo/demo.html";
    }


    @RequestMapping(path = "emp",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getemp(){
        Map<String,Object> emp=new HashMap<>();
        emp.put("acc","nihao");
        emp.put("a",222);
        return emp;
    }


    @RequestMapping(path = "emps",method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String,Object>> getemps(){
        List< Map<String,Object>> cc=new ArrayList<>();
        Map<String,Object> emp=new HashMap<>();
        emp.put("acc","nihao");
        emp.put("a",222);
        Map<String,Object> emp2=new HashMap<>();
        emp2.put("acc","nibuhao");
        emp2.put("a",333);
        cc.add(emp);
        cc.add(emp2);
        return cc;
    }


    // cookie测试
    @RequestMapping(path = "/cookie", method = RequestMethod.GET)
    @ResponseBody
    public String setCookies(HttpServletResponse response){
        Cookie cookie = new Cookie("code", CommonUtil.generateUUID());
        // 不让它所有子域名都发,该路径和子路径下
        cookie.setPath("/cookie");
        // cookie默认在内存里，得加生效时间，设置cookie 生效时间
        cookie.setMaxAge(60 * 10);// 10分钟
        response.addCookie(cookie);

        return "set cookie";
    }

    @RequestMapping(path = "/cookie/get", method = RequestMethod.GET)
    @ResponseBody
    public String getCookies(@CookieValue("code") String code){
        System.out.println(code);
        return "get cookie";
    }

    // session
    @RequestMapping(path = "/session", method = RequestMethod.GET)
    @ResponseBody
    public String setSession(HttpSession session){
        session.setAttribute("id", 1);
        session.setAttribute("name", "sfdsafasdfasdfasdfsadfdddd");
        return "set session";
    }

    @RequestMapping(path = "/session/get", method = RequestMethod.GET)
    @ResponseBody
    public String getSession(HttpSession session){
        System.out.println(session.getAttribute("id"));
        System.out.println(session.getAttribute("name"));
        return "set session";
    }

    @RequestMapping(path={"/kaptcha"},method = RequestMethod.GET)
    public  void getKaptcha(HttpServletResponse response, HttpSession httpSession){
        String text = captcheProducer.createText();
        BufferedImage image = captcheProducer.createImage(text);

        httpSession.setAttribute("kaptcha", text);

        response.setContentType("image/png");
        try{
            OutputStream os = response.getOutputStream();
            ImageIO.write(image, "png", os);
        }catch (IOException e){
            logger.error("验证码失败"+e.getMessage());
        }

    }

    // ajax示例
    @RequestMapping(path={"/ajax"},method = RequestMethod.POST)
    @ResponseBody
    public String testAjax(String name, int age) {
        System.out.println(name);
        System.out.println(age);
        return CommonUtil.getJSONString(0,"操作成功");
    }

    @RequestMapping(path = "/error", method = RequestMethod.GET)
    public String getError(){
        return "error/500";
    }
}
