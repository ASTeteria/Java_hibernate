package HW2;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.io.Serializable;


@Data
@NoArgsConstructor

@Entity
public class Word implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String value;

    public Word(String value) {
        this.value = value;
    }

}