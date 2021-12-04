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
@Table(name = "cities")
public class CityEntity {

    @Id
    @Column(name = "city_id", length = 15, unique = true, nullable = false)
    private Integer cityId;

    @Column(name = "city_ascii", length = 50, nullable = false)
    private String cityName;

    @Column(name = "lat", length = 9, nullable = false)
    private Double latitude;

    @Column(name = "lng", length = 9, nullable = false)
    private Double longitude;

    @Column(name = "iso2", length = 2, nullable = false)
    private String iso2;

    @Column(name = "iso3", length = 3, nullable = false)
    private String iso3;

    @Column(name = "city_population", length = 15, nullable = true)
    private String population;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CityEntity that = (CityEntity) o;
        return cityId != null && Objects.equals(cityId, that.cityId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
