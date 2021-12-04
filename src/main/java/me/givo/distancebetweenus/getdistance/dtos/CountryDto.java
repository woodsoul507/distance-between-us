package me.givo.distancebetweenus.getdistance.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CountryDto {

    @EqualsAndHashCode.Include
    private String countryId; // We are using the alpha 2 country code as unique ID

    private String countryName;

}
