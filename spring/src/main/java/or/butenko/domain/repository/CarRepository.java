package or.butenko.domain.repository;

import java.util.List;
import java.util.UUID;
import or.butenko.domain.model.car.Car;

public interface CarRepository {

  Car saveCar(Car car);

  Car getCar(UUID uuid);

  List<Car> getAllCars();

  Car updateCar(Car car);

  void deleteCar(UUID uuid);
}
