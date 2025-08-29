package or.butenko.domain.service;

import or.butenko.domain.model.order.Order;
import or.butenko.domain.repository.CarRepository;

public interface PurchaseUseCase {

  Order execute(Order order);

  void setCarRepository(CarRepository carRepository);
}
