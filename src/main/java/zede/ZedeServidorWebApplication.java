package zede;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration
@EntityScan(basePackages={"zede.model"})
@EnableJpaRepositories(basePackages = "zede.repository")
@ComponentScan(basePackages={"zede.controller", "zede.utilities"})
@EnableTransactionManagement
public class ZedeServidorWebApplication {
	public static void main(String[] args) {
		SpringApplication.run(ZedeServidorWebApplication.class, args);
	}
}
