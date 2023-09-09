package se.systementor.webservice_project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.systementor.webservice_project.models.Forecast;
import se.systementor.webservice_project.repositories.ForecastRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@Service
public class ForecastService {

    @Autowired
    private ForecastRepository forecastRepository;
    //private static List<Forecast> forecasts = new ArrayList<Forecast>();    // static means it's not bound to one instance


    // Constructor
    public ForecastService() {

    }

    /*
    private List<Forecast> readFromFile() throws IOException {
        // If it's empty, return an empty array
        if (!Files.exists(Path.of("predictions.json")))
            return new ArrayList<>();
        ObjectMapper objectMapper = getObjectMapper();
        var jsonStr = Files.readString(Path.of("predictions.json"));

        return  new ArrayList(Arrays.asList(
                objectMapper.readValue( jsonStr, Forecast[].class )));
    }

    private void writeAllToFile(List<Forecast> weatherPredictions) throws IOException {
        // Write the list of predictions when adding or updating a prediction
        ObjectMapper objectMapper = getObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);    // Creates indentures for the output
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        StringWriter stringWriter = new StringWriter();
        objectMapper.writeValue(stringWriter, weatherPredictions);

        Files.writeString(Path.of("predictions.json"), stringWriter.toString());
    }

    private static ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        //mapper.registerModule(new JavaTimeModule());

        return mapper;
    }
    */

    public List<Forecast> getForecasts() {
        return forecastRepository.findAll();
        //return forecasts;
    }

    public Forecast add(Forecast forecast) throws IOException {

        /*
        // Rows below shows how to add for JSON
        // First we add the prediction to our forecasts array
        forecast.setId(UUID.randomUUID());
        forecasts.add(forecast);
        // Then we save the array in a file (JSON)
        writeAllToFile(forecasts);
         */

        forecastRepository.save(forecast);
        return forecast;
    }

    public void update(Forecast forecastFromUser) throws IOException {
        // Updates the forecasts list
        /*
        var forecastInList = get(forecastFromUser.getId()).get();
        forecastInList.setDate(forecastFromUser.getDate());
        forecastInList.setHour(forecastFromUser.getHour());
        forecastInList.setTemperature(forecastFromUser.getTemperature());
        forecastInList.setLastModifiedBy(forecastFromUser.getLastModifiedBy());

        writeAllToFile(forecasts);
         */
    }

    public Forecast getByIndex(int i) {
        return null;
    }

    public Optional<Forecast> get(UUID id) {
        return forecastRepository.findById(id);
        //return getForecasts().stream().filter(forecast -> forecast.getId().equals(id)).findFirst();
    }

    public void getAllOnDate(LocalDate now) {
        //return forecastRepository.
    }
}
