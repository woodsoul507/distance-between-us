package me.givo.distancebetweenus.getdistance.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.givo.distancebetweenus.getdistance.dtos.CityDto;

@Data
@NoArgsConstructor
public class Coordinate {

    private Double latitude;
    private Double longitude;

    public Coordinate(CityDto city) {
        this.latitude = city.getLatitude();
        this.longitude = city.getLongitude();
    }

    public Coordinate(ApiCurrentLocationResponse apiCurrentLocationResponse) {
        this.latitude = apiCurrentLocationResponse.getLatitude();
        this.longitude = apiCurrentLocationResponse.getLongitude();
    }

    public Double distanceBetweenUsKm(Coordinate destination) {

        Double currentLocationLat = this.latitude;
        Double currentLocationLng = this.longitude;
        Double destinationLat = destination.latitude;
        Double destinationLng = destination.longitude;

        final int R = 6371; // Earth's radius

        double latDistance = Math.toRadians(destinationLat - currentLocationLat);
        double lonDistance = Math.toRadians(destinationLng - currentLocationLng);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(currentLocationLat)) * Math.cos(Math.toRadians(destinationLat))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c; // in Km

        double height = 0.0; // (publicationAltitude - destinationAltitude) non cities database with altitude
        // field yet

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);

    }
}
