package dev.vkekukh.trainsimulator.railway.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "connections")
public class Connection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "the stationA is mandatory in connection")
    @OneToOne(fetch = FetchType.EAGER)
    private TrainStation stationA;
    @NotBlank(message = "the stationB is mandatory in connection")
    @OneToOne(fetch = FetchType.EAGER)
    private TrainStation stationB;
    @NotBlank(message = "the distance is mandatory in connection")
    private int distance;

    private float averageSpeed;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Connection that = (Connection) o;
        return Objects.equals(stationA, that.stationA) && Objects.equals(stationB, that.stationB);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stationA, stationB);
    }

    @Override
    public String toString() {
        return "Connection{" +
                "id=" + id +
                ", stationA=" + stationA +
                ", stationB=" + stationB +
                ", distance=" + distance +
                ", averageSpeed=" + averageSpeed +
                '}';
    }
}
