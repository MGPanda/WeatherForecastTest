package training.weather;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.javanet.NetHttpTransport;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

public class WeatherForecast {

    /**
     * Since this part of the URL will be constant, we'll just use it as a common variable.
     */
    private final String METAWEATHER_URL = "https://www.metaweather.com/api/location";

    public String getCityWeather(String city, Date dateTime) {
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();

        /*
        We make the request and look for the city. Since the API returns an array, empty or otherwise,
        independently of the city we're looking for, we're gonna create an empty JSONArray beforehand
        and initialize it as we go along. The Exception should only happen if there's a connection error,
        not because of the code.
         */
        JSONArray weatherJsonArray = new JSONArray();
        try {
            String requestUrl = METAWEATHER_URL + "/search/?query=" + city;
            HttpRequest request = requestFactory.buildGetRequest(new GenericUrl(requestUrl));

            HttpResponse response = request.execute();
            String stringifiedResponse = response.parseAsString();
            weatherJsonArray = new JSONArray(stringifiedResponse);

        } catch (IOException e) {
            e.printStackTrace();
            return "Connection error. Please try again later.";
        }

        if (weatherJsonArray.length() == 0) {
            return "The city you're looking for does not exist. Perhaps it's written incorrectly?";
        }
        return null;
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
