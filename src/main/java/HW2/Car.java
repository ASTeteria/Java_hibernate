package HW2;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class Car implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String brand;
    @Enumerated(EnumType.STRING)
    private BodyType bodyType;
    private int power;
    private int price;
    private int year;

    public Car(String brand, BodyType bodyType, int power, int price, int year) {
        this.brand = brand;
        this.bodyType = bodyType;
        this.power = power;
        this.price = price;
        this.year = year;
    }
}

