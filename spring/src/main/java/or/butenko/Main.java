package or.butenko;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import or.butenko.domain.model.car.Car;
import or.butenko.domain.model.car.Engine;
import or.butenko.domain.model.car.enums.CarDrive;
import or.butenko.domain.model.car.enums.Color;
import or.butenko.domain.model.car.enums.FuelType;
import or.butenko.domain.model.customer.Customer;
import or.butenko.domain.model.order.Order;
import or.butenko.domain.repository.CarRepository;
import or.butenko.domain.repository.CustomerRepository;
import or.butenko.domain.repository.OrderRepository;
import or.butenko.domain.service.PurchaseUseCase;
import or.butenko.domain.service.impl.PurchaseService;
import or.butenko.infrastructure.configuration.ApplicationConfiguration;
import or.butenko.infrastructure.configuration.SpringConfiguration;
import or.butenko.infrastructure.locale.MessageLocalizer;
import or.butenko.infrastructure.locale.impl.EnglishMessageLocalizerImpl;
import or.butenko.infrastructure.locale.impl.RussianMessageLocalizerImpl;
import or.butenko.infrastructure.repository.simple.CarRepositoryImpl;
import or.butenko.infrastructure.repository.simple.CustomerRepositoryImpl;
import or.butenko.infrastructure.repository.simple.OrderRepositoryImpl;
import or.butenko.infrastructure.utils.localizer.LocalizerConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class Main {

  @Value("${application.language}")
  private String applicationLanguage;

  public static void main(final String[] args) {
    log.info("This application was started");

    final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(
        ApplicationConfiguration.class,
        SpringConfiguration.class
    );

    final String language = applicationContext.getEnvironment().getProperty(
        "application.language",
        String.class
    );

    final MessageLocalizer messageLocalizer;
    final String messageLocalizerBeanName = "messageLocalizer";

    messageLocalizer = switch (language) {
      case "russian" -> applicationContext.getBean(messageLocalizerBeanName,
          RussianMessageLocalizerImpl.class);
      case null, default -> applicationContext.getBean(messageLocalizerBeanName,
          EnglishMessageLocalizerImpl.class);
    };


    log.info(messageLocalizer.returnLocalizedMessage(LocalizerConstant.SPRING_STARTED));
    log.info(
        messageLocalizer.returnLocalizedMessage(LocalizerConstant.CREATED_SPRING_BEANS),
        Arrays.toString(applicationContext.getBeanDefinitionNames()));

    final CarRepository carRepository = applicationContext.getBean("carRepository",
        CarRepositoryImpl.class);
    final CustomerRepository customerRepository =
        applicationContext.getBean("customerRepository", CustomerRepositoryImpl.class);
    final OrderRepository orderRepository =
        applicationContext.getBean("orderRepository", OrderRepositoryImpl.class);
    final PurchaseUseCase purchaseUseCase =
        applicationContext.getBean("purchaseUseCase", PurchaseService.class);

    final String bmw = "BMW";
    final String m5 = "M5";

    final Engine bmwEngine = new Engine(4400, 625, FuelType.PETROL);
    final Car blackM5 = new Car(null, bmw, m5, Color.BLACK, bmwEngine,
        CarDrive.ALL_WHEEL, BigDecimal.valueOf(15000000));
    final Car whiteM5 = new Car(null, bmw, m5, Color.WHITE, bmwEngine,
        CarDrive.ALL_WHEEL, BigDecimal.valueOf(14950000));
    final Car blackX5M = new Car(null, bmw, "X5 M", Color.BLACK, bmwEngine,
        CarDrive.ALL_WHEEL, BigDecimal.valueOf(19000000));

    carRepository.saveCar(blackM5);
    carRepository.saveCar(whiteM5);
    carRepository.saveCar(blackX5M);
    carRepository.getAllCars();

    final String jahnGalt = "John Galt";

    final Customer customer = new Customer(null, jahnGalt, "+79697055555", 0);
    final Order order = new Order(null, List.of(blackM5, blackX5M), customer);

    customerRepository.saveCustomer(customer);
    orderRepository.saveOrder(order);
    customerRepository.getCustomer(jahnGalt);
    orderRepository.getOrder(customer);

    purchaseUseCase.execute(order);
    carRepository.getAllCars();
  }
}
