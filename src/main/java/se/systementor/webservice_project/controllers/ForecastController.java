package se.systementor.webservice_project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.systementor.webservice_project.dto.ForecastListDTO;
import se.systementor.webservice_project.dto.NewForecastDTO;
import se.systementor.webservice_project.models.Forecast;
import se.systementor.webservice_project.services.ForecastService;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

//1.  Client anropar /api/forecasts - GET
//2.  Spring kollar vilken funktion som hanterar denna /api/forecasts
//3.  Spring anropar den funktionen
//3.5 VÅR KOD KÖRS
//4.  Spring tar det som funktionen returnerar och gör till JSON
//5.  Spring skickar tillbaka JSON till client

@RestController     // Makes so that our application can answer http calls
public class ForecastController {

    @Autowired
    private ForecastService forecastService;

    // Returns all forecasts
    @GetMapping("/api/forecasts")       // http get
    public ResponseEntity<List<ForecastListDTO>> getAll() {

        return new ResponseEntity<List<ForecastListDTO>>(forecastService.getForecasts().stream().map(c->{
            var forecastListDTO = new ForecastListDTO();
            forecastListDTO.Id = c.getId();
            forecastListDTO.Date = c.getDate();
            forecastListDTO.Hour = c.getHour();
            forecastListDTO.Temperature = c.getTemperature();
            return forecastListDTO;
        }).collect(Collectors.toList()), HttpStatus.OK);    // ResponseEntity checks both the data and its status
    }

    // Returns one specific forecast
    @GetMapping("/api/forecasts/{id}")
    public ResponseEntity<Forecast> getForecast(@PathVariable UUID id) {
        Optional<Forecast> forecast = forecastService.get(id);
        if (forecast.isPresent())
            return ResponseEntity.ok(forecast.get());
        return  ResponseEntity.notFound().build();
    }

    // Update one prediction
    @PutMapping("/api/forecasts/{id}")
    public ResponseEntity<Forecast> updateForecast(@PathVariable UUID id, @RequestBody NewForecastDTO newForecastDTO) throws IOException {
        // Mapping from dto -> entity
        var forecast = new Forecast();
        forecast.setId(id);
        forecast.setDate(newForecastDTO.getDate());
        forecast.setHour(newForecastDTO.getHour());
        forecast.setTemperature(newForecastDTO.getTemperature());
        forecast.setLastModifiedBy("Kevin Åhlin");

        forecastService.update(forecast);
        return ResponseEntity.ok(forecast);
    }

    @PostMapping("/api/forecasts")
    public ResponseEntity<Forecast> New( @RequestBody Forecast forecast) throws IOException {   // id
        var newCreated = forecastService.add(forecast);
        return ResponseEntity.ok(newCreated);   // mer REST ful = created (204) samt url till forecast
    }

    /*
    @DeleteMapping("/api/forecasts/{id}")
    // This 'hard' delete isn't used very often
    public ResponseEntity<Forecast> getForecast(@PathVariable UUID id) {
        Optional<Forecast> forecast = forecastService.get(id);
        if (forecast.isPresent())
            return ResponseEntity.ok(forecast.get());
        return  ResponseEntity.notFound().build();
    }
     */


}
