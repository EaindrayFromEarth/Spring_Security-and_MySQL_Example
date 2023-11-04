

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.springsecuritymysql")

@EntityScan(basePackages = "com.springsecuritymysql.springsecuritymysql.model") // Adjust the package name
@EnableJpaRepositories(basePackages = "com.springsecuritymysql.springsecuritymysql.repository")
public class SpringsecuritymysqlApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringsecuritymysqlApplication.class, args);
    }
}
