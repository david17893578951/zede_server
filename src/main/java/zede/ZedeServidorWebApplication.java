package zede;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
@EnableAutoConfiguration
@EntityScan(basePackages={"zede.model"})
@EnableJpaRepositories(basePackages = "zede.repository")
@ComponentScan(basePackages={"zede.controller", "zede.utilities"})
@EnableTransactionManagement

public class ZedeServidorWebApplication {
	  @RequestMapping("/")
	    @ResponseBody
	    String home() {
	      return "Hello World!";
	    }

	public static void main(String[] args) {
		SpringApplication.run(ZedeServidorWebApplication.class, args);
	}
}
