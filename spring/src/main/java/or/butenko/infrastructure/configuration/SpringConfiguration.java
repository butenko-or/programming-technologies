package or.butenko.infrastructure.configuration;

import or.butenko.infrastructure.MethodExecutionTimeLogger;
import or.butenko.infrastructure.configuration.beanfactorypostproccesors.MessageLocalizerPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:application.properties")
public class SpringConfiguration {

  @Bean
  public static MessageLocalizerPostProcessor messageLocalizerPostProcessor(
      final Environment environment
  ) {
    final MessageLocalizerPostProcessor messageLocalizerPostProcessor =
        new MessageLocalizerPostProcessor();
    messageLocalizerPostProcessor.setEnvironment(environment);
    return messageLocalizerPostProcessor;
  }

  @Bean
  public static MethodExecutionTimeLogger methodExecutionTimeLogger() {
    System.out.println("БИН ЗАРЕГЕСТРИРОВАН");
    return new MethodExecutionTimeLogger();
  }
}
