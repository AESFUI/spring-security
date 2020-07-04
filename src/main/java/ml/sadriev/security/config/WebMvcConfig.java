package ml.sadriev.security.config;

import ml.sadriev.security.service.UserDetailsServiceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

//@Configuration
@ComponentScan
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Add handlers to serve static resources such as images, js, and, css files from specific locations under web
     * application root, the classpath, and others.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/")
                .setCachePeriod(31556926);
    }

    @Bean
    InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    /**
     * Configure simple automated controllers pre-configured with the response status code and/or a view to render the
     * response body. This is useful in cases where there is no need for custom controller logic -- e.g. render a home
     * page, perform simple site URL redirects, return a 404 status with HTML content, a 204 with no content, and more.
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/free").setViewName("free");
        registry.addViewController("/admin").setViewName("admin");
    }
}
