package se.systementor.webservice_project.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.systementor.webservice_project.models.Forecast;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ForecastRepository extends CrudRepository<Forecast, UUID> {

    @Override
    List<Forecast> findAll();

    List<Forecast> findAllByPredictionDateAndColor(LocalDate date, String color);   // autofill funkar inte, varför inte?

}
