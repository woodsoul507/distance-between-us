package me.givo.distancebetweenus.getdistance.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiCurrentLocationResponse {

    private String ip;
    private String country_code;
    private String country_name;
    private String region_code;
    private String region_name;
    private String city;
    private String zip_code;
    private String time_zone;
    private Double latitude;
    private Double longitude;
    private Integer metro_code;

}
