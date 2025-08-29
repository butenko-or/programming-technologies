package or.butenko.domain.model.car;

import lombok.AllArgsConstructor;
import or.butenko.domain.model.car.enums.FuelType;

@AllArgsConstructor
public class Engine {

  private int capacity;
  private int horsePower;
  private FuelType fuelType;
}
