package top.pdcasystem.pdcasystem.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class TestController {
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

}
