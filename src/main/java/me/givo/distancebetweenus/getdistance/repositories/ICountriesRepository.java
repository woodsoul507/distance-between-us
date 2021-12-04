package me.givo.distancebetweenus.getdistance.repositories;


import me.givo.distancebetweenus.getdistance.entities.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ICountriesRepository extends JpaRepository<CountryEntity, String> {

}
