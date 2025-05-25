package SCRMS.major.example.SCRM.project.SwaggerConfig;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

   @Bean
   public OpenAPI customOpenApi(){
       return  new OpenAPI()
               .info(new Info()
                       .title("Student Course Registration Management System")
                       .version("1.0.0")
                       .description("API documentation for Course registration system management  - Course Enrollment, Payment, and Admin management system.")
                       .contact(new Contact()
                               .name(" Shadab Qureshi")
                               .email("officialshadab27@gmail.com")
                               .url("https://github.com/Shaad27s"))
                       .license(new License().name("Apache 2.0").url("http://springdoc.org")));

    }
}
