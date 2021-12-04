package me.givo.distancebetweenus.getdistance.repositories;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import me.givo.distancebetweenus.getdistance.entities.CityEntity;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ICitiesRepositoryTest {

    @Autowired
    ICitiesRepository citiesRepository;

    @Test
    void testGetByName() {

        CityEntity panama = citiesRepository.getByNameAndCode("Panama City", "PA");
        assertTrue(panama.getCityName().equals("Panama City"));
        assertTrue(panama.getIso2().equals("PA"));
    }
}
