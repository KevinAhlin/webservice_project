package se.systementor.webservice_project.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Service;
import se.systementor.webservice_project.models.Forecast;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
public class ForecastService {

    private static List<Forecast> forecasts = new ArrayList<Forecast>();    // static means it's not bound to one instance


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

    public List<Forecast> getForecasts() {
        return forecasts;
    }

    public void add(Forecast forecast) throws IOException {
        // First we add the prediction to our forecasts array
        forecasts.add(forecast);

        // Then we save the array in a file (JSON)
        writeAllToFile(forecasts);
    }

    public void update(Forecast forecast) throws IOException {

        writeAllToFile(forecasts);
    }

    public Forecast getByIndex(int i) {
        return forecasts.get(i);
    }

    public Optional<Forecast> get(UUID id) {
        return getForecasts().stream().filter(c->c.getId().equals(id)).findFirst();
    }
}
