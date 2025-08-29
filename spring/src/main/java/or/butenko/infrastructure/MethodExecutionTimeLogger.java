package or.butenko.infrastructure;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

//TODO: дописать BeanPostProcessor, который будет логировать время выполнения методов.
public class MethodExecutionTimeLogger implements BeanPostProcessor {

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) {
    System.out.println("Before initialization of " + beanName);
    return bean;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) {
    System.out.println("After initialization of " + beanName);
    return bean;
  }

//  @Override
//  public Object postProcessAfterInitialization(
//      final Object bean,
//      final String beanName
//  ) throws BeansException {
//    // Проверяем, является ли бин сервисом (например, по имени или типу)
//    if (bean.getClass().getPackageName().startsWith("or.butenko.domain.service")) {
//      return ProxyFactory.createProxy(bean);
//    }
//    return bean;
//  }
}
