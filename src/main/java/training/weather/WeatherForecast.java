package training.weather;

import java.io.IOException;
import java.util.Date;

import org.json.JSONObject;

/**
 * The WeatherForecast class is the one that the user will directly interact with.
 */
public class WeatherForecast {

    /**
     * An instance of our WeatherService class, which will directly interact with the API.
     */
    private final WeatherService WEATHER_SERVICE = new WeatherService();

    /**
     * A simple function that returns a better formatted version of the weather report, to be sent to the user.
     * @param weatherReport The weather report in JSONObject format.
     * @param city The city we searched for initially. This is just for aesthetic purposes.
     * @return We return a large string with all the information, on a more easily readable format.
     */
    private String printReport(JSONObject weatherReport, String city) {
        return "Weather report for " + city + ", " + weatherReport.get("applicable_date") + "\n"
                + weatherReport.get("weather_state_name").toString() + "\n"
                + "Wind direction: " + weatherReport.get("wind_direction_compass").toString() + "\n"
                + "Wind speed: " + weatherReport.get("wind_speed").toString() + "km/h\n"
                + "Minimum temperature: " + weatherReport.get("min_temp").toString() + "º\n"
                + "Maximum temperature: " + weatherReport.get("max_temp").toString() + "º\n"
                + "Average temperature: " + weatherReport.get("the_temp").toString() + "º\n"
                + "Humidity: " + weatherReport.get("humidity").toString() + "%\n";
    }

    /**
     * This method will allow the user to directly search for a specific weather report, providing a city name
     * and a date.
     * @param city The name of the city we're looking for.
     * @param dateTime The date of the weather report we're looking for. From the API's documentation, we can get
     *                 info of most locations "from early 2013 to 5-10 days in the future".
     * @return The method returns a string that will be shown directly to the user.
     */
    public String getCityWeather(String city, Date dateTime) {
        try {
            String cityId = WEATHER_SERVICE.searchWoeIdByName(city);
            JSONObject weatherReport;

            if (cityId == null) {
                return "The city you're looking for does not exist. Perhaps it's written incorrectly?";
            } else {
                weatherReport = WEATHER_SERVICE.getReportWithWoeId(cityId, dateTime);
            }

            if (weatherReport == null) {
                return "No weather reports for the specified date.";
            } else {
                return printReport(weatherReport, city);
            }

        } catch (IOException e) {
            e.printStackTrace();
            return "Connection error. Please try again later.";
        }
    }

    /*
      Old code.
     */
//    public String getCityWeather(String city, Date datetime) throws IOException {
//        if (datetime == null) {
//            datetime = new Date();
//        }
//        if (datetime.before(new Date(new Date().getTime() + (1000 * 60 * 60 * 24 * 6)))) {
//            HttpRequestFactory rf = new NetHttpTransport().createRequestFactory();
//            HttpRequest req = rf
//                    .buildGetRequest(new GenericUrl("https://www.metaweather.com/api/location/search/?query=" + city));
//            String r = req.execute().parseAsString();
//            JSONArray array = new JSONArray(r);
//            String woe = array.getJSONObject(0).get("woeid").toString();
//            rf = new NetHttpTransport().createRequestFactory();
//            req = rf.buildGetRequest(new GenericUrl("https://www.metaweather.com/api/location/" + woe));
//            r = req.execute().parseAsString();
//            JSONArray results = new JSONObject(r).getJSONArray("consolidated_weather");
//            for (int i = 0; i < results.length(); i++) {
//                if (new SimpleDateFormat("yyyy-MM-dd").format(datetime)
//                        .equals(results.getJSONObject(i).get("applicable_date").toString())) {
//                    return results.getJSONObject(i).get("weather_state_name").toString();
//                }
//            }
//        }
//        return "";
//    }
}
