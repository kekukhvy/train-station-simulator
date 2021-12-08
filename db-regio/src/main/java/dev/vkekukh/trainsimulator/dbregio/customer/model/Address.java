package dev.vkekukh.trainsimulator.dbregio.customer.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "the country is mandatory in person")
    private String country;
    @NotBlank(message = "the city is mandatory in person")
    private String city;
    private String zipCode;
    @NotBlank(message = "the street is mandatory in person")
    private String street;
    @NotBlank(message = "the houseNo is mandatory in person")
    private String houseNo;
    private String flat;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return country.equals(address.country)
                && city.equals(address.city)
                && Objects.equals(zipCode, address.zipCode)
                && street.equals(address.street)
                && houseNo.equals(address.houseNo)
                && Objects.equals(flat, address.flat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, city, zipCode, street, houseNo, flat);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Address{");
        sb.append("id=").append(id);
        sb.append(", country='").append(country).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", zipCode='").append(zipCode).append('\'');
        sb.append(", street='").append(street).append('\'');
        sb.append(", houseNo='").append(houseNo).append('\'');
        sb.append(", flat='").append(flat).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
