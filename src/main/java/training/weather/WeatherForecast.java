package training.weather;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.javanet.NetHttpTransport;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

public class WeatherForecast {

    /**
     * Since this part of the URL will be constant, we'll just use it as a common variable.
     */
    private final String METAWEATHER_URL = "https://www.metaweather.com/api/location";

    /**
     * This method will make a full request to the API; it will create the HttpRequest via a RequestFactory,
     * execute it, treat it properly and return it as a (more easily understandable) parsed String.
     *
     * @param requestUrl The url that we're going to access.
     * @return The response, parsed as a String.
     * @throws IOException The exception should only ever happen if there's a network error.
     */
    private String makeRequest(String requestUrl) throws IOException {
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        HttpRequest request = requestFactory.buildGetRequest(new GenericUrl(requestUrl));

        HttpResponse response = request.execute();
        return response.parseAsString();
    }

    /**
     * We make the request and look for the city. For simplicity's sake, we're returning just the first
     * result.
     *
     * @param city The city we're gonna be looking for, passed as an argument.
     * @return We return the woeId of the city as a String. If we don't find anything, we'll just return
     * null and inform the user.
     * @throws IOException The makeRequest method might throw an exception if there's a network error.
     */
    private String searchCityWoeIdByName(String city) throws IOException {
        String requestUrl = METAWEATHER_URL + "/search/?query=" + city;

        JSONArray searchResults = new JSONArray(makeRequest(requestUrl));

        if (searchResults.length() == 0) {
            return null;
        } else {
            JSONObject cityData = searchResults.getJSONObject(0);
            return cityData.get("woeid").toString();
        }

    }

    private JSONObject getReportWithWoeId(String woeId, Date dateTime) throws IOException {
        if (dateTime == null) {
            dateTime = new Date();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTime);

        String requestUrl = METAWEATHER_URL + "/" + woeId + "/" + calendar.get(Calendar.YEAR) + "/"
                + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.DAY_OF_MONTH);

        JSONArray fullForecast = new JSONArray(makeRequest(requestUrl));

        return fullForecast.getJSONObject(0);
    }

    private String printReport(JSONObject weatherReport) {
        return "";
    }

    public String getCityWeather(String city, Date dateTime) {
        try {
            String cityId = searchCityWoeIdByName(city);
            JSONObject weatherReport;

            if (cityId == null) {
                return "The city you're looking for does not exist. Perhaps it's written incorrectly?";
            } else {
                weatherReport = getReportWithWoeId(cityId, dateTime);
            }

            if (weatherReport == null) {
                return "The city you're looking for does not exist. Perhaps it's written incorrectly?";
            } else {
                return printReport(weatherReport);
            }

        } catch (IOException e) {
            e.printStackTrace();
            return "Connection error. Please try again later.";
        }
    }

//	public String getCityWeather(String city, Date datetime) throws IOException {
//		if (datetime == null) {
//			datetime = new Date();
//		}
//		if (datetime.before(new Date(new Date().getTime() + (1000 * 60 * 60 * 24 * 6)))) {
//			HttpRequestFactory rf = new NetHttpTransport().createRequestFactory();
//			HttpRequest req = rf
//				.buildGetRequest(new GenericUrl("https://www.metaweather.com/api/location/search/?query=" + city));
//			String r = req.execute().parseAsString();
//			JSONArray array = new JSONArray(r);
//			String woe = array.getJSONObject(0).get("woeid").toString();
//			rf = new NetHttpTransport().createRequestFactory();
//			req = rf.buildGetRequest(new GenericUrl("https://www.metaweather.com/api/location/" + woe));
//			r = req.execute().parseAsString();
//			JSONArray results = new JSONObject(r).getJSONArray("consolidated_weather");
//			for (int i = 0; i < results.length(); i++) {
//				if (new SimpleDateFormat("yyyy-MM-dd").format(datetime)
//					.equals(results.getJSONObject(i).get("applicable_date").toString())) {
//					return results.getJSONObject(i).get("weather_state_name").toString();
//				}
//			}
//		}
//		return "";
//	}
}
