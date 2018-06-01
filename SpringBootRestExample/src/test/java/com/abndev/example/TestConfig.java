/**
 * Project ..... TreatmentSchedule<br>
 * <br>
 * Author ...... Andrey.B.Nikitin<br>
 * E-Mail ...... ABN.Java@GMail.com<br>
 * Created ..... 2018-05-15<br>
 * <br>
 */
package com.abndev.example;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 
 * @since 2018.05.15
 * @author annik
 */
@Configuration
@ComponentScan("com.abndev.example")
@EnableWebMvc
public class TestConfig {

//    @Bean
//    public UrlBasedViewResolver setupViewResolver() {
//        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
//        resolver.setPrefix("/tempplates/");
//        resolver.setSuffix(".html");
//        resolver.setViewClass(JstlView.class);
//        return resolver;
//    }
 
}
