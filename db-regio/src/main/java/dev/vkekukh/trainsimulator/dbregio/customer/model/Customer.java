package dev.vkekukh.trainsimulator.dbregio.customer.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Accessors(chain = true)
@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @NotBlank(message = "the login is mandatory in person")
    private String login;
    private String fullName;
    private int discount;

    @OneToOne
    private Address address;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(login, customer.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Customer{");
        sb.append("id=").append(id);
        sb.append(", login='").append(login).append('\'');
        sb.append(", fullName='").append(fullName).append('\'');
        sb.append(", discount=").append(discount);
        sb.append(", address=").append(address);
        sb.append('}');
        return sb.toString();
    }
}
