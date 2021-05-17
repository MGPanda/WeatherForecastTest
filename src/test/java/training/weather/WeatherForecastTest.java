package training.weather;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Test;

public class WeatherForecastTest {

    /**
     * Basic test; a correct city and the current date.
     */
    @Test
    public void basicTest() {
        WeatherForecast weatherForecast = new WeatherForecast();
        String forecast = weatherForecast.getCityWeather("Madrid", new Date());
        System.out.println(forecast);
    }

    /**
     * Current time but a non-existing city.
     */
    @Test
    public void wrongCityTest() {
        WeatherForecast weatherForecast = new WeatherForecast();
        String forecast = weatherForecast.getCityWeather("Uxlyzn", new Date());
        System.out.println(forecast);
    }
}