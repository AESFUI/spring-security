package ml.sadriev.security.config;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitializr extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * Specify {@code @Configuration} and/or {@code @Component} classes for the {@linkplain
     * #createRootApplicationContext() root application context}.
     *
     * @return the configuration for the root application context, or {@code null} if creation and registration of a
     * root context is not desired
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
//        return new Class<?>[] {ContextConfig.class};
        return null;
    }

    /**
     * Specify {@code @Configuration} and/or {@code @Component} classes for the {@linkplain
     * #createServletApplicationContext() Servlet application context}.
     *
     * @return the configuration for the Servlet application context, or {@code null} if all configuration is specified
     * through root config classes.
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {WebMvcConfig.class};
    }

    /**
     * Specify the servlet mapping(s) for the {@code DispatcherServlet} &mdash; for example {@code "/"}, {@code "/app"},
     * etc.
     *
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * Specify filters to add and map to the {@code DispatcherServlet}.
     *
     * @return an array of filters or {@code null}
     */
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter cef = new CharacterEncodingFilter();
        cef.setEncoding("UTF-8");
        cef.setForceEncoding(true);
        return new Filter[] {new HiddenHttpMethodFilter(), cef};
    }


}
