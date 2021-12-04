package me.givo.distancebetweenus.getdistance.controllers;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import me.givo.distancebetweenus.getdistance.dtos.CityDto;
import me.givo.distancebetweenus.getdistance.dtos.CountryDto;
import me.givo.distancebetweenus.getdistance.entities.CityEntity;
import me.givo.distancebetweenus.getdistance.entities.CountryEntity;
import me.givo.distancebetweenus.getdistance.models.ApiCurrentLocationResponse;
import me.givo.distancebetweenus.getdistance.models.Coordinate;
import me.givo.distancebetweenus.getdistance.models.DistanceResponse;
import me.givo.distancebetweenus.getdistance.repositories.ICitiesRepository;
import me.givo.distancebetweenus.getdistance.repositories.ICountriesRepository;
import me.givo.distancebetweenus.getdistance.services.CurrentLocationService;

@RequestMapping(path = "api/v1/get-distance")
@Validated
@RestController
public class DistanceController {

    private final CurrentLocationService currentLocationService;
    private final ICitiesRepository citiesRepository;
    private final ICountriesRepository countriesRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public DistanceController(CurrentLocationService currentLocationService, ICitiesRepository citiesRepository,
            ICountriesRepository countriesRepository, ModelMapper modelMapper) {
        this.currentLocationService = currentLocationService;
        this.citiesRepository = citiesRepository;
        this.countriesRepository = countriesRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<Object> getDistance(
            @RequestParam(name = "country") String country,
            @RequestParam(name = "city") String city,
            @RequestParam(required = false, name = "ip") String ip,
            HttpServletRequest request) {

        ApiCurrentLocationResponse currentLocation = new ApiCurrentLocationResponse();
        if (ip == null || ip.isEmpty()) {
            String requestIP = request.getRemoteAddr();
            System.out.println(requestIP);
            try {
                currentLocation = currentLocationService.getCurrentLocation(requestIP);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            try {
                currentLocation = currentLocationService.getCurrentLocation(ip);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        CountryEntity countryEntity = countriesRepository.getById(country);
        CityEntity cityEntity = citiesRepository.getByNameAndCode(city, country);

        CountryDto countryDto = convertToCountryDto(countryEntity);
        CityDto cityDto = convertToCityDto(cityEntity);

        Coordinate currentLocationCoordinate = new Coordinate(currentLocation);
        Coordinate destinationCoordinate = new Coordinate(cityDto);

        Double distance = currentLocationCoordinate.distanceBetweenUsKm(destinationCoordinate);

        DistanceResponse response = new DistanceResponse(countryDto, cityDto, distance);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    private CityDto convertToCityDto(CityEntity cityEntity) {
        return modelMapper.map(cityEntity, CityDto.class);
    }

    private CountryDto convertToCountryDto(CountryEntity countryEntity) {
        return modelMapper.map(countryEntity, CountryDto.class);
    }
}