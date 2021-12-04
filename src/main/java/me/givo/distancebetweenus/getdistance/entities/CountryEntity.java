package me.givo.distancebetweenus.getdistance.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "countriescode")
public class CountryEntity {

    @Id
    @Column(name = "code2", length = 2, unique = true, nullable = false)
    private String countryId; // We are using the alpha 2 country code as unique ID

    @Column(name = "country", length = 50, nullable = false)
    private String countryName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CountryEntity that = (CountryEntity) o;
        return countryId != null && Objects.equals(countryId, that.countryId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
