package se.systementor.webservice_project.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.systementor.webservice_project.models.Forecast;
import se.systementor.webservice_project.repositories.ForecastRepository;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;

@Service
public class ForecastService {
    private static List<Forecast> forecasts = new ArrayList<Forecast>();    // static means it's not bound to one instance

    @Autowired
    private ForecastRepository forecastRepository;

    // Constructor
    public ForecastService() {
        try {
            forecasts = readFromFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private List<Forecast> readFromFile() throws IOException {
        // If it's empty, return an empty array
        if (!Files.exists(Path.of("predictions.json")))
            return new ArrayList<>();
        ObjectMapper objectMapper = getObjectMapper();
        var jsonStr = Files.readString(Path.of("predictions.json"));

        return new ArrayList(Arrays.asList(objectMapper.readValue( jsonStr, Forecast[].class )));
    }

    /*
    private void writeAllToFile(List<Forecast> weatherPredictions) throws IOException {
        // Write the list of predictions when adding or updating a prediction
        ObjectMapper objectMapper = getObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);    // Creates indentures for the output
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        StringWriter stringWriter = new StringWriter();
        objectMapper.writeValue(stringWriter, weatherPredictions);

        Files.writeString(Path.of("predictions.json"), stringWriter.toString());
    }
    */

    private static ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();     // for JSON

        return mapper;
    }

    public List<Forecast> getForecasts() {
        return forecastRepository.findAll();
    }

    public Forecast add(Forecast forecast) throws IOException {
        forecastRepository.save(forecast);

        return forecast;
    }

    public void update(Forecast forecastFromUser) throws IOException {
        // Updates the forecasts list
        forecastRepository.save(forecastFromUser);
    }

    public Forecast getByIndex(int i) {
        return null;
    }

    public Optional<Forecast> get(UUID id) {
        return forecastRepository.findById(id);
        //return getForecasts().stream().filter(forecast -> forecast.getId().equals(id)).findFirst();
    }

    public boolean deleteById(UUID id) {
        var forecast = forecastRepository.findById(id);
        if (!forecast.isPresent()) {
            return false;
        }
        forecasts.remove(forecast);
        return true;
    }

    // Returns the average temperature per hour, including rainOrSnow parameter
    public List<Map<String, Object>> getAverageTemperaturePerHour(LocalDate date) {
        List<Object> averageTemperatureData = forecastRepository.findAverageTemperaturePerHour(date);
        List<Map<String, Object>> transformedTempData = new ArrayList<>();

        for (Object object : averageTemperatureData) {
            Object[] objectArray = (Object[]) object;

            Map<String, Object> tempDataMap = new LinkedHashMap<>();
            tempDataMap.put("Date", objectArray[0]);
            tempDataMap.put("Hour", objectArray[1]);
            tempDataMap.put("rainOrSnow", objectArray[2]);
            tempDataMap.put("Temp", objectArray[3]);

            transformedTempData.add(tempDataMap);
        }

        return transformedTempData;
    }

}
