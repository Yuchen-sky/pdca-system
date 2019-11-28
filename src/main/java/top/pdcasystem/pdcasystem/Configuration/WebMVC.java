package top.pdcasystem.pdcasystem.Configuration;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.pdcasystem.pdcasystem.Controller.intercepter.AlphaIntercapter;
import top.pdcasystem.pdcasystem.Controller.intercepter.LoginIntercepter;
import top.pdcasystem.pdcasystem.Controller.intercepter.LoginRequireIntercepter;
import top.pdcasystem.pdcasystem.annotation.LoginRequired;

@Configuration
public class WebMVC implements WebMvcConfigurer {
    @Autowired
    private AlphaIntercapter alphaIntercapter;

    @Autowired
    private LoginIntercepter loginIntercepter;

    @Autowired
    private LoginRequireIntercepter loginRequireIntercepter;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(alphaIntercapter).excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpeg", "/**/*.jpg", "**/resources/")
        .addPathPatterns("/getsetplan");
        registry.addInterceptor(loginIntercepter).excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpeg", "/**/*.jpg", "**/resources/");

        registry.addInterceptor(loginRequireIntercepter).excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpeg", "/**/*.jpg", "**/resources/");

    }
}
