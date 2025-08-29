package infrastructure.configuration;

import java.util.List;
import or.butenko.infrastructure.configuration.ApplicationConfiguration;
import or.butenko.infrastructure.configuration.SpringConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationConfigurationTest {

  private static ApplicationContext applicationContext;
  private final List<String> expectedBeans = List.of(
      "carRepository",
      "orderRepository",
      "customerRepository",
      "purchaseUseCase",
      "messageLocalizer"
  );

  @BeforeAll
  public static void initApplicationContext() {
    applicationContext = new AnnotationConfigApplicationContext(
        ApplicationConfiguration.class,
        SpringConfiguration.class
    );
  }

  @Test
  @DisplayName("Test to check the creation of application context.")
  public void createdApplicationContextTest() {
    Assertions.assertNotNull(applicationContext);
  }

  @Test
  @DisplayName("Test to check the creation of necessary beans.")
  public void createdSpringBeansTest() {
    final List<String> beans = List.of(applicationContext.getBeanDefinitionNames());
    Assertions.assertTrue(beans.containsAll(this.expectedBeans));
  }
}
