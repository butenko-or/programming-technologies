package or.butenko.infrastructure.repository.simple;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import or.butenko.domain.model.car.Car;
import or.butenko.domain.repository.CarRepository;

@Slf4j
@RequiredArgsConstructor
public class CarRepositoryImpl implements CarRepository {

  private final List<Car> cars;

  @Override
  public Car saveCar(final Car car) {
    car.setUuid(UUID.randomUUID());
    this.cars.add(car);
    return car;
  }

  @Override
  public Car getCar(final UUID uuid) {
    return this.cars.stream()
        .filter(c -> c.getUuid().equals(uuid))
        .findFirst()
        .orElseThrow(RuntimeException::new);
  }

  @Override
  public List<Car> getAllCars() {
    log.info("Was returned {}", this.cars);
    return this.cars;
  }

  @Override
  public Car updateCar(final Car car) {
    final Car outdatedCar = this.cars.stream()
        .filter(c -> c.getUuid().equals(car.getUuid()))
        .findFirst()
        .orElseThrow(RuntimeException::new);
    this.cars.remove(outdatedCar);
    this.cars.add(car);
    return car;
  }

  @Override
  public void deleteCar(final UUID uuid) {
    final Car car = this.cars.stream()
        .filter(c -> c.getUuid().equals(uuid))
        .findFirst()
        .orElseThrow(RuntimeException::new);
    this.cars.remove(car);
  }
}
