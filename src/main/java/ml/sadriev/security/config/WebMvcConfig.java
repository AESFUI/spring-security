package ml.sadriev.security.config;

import javax.annotation.Resource;
import ml.sadriev.security.enums.RoleType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@ComponentScan(basePackages = {"ml.sadriev.security"})
@EnableWebMvc
@EnableWebSecurity
public class WebMvcConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    @Resource
    private UserDetailsService userDetailsService;

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
        registry.addResourceHandler("/views/**")
                .addResourceLocations("/views/")
                .setCachePeriod(31556926);
    }

    @Bean
    InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/pages");
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

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/free").permitAll()
                .antMatchers("/admin").hasRole(RoleType.ADMIN.name())
                .anyRequest().authenticated()

                .and()
        .formLogin()
        .and()
        .logout().permitAll()
        .logoutSuccessUrl("/login")
        .and()
        .csrf().disable();
    }
}
