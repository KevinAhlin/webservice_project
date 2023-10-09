package se.systementor.webservice_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.systementor.webservice_project.models.Forecast;
import se.systementor.webservice_project.services.ForecastService;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.*;
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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String timeStamp = "2020-01-01";
		TemporalAccessor temporalAccessor = formatter.parse(timeStamp);
		LocalDateTime localDateTime = LocalDateTime.from(temporalAccessor);
		ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
		Instant result = Instant.from(zonedDateTime);

		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String date = "2023-05-08";
		// Convert string 'date' to LocalDate
		LocalDate localDate = LocalDate.parse(date, dateFormatter);
		Instant resultInstant = Instant.from(localDate);
		*/

		//forecastService.getAllOnDate(LocalDate.now());

		var scanner = new Scanner(System.in);

		while (true) {
			showMenu();
			System.out.println("Choose an action: ");
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
			else if (sel == 4) {
				//deletePrediction(scanner);
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
		for (var forecast : forecastService.getForecasts()) {
			System.out.printf("%d: %d, Time %d:00, Temp:%d %n",
					num,
					forecast.getPredictionDate(),
					forecast.getPredictionHour(),
					forecast.getPredictionTemperature()
			);
			num++;
		}
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

	private void deletePrediction(Scanner scanner, UUID id) {
		System.out.println("Which forecast would you like to delete?");
		int forecastId = scanner.nextInt();
		var forecast = forecastService.get(id);

		forecastService.deleteById(id);
	}


	public void showMenu() {
		System.out.println("--------------------");
		System.out.println("1. List predictions");
		System.out.println("2. Create new prediction");
		System.out.println("3. Update a prediction");
		System.out.println("4. Delete a prediction");
		System.out.println("9. Exit");
		System.out.println("--------------------");
	}
}
