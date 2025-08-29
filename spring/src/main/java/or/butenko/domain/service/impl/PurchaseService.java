package or.butenko.domain.service.impl;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import or.butenko.domain.model.order.Order;
import or.butenko.domain.repository.CarRepository;
import or.butenko.domain.service.PurchaseUseCase;

@Slf4j
@Setter
public class PurchaseService implements PurchaseUseCase {

  private CarRepository carRepository;

  @Override
  public Order execute(final Order order) {
    try {
      Thread.sleep(345L);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    order.getProducts().forEach(c -> this.carRepository.deleteCar(c.getUuid()));
    log.info("All cars from order was deleted from price-list");
    return order;
  }
}
