package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import converter.BillConverter;
import converter.ServiceConverter;
// import converter.ServicesClientsConverter;

import java.util.Locale;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"controller", "dao", "config", "converter"})
public class ServletConfig  implements WebMvcConfigurer {
    // @Autowired
    // ServicesClientsConverter servicesClientsConverter;
    @Autowired
    ServiceConverter serviceConverter;
    @Autowired
    BillConverter billConverter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        // registry.addConverter(servicesClientsConverter);
        registry.addConverter(serviceConverter);
        registry.addConverter(billConverter);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Bean(name = "localeResolver")
    public LocaleResolver localeResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(new Locale("ru_RU"));
        return sessionLocaleResolver;
    }

    @Bean(name = "viewResolver")
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}
