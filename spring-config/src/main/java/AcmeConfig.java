import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AcmeConfig {

  @Bean
  public String secretMessage() {
    return "Important Message";
  }

  @Bean
  public ImportantBusinessService lucrativeService() {
    return investment -> investment * 10;
  }

  @Bean
  public MessageService secretMessageService(String secreteMessage) {
    return () -> secreteMessage;
  }

}
