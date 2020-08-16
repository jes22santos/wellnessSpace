package ie.cct.wellnessSpace.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
@EnableWebSecurity
public class WebConfig implements WebMvcConfigurer {

    /*
        Class to configure the template resolver using Thymeleaf
        This code is based on code developed on tutorial by The Thymeleaf Team(2017)
     */
   @Bean
    @Description("Thymeleaf template resolver serving HTML 5")
    public ClassLoaderTemplateResolver templateResolver() {

        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();

        templateResolver.setPrefix("templates/");
        templateResolver.setCacheable(false);
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setOrder(0);
        templateResolver.setCheckExistence(true);



        return templateResolver;
    }
    @Bean
    public ClassLoaderTemplateResolver templateResolverAdmin() {

        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();


        templateResolver.setPrefix("templates/admin/");
        templateResolver.setCacheable(false);
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setOrder(1);
        templateResolver.setCheckExistence(true);


        return templateResolver;
    }
    @Bean
    public ClassLoaderTemplateResolver templateResolverUser() {

        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();


        templateResolver.setPrefix("templates/user/");
        templateResolver.setCacheable(false);
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setOrder(2);
        templateResolver.setCheckExistence(true);


        return templateResolver;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }
}
