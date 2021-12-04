package me.givo.distancebetweenus.getdistance.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CityDto {

    @EqualsAndHashCode.Include
    private Integer cityId;

    private String cityName;

    private Double latitude;

    private Double longitude;

    private String iso2;

    private String iso3;

    private Integer population;

}
