package demo2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ratpack.func.Action;
import ratpack.handling.Chain;
import ratpack.spring.config.EnableRatpack;

@SpringBootApplication
@EnableRatpack
public class SpringBootCentricHelloWorld {

  @Bean
  Action<Chain> chain() {
    return chain -> chain
      .get(ctx -> ctx.render("Hello from Spring Boot!"));
  }

  public static void main(String[] args) {
    SpringApplication.run(SpringBootCentricHelloWorld.class, args);
  }
}
