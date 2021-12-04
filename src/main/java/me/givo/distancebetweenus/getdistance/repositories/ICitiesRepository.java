package me.givo.distancebetweenus.getdistance.repositories;

import me.givo.distancebetweenus.getdistance.entities.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICitiesRepository extends JpaRepository<CityEntity, Integer> {

    @Query(value = "SELECT * FROM cities c WHERE c.city_ascii = :city and c.iso2 = :country", nativeQuery = true)
    CityEntity getByNameAndCode(@Param("city") String city, @Param("country") String country);
}
