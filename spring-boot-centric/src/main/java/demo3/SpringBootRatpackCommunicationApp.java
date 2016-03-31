package demo3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import ratpack.func.Action;
import ratpack.handling.Chain;
import ratpack.http.client.HttpClient;
import ratpack.http.client.ReceivedResponse;
import ratpack.jackson.Jackson;
import ratpack.spring.config.EnableRatpack;

import java.net.URI;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableRatpack
@Controller
public class SpringBootRatpackCommunicationApp {

  @Bean
  Action<Chain> chain() {
    return chain -> chain
      .get(ctx -> ctx.render("Hello from Ratpack in Spring Boot!"))
      .get("json", ctx -> {
        Map<String, String> map = new HashMap<>();
        map.put("date", Instant.now().toString());
        ctx.render(Jackson.json(map));
      })
      .get("boot", ctx -> {
        HttpClient client = ctx.get(HttpClient.class);
        client.get(new URI("http://localhost:8080"))
          .map(ReceivedResponse::getBody)
          .map(body -> "Received from Spring Boot: " + body.getText())
          .then(ctx::render);
      });
  }

  @RequestMapping("/")
  @ResponseBody String bootRoot() {
    return "This is Spring Boot";
  }

  @RequestMapping("/ratpack")
  @ResponseBody Map bootRatpack() {
    RestTemplate restTemplate = new RestTemplate();
    return restTemplate.getForObject("http://localhost:5050/json", Map.class);
  }

  public static void main(String[] args) {
    SpringApplication.run(SpringBootRatpackCommunicationApp.class, args);
  }
}
