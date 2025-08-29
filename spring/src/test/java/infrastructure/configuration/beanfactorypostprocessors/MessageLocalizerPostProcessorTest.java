package infrastructure.configuration.beanfactorypostprocessors;

import or.butenko.infrastructure.configuration.ApplicationConfiguration;
import or.butenko.infrastructure.configuration.SpringConfiguration;
import or.butenko.infrastructure.locale.MessageLocalizer;
import or.butenko.infrastructure.locale.impl.EnglishMessageLocalizerImpl;
import or.butenko.infrastructure.locale.impl.RussianMessageLocalizerImpl;
import or.butenko.infrastructure.utils.localizer.LocalizerConstant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
public class MessageLocalizerPostProcessorTest {

  private static MessageLocalizer messageLocalizer;

  @BeforeAll
  public static void initApplicationContext() {
    final ApplicationContext applicationContext =
        new AnnotationConfigApplicationContext(
            ApplicationConfiguration.class,
            SpringConfiguration.class
        );

    final String language = applicationContext.getEnvironment().getProperty(
        "application.language",
        String.class
    );
    final String messageLocalizerBeanName = "messageLocalizer";

    messageLocalizer = switch (language) {
      case "russian" -> applicationContext.getBean(messageLocalizerBeanName,
          RussianMessageLocalizerImpl.class);
      case null, default -> applicationContext.getBean(messageLocalizerBeanName,
          EnglishMessageLocalizerImpl.class);
    };
  }

  @Test
  @DisplayName("Test to check the creation necessary configuration of MessageLocalizer bean.")
  public void configuringMessageLocalizerRussianTest() {
    final String expected = messageLocalizer
        .returnLocalizedMessage(LocalizerConstant.APPLICATION_STARTED);

    Assertions.assertEquals("Приложение запущено", expected);
  }

  @Test
  @DisplayName("Test to check the creation not necessary configuration MessageLocalizer bean.")
  public void configuringMessageLocalizerEnglishFailureTest() {
    final String expected = messageLocalizer
        .returnLocalizedMessage(LocalizerConstant.APPLICATION_STARTED);

    Assertions.assertNotEquals("The application was started", expected);
  }

}
