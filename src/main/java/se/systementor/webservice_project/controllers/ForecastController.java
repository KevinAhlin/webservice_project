package se.systementor.webservice_project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.systementor.webservice_project.models.Forecast;
import se.systementor.webservice_project.services.ForecastService;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

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
    public ResponseEntity<List<Forecast>> getAll() {
        // ResponseEntity checks both the data and its status
        return new ResponseEntity<>(forecastService.getForecasts(), HttpStatus.OK);
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
    public ResponseEntity<Forecast> updateForecast(@PathVariable UUID id, @RequestBody Forecast forecast) throws IOException, IOException {
        forecastService.update(forecast);
        return ResponseEntity.ok(forecast);
    }





    //    @PutMapping("/api/products/{id}")
    //    public ResponseEntity<Product> Update(@PathVariable UUID id, @RequestBody Product product){
    //        boolean status = productService.update(product);
    //        if(status == true)
    //            return ResponseEntity.ok(product);
    //        else
    //            return ResponseEntity.badRequest().build();
    //    }

}
