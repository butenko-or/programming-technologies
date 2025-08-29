package or.butenko.infrastructure.configuration.beanfactorypostproccesors;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import or.butenko.infrastructure.locale.impl.EnglishMessageLocalizerImpl;
import or.butenko.infrastructure.locale.impl.RussianMessageLocalizerImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.lang.NonNull;

@Slf4j
@NoArgsConstructor
public class MessageLocalizerPostProcessor implements BeanFactoryPostProcessor, EnvironmentAware {

  private static final String MESSAGE_LOCALIZER = "messageLocalizer";
  private static final String LANGUAGE_PROPERTY = "application.language";
  private Environment environment;

  @Override
  public void postProcessBeanFactory(@NonNull final ConfigurableListableBeanFactory beanFactory)
      throws BeansException {

    final String applicationLanguage = this.environment.getProperty(LANGUAGE_PROPERTY);
    log.debug("Property: {} = {}", LANGUAGE_PROPERTY, applicationLanguage);

    final BeanDefinitionRegistry registry = (BeanDefinitionRegistry) beanFactory;

    switch (applicationLanguage) {
      case "russian":
        this.registerMessageLocalizer(registry, RussianMessageLocalizerImpl.class);
        log.debug("BeanDefinition: RussianMessageLocalizer was registered");
        break;
      case null, default:
        this.registerMessageLocalizer(registry, EnglishMessageLocalizerImpl.class);
        log.debug("BeanDefinition: EnglishMessageLocalizer was registered");
    }
  }

  @Override
  public void setEnvironment(@NonNull final Environment environment) {
    this.environment = environment;
  }

  private void registerMessageLocalizer(
      final BeanDefinitionRegistry registry,
      final Class<?> localizerClass
  ) {
    registry.registerBeanDefinition(
        MESSAGE_LOCALIZER,
        BeanDefinitionBuilder.genericBeanDefinition(localizerClass).getBeanDefinition()
    );
  }
}
