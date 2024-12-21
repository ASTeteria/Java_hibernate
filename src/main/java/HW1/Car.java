package HW1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    private int id;
    private String brand;
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

    @SneakyThrows
    public static void createCarTable(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(
                    "create table if not exists cars (" +
                            "id int primary key auto_increment," +
                            " brand varchar(255), " +
                            "bodyType varchar(255), " +
                            "power int, " +
                            "price int, " +
                            "year int" +
                            ")"
            );
            System.out.println("Car Table created");
        }
    }

    @SneakyThrows
    public static void insertCar(Connection connection, Car car) {
        String query = "insert into cars (brand, bodyType, power, price, year) values (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, car.getBrand());
            preparedStatement.setString(2, car.getBodyType().toString());
            preparedStatement.setInt(3, car.getPower());
            preparedStatement.setInt(4, car.getPrice());
            preparedStatement.setInt(5, car.getYear());
            preparedStatement.execute();
            System.out.println("Car " + car.getBrand() + " inserted");
        }

    }

    @SneakyThrows
    public static List<Car> getAllCars(Connection connection) {
        List<Car> cars = new ArrayList<>();
        String query = "select * from cars";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String brand = resultSet.getString("brand");
                BodyType bodyType = BodyType.valueOf(resultSet.getString("bodyType"));
                int power = resultSet.getInt("power");
                int price = resultSet.getInt("price");
                int year = resultSet.getInt("year");

                Car car = new Car(id, brand, bodyType, power, price, year);
                cars.add(car);
            }
        }
        return cars;
    }
}
