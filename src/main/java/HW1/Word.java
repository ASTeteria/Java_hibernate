package HW1;

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
@NoArgsConstructor
public class Word {
    private int id;
    private String value;

    public Word(String value) {
        this.value = value;
    }

    @SneakyThrows
    public static void createWordTable(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(
                    "create table if not exists words (" +
                            "id int primary key auto_increment, " +
                            "value varchar(255) not null " +
                            ")"
            );
            System.out.println("Word table was created if it did not exist");
        }
    }

    @SneakyThrows
    public static void insertWord(Connection connection, Word word) {
        String query = "insert into words (value) values (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, word.getValue());
            preparedStatement.execute();
            System.out.println("Word " + word.getValue() + " inserted");
        }
    }

    @SneakyThrows
    public static List<String> getAllWords(Connection connection) {
        List<String> values = new ArrayList<>();
        String query = "select value from words";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                values.add(resultSet.getString("value"));
            }
        }
        return values;
    }
}
