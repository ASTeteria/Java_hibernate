package HW1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/WordCars";
        String username = "ASTeteria";
        String password = "19823366";

        try(Connection conn = DriverManager.getConnection(url, username, password)) {
            System.out.println("Connected to the database is"+conn.isValid(5));

            Word.createWordTable(conn);

            Word.insertWord(conn, new Word("Hello"));
            Word.insertWord(conn, new Word("Apple"));
            Word.insertWord(conn, new Word("Pen"));
            Word.insertWord(conn, new Word("Pineapple"));

            List<String> allWords = Word.getAllWords(conn);
            for (String allWord : allWords) {
                System.out.println(allWord);
            }
            System.out.println("-------------------------------------------------------------");
            Car.createCarTable(conn);

            Car.insertCar(conn, new Car("Toyoya", BodyType.COUPE, 200, 12000,2000));
            Car.insertCar(conn, new Car("Ford", BodyType.JEEP, 250, 32000,2001));
            Car.insertCar(conn, new Car("Honda", BodyType.TRUCK, 150, 42000,2010));
            Car.insertCar(conn, new Car("Kia", BodyType.SEDAN, 300, 200000,1987));

            List<Car> allCars = Car.getAllCars(conn);
            for (Car car : allCars) {
                System.out.println(car);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}