/**
 * Project ..... SpringBootRestExample<br>
 * <br>
 * Author ...... Andrey.B.Nikitin<br>
 * E-Mail ...... ABN.Java@GMail.com<br>
 * Created ..... 2018-06-05<br>
 * <br>
 */
package app;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2 config.
 * 
 * @since 2018.08.11
 * @author Andre.B.Nikitin
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final ArrayList<VendorExtension> VENDOR_EXTENSIONS = new ArrayList<VendorExtension>();
//    static {
//        VENDOR_EXTENSIONS.add( e )
//    }

    private static final Contact DEFAULT_CONTACT = new Contact( /* name */ "Andrew B. Nikitin",
        /* url */ null,
        /* email */ "ABN.Java@gmail.com" );

    private static final ApiInfo DEAFULT_API_INFO = new ApiInfo( /* title */ "Api Documentation",
        /* description */ "Api Documentation",
        /* version */ "1.0",
        /* termsOfServiceUrl */ "urn:tos",
        DEFAULT_CONTACT,
        /* license */ "Apache 2.0",
        /* licenseUrl */ "http://www.apache.org/licenses/LICENSE-2.0",
        VENDOR_EXTENSIONS );

    private static final Set<String> DEFAULT_PRODUCES = new HashSet<String>();
    static {
        DEFAULT_PRODUCES.add( "application/json" );
    }

    @Bean
    public Docket api() {

        return new Docket( DocumentationType.SWAGGER_2 ).apiInfo( DEAFULT_API_INFO )
            .produces( DEFAULT_PRODUCES );
    }
}
