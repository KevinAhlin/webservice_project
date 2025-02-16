package se.systementor.webservice_project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.systementor.webservice_project.dto.ForecastListDTO;
import se.systementor.webservice_project.models.Forecast;
import se.systementor.webservice_project.services.ForecastService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

//1.  Client anropar /api/forecasts - GET
//2.  Spring kollar vilken funktion som hanterar denna /api/forecasts
//3.  Spring anropar den funktionen
//3.5 VÅR KOD KÖRS
//4.  Spring tar det som funktionen returnerar och gör till JSON
//5.  Spring skickar tillbaka JSON till client

@RestController         // Makes so that our application can answer http calls
public class ForecastController {

    @Autowired
    private ForecastService forecastService;

    // Constructor
    ForecastController() {
        forecastService = new ForecastService();
    }

    // Returns all forecasts
    @GetMapping("/api/forecasts")       // http - GET
    public ResponseEntity<List<ForecastListDTO>> getAll() {

        return new ResponseEntity<List<ForecastListDTO>>(   // ResponseEntity checks both the data and its status
                forecastService.getForecasts().stream().map(forecast->{
            var forecastListDTO = new ForecastListDTO();
            forecastListDTO.Id = forecast.getId();
            /*
            forecastListDTO.Date = c.getDate();
            forecastListDTO.Hour = c.getHour();
            forecastListDTO.Temperature = c.getTemperature();
             */
            return forecastListDTO;
        }).collect(Collectors.toList()), HttpStatus.OK
        );
    }

    // Returns one specific forecast
    @GetMapping("/api/forecasts/{id}")
    public ResponseEntity<Forecast> getForecast(@PathVariable UUID id) {
        Optional<Forecast> forecast = forecastService.get(id);
        if (forecast.isPresent()) {
            return ResponseEntity.ok(forecast.get());
        }
        return  ResponseEntity.notFound().build();
    }

    // Returns the average on a date you put in the URL
    @GetMapping("/api/forecasts/averageTemperature/{date}")
    public ResponseEntity<List<Map<String, Object>>> getAverageTemperaturePerHour(
            @PathVariable("date") @DateTimeFormat(pattern = "yyyyMMdd") LocalDate date) {

        List<Map<String, Object>> averageTemperatureData =
                forecastService.getAverageTemperaturePerHour(date);

        return new ResponseEntity<>(averageTemperatureData, HttpStatus.OK);
    }

    // Create a new forecast
    @PostMapping("/api/forecasts")
    public ResponseEntity<Forecast> newForecast( @RequestBody Forecast forecast) throws IOException {   // id
        var newCreated = forecastService.add(forecast);
        return ResponseEntity.ok(newCreated);   // mer REST ful = created (204) samt url till forecast
    }

    // Updates one forecast
    @PutMapping("/api/forecasts/{id}")
    public ResponseEntity<Forecast> updateForecast(
            @PathVariable UUID id, @RequestBody ForecastListDTO forecastListDTO) throws IOException {
        // Mapping from dto -> entity

        // LÖSNING
        var forecast = new Forecast();
        //var forecast = forecastService.get(id).get();   // hämta objektet från databas = alla properties sätts
        forecast.setId(id);
        /*
        forecast.setDate(newForecastDTO.getDate());
        forecast.setHour(newForecastDTO.getHour());
        forecast.setTemperature(newForecastDTO.getTemperature());
         */
        // forecast som är blankt på alla properties utom date, hour, temp
        forecastService.update(forecast);
        return ResponseEntity.ok(forecast);
    }

    // Deletes a forecast
    @DeleteMapping("/api/forecasts/{id}")
    public ResponseEntity<String> deleteForecast(@PathVariable UUID id) {
        //Optional<Forecast> forecast = forecastService.get(id);
        boolean status = forecastService.deleteById(id);
        if (status == true) {
            return ResponseEntity.ok("Deleted");
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }

}
