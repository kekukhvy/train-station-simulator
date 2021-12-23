package dev.vkekukh.trainsimulator.railway.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "routes")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String route_number;

    @NotBlank(message = "the name is mandatory in route")
    private String name;

    @OneToMany(targetEntity=Connection.class, fetch = FetchType.EAGER)
    private List<Connection> connections = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(route_number, route.route_number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(route_number);
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", route_number='" + route_number + '\'' +
                ", name='" + name + '\'' +
                ", connections=" + connections +
                '}';
    }
}
