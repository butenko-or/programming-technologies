package or.butenko.domain.model.car;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import or.butenko.domain.model.car.enums.CarDrive;
import or.butenko.domain.model.car.enums.Color;

@AllArgsConstructor
@Data
public class Car {

  private UUID uuid;
  private String brand;
  private String model;
  private Color color;
  private Engine engine;
  private CarDrive carDrive;
  private BigDecimal price;
}
