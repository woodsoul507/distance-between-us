package me.givo.distancebetweenus.getdistance.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.givo.distancebetweenus.getdistance.dtos.CityDto;
import me.givo.distancebetweenus.getdistance.dtos.CountryDto;

@Data
@NoArgsConstructor
public class DistanceResponse {

    private String name;
    private Double distance;
    private String country;
    private Integer population;
    private String iso2;
    private String iso3;
    private Double latitude;
    private Double longitude;

    public DistanceResponse(CountryDto country, CityDto city, Double distance) {

        this.name = city.getCityName();
        this.distance = distance;
        this.country = country.getCountryName();
        this.population = city.getPopulation();
        this.iso2 = city.getIso2();
        this.iso3 = city.getIso3();
        this.latitude = city.getLatitude();
        this.longitude = city.getLongitude();
    }
}
