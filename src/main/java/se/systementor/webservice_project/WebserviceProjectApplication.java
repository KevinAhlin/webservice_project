package se.systementor.webservice_project;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.systementor.webservice_project.models.BlogPost;
import se.systementor.webservice_project.models.DataSource;
import se.systementor.webservice_project.models.Forecast;
import se.systementor.webservice_project.services.ForecastService;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

@SpringBootApplication
public class WebserviceProjectApplication implements CommandLineRunner {

	@Autowired
	private ForecastService forecastService;

	public static void main(String[] args) {
		SpringApplication.run(WebserviceProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*
		var objectMapper = new ObjectMapper();
		var forecast = new Forecast();
		forecast.setId(UUID.randomUUID());
		forecast.setPredictionDate(LocalDate.now());
		forecast.setPredictionHour(12);
		forecast.setPredictionTemperature(12);
		forecast.setDataSource(DataSource.Console);

		// Serialization - take POJO and make in into JSON
		String json = objectMapper.writeValueAsString(forecast);
		System.out.println(json);	// {"id":"428e4e9f-3cc5-4897-88b2-3c6ac2f8d481","date":20230101,"hour":12,"temperature":12.0}

		// Deserialization - take JSON and turn in into POJO
		Forecast forecast2 = objectMapper.readValue(json, Forecast.class);
		*/

		forecastService.getAllOnDate(LocalDate.now());

		var scanner = new Scanner(System.in);

		while (true) {
			showMenu();
			System.out.println("Action: ");
			int sel = scanner.nextInt();
			if (sel == 1) {
				listPrediction();
			}
			else if (sel == 2) {
				addPrediction(scanner);
			}
			else if (sel == 3) {
				updatePrediction(scanner);
			}
			else if (sel == 9) {
				break;
			}
			else {
				System.out.println("Choose an existing action.");
			}
		}
	}

	private void listPrediction() {
		System.out.println("*** LIST OF PREDICTIONS ***");

		int num = 1;
		/*
		for (var forecast : forecastService.getForecasts()) {
			System.out.printf("%t: %d, Time %d:00, Temp:%d %n",
					num,
					forecast.getPredictionDate(),
					forecast.getPredictionHour(),
					forecast.getPredictionTemperature()
			);
			num++;
		}

		 */
		//forecastService.ForecastService();
	}

	private void addPrediction(Scanner scanner) throws IOException {
		// Input on day, hour, temp
		// Call on the service - Save
		System.out.println("--- CREATE PREDICTION ---");

		// Input the day/date
		System.out.printf("Put in the day, e.g, '%s': ",
				new SimpleDateFormat("yyyyMMdd").format(new Date()));
		int day = scanner.nextInt();

		// Input the hour
		System.out.println("Insert hour: ");
		int hour = scanner.nextInt();

		// Input the temp
		System.out.println("Insert temperature: ");
		int temp = scanner.nextInt();

		// Create a new forecast prediction
		var forecast = new Forecast();
		forecast.setId(UUID.randomUUID());
		forecast.setPredictionDate(LocalDate.now());
		forecast.setPredictionHour(hour);
		forecast.setPredictionTemperature(temp);

		forecastService.add(forecast);
	}

	private void updatePrediction(Scanner scanner) throws IOException {
		// Show the list of predictions
		listPrediction();

		// Select a prediction to update
		System.out.println("Select a row to update: ");
		int row = scanner.nextInt();

		var forecast = forecastService.getByIndex(row-1);
		/*
		System.out.printf("DATE: %d, TIME: %d, CURRENT TEMP: %f %n",
				forecast.getPredictionDate(),
				forecast.getPredictionHour(),
				forecast.getPredictionTemperature()
		);
		 */
		System.out.println("Insert the new temperature");
		int temp = scanner.nextInt();
		forecast.setPredictionTemperature(temp);

		forecastService.update(forecast);
	}


	public void showMenu() {
		System.out.println("--------------------");
		System.out.println("1. List predictions");
		System.out.println("2. Create prediction");
		System.out.println("3. Update prediction");
		System.out.println("9. Exit");
		System.out.println("--------------------");
	}
}
