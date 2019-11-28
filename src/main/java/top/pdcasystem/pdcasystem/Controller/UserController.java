package top.pdcasystem.pdcasystem.Controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import top.pdcasystem.pdcasystem.Entity.User;
import top.pdcasystem.pdcasystem.Service.UserService;
import top.pdcasystem.pdcasystem.Util.CommonUtil;
import top.pdcasystem.pdcasystem.Util.HostHolder;
import top.pdcasystem.pdcasystem.annotation.LoginRequired;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Value("${pdcasystem.path.upload}")
    private String upload;

    @Value("${pdcasystem.path.domain}")
    private String domain;

    @Autowired
    UserService userService;

    @Autowired
    private HostHolder hostHolder;

    @LoginRequired
    @RequestMapping(path = "/setting", method = RequestMethod.GET)
    public String setting(){
        return "setting";
    }

    @LoginRequired
    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public String upload(MultipartFile headerImage, Model model){
        if (headerImage == null) {
            model.addAttribute("error", "没选文件");
            return "setting";
        }

        String fileName = headerImage.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        if (StringUtils.isBlank(suffix)) {
            model.addAttribute("error", "格式不正确");
        }

        // 生成随机文件名
        fileName = CommonUtil.generateUUID() + suffix;
        // 确定存放的路径
        File dest = new File(upload + "/" +fileName);

        try {
            headerImage.transferTo(dest);
        } catch (IOException e) {
            logger.error("上传文件失败" + e.getMessage());
            throw new RuntimeException("上传文件失败");
        }

        //更新当前头像路径（web访问路径，dasfsdaf.png）
        User user = hostHolder.getUser();
        String headerUrl = domain + "/user/header" + fileName;
        userService.updateHeader(user.getId(),headerUrl);

        return "redirect:/getplan";
    }

    @LoginRequired
    @RequestMapping(path = "/header/{fileName}", method = RequestMethod.GET)
    public void setting(@PathVariable("fileName") String fileName, HttpServletResponse response){
        // 服务器存放路径
        fileName = upload + "/" + fileName;
        // 文件猴嘴
        String usffix = fileName.substring(fileName.lastIndexOf("."));
        // 响应图片
        response.setContentType("image/" + usffix);// ?
        try (
                FileInputStream fis = new FileInputStream(fileName);
                OutputStream os = response.getOutputStream();
                ){
            byte[] buffer = new byte[1024];
            int b = 0;
            while ((b = fis.read(buffer)) != -1) {
                os.write(buffer, 0, b);
            }
        } catch (IOException e) {
            logger.error("读取文件失败" + e.getMessage());
        }

    }
}
