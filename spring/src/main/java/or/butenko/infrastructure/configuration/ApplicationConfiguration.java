package or.butenko.infrastructure.configuration;

import java.util.ArrayList;
import or.butenko.domain.model.car.Car;
import or.butenko.domain.model.customer.Customer;
import or.butenko.domain.model.order.Order;
import or.butenko.domain.repository.CarRepository;
import or.butenko.domain.repository.CustomerRepository;
import or.butenko.domain.repository.OrderRepository;
import or.butenko.domain.service.PurchaseUseCase;
import or.butenko.domain.service.impl.PurchaseService;
import or.butenko.infrastructure.repository.simple.CarRepositoryImpl;
import or.butenko.infrastructure.repository.simple.CustomerRepositoryImpl;
import or.butenko.infrastructure.repository.simple.OrderRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

  @Bean
  public CarRepository carRepository() {
    return new CarRepositoryImpl(new ArrayList<Car>());
  }

  @Bean
  public OrderRepository orderRepository() {
    return new OrderRepositoryImpl(new ArrayList<Order>());
  }

  @Bean
  public CustomerRepository customerRepository() {
    return new CustomerRepositoryImpl(new ArrayList<Customer>());
  }

  @Bean
  public PurchaseUseCase purchaseUseCase(final CarRepository carRepository) {
    final PurchaseUseCase purchaseUseCase = new PurchaseService();
    purchaseUseCase.setCarRepository(carRepository);
    return purchaseUseCase;
  }
}
