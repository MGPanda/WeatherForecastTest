package training.weather;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
     * Current time but a non-existing city (this and passing a null city will give us the same result).
     */
    @Test
    public void wrongCityTest() {
        WeatherForecast weatherForecast = new WeatherForecast();
        String forecast = weatherForecast.getCityWeather("Uxlyzn", new Date());
        System.out.println(forecast);
    }

    /**
     * Existing city, null time.
     */
    @Test
    public void noTimeTest() {
        WeatherForecast weatherForecast = new WeatherForecast();
        String forecast = weatherForecast.getCityWeather("Madrid", null);
        System.out.println(forecast);
    }

    /**
     * Existing city, date older than expected.
     */
    @Test
    public void oldDateTest() throws ParseException {
        String dateString = "16/09/1996";
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateString);

        WeatherForecast weatherForecast = new WeatherForecast();
        String forecast = weatherForecast.getCityWeather("Madrid", date);
        System.out.println(forecast);
    }

    /**
     * Using the current date, we try if the API's ability to give country data would affect in any way.
     */
    @Test
    public void countryTest() {
        WeatherForecast weatherForecast = new WeatherForecast();
        String forecast = weatherForecast.getCityWeather("Spain", new Date());
        System.out.println(forecast);
    }
}