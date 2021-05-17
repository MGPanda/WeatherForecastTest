package training.weather;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/**
 * The WeatherService class is dedicated entirely to the interaction with the API and the network in general.
 */
public class WeatherService {
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
    public String makeRequest(String requestUrl) throws IOException {
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
    public String searchWoeIdByName(String city) throws IOException {
        String requestUrl = METAWEATHER_URL + "/search/?query=" + city;

        JSONArray searchResults = new JSONArray(makeRequest(requestUrl));

        if (searchResults.length() == 0) {
            return null;
        } else {
            JSONObject cityData = searchResults.getJSONObject(0);
            return cityData.get("woeid").toString();
        }

    }

    /**
     * This method receives a woeId and a dateTime and will use them to look for a specific weather report.
     * If the date passed by the user is null, we'll just use the current one.
     * @param woeId The woeId of the city we're looking for.
     * @param dateTime The date and time of the report we're looking for.
     * @return The method returns the first report that fits the terms we're looking for.
     * @throws IOException The makeRequest method might throw an exception if there's a network error.
     */
    public JSONObject getReportWithWoeId(String woeId, Date dateTime) throws IOException {
        if (dateTime == null) {
            dateTime = new Date();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTime);

        String requestUrl = METAWEATHER_URL + "/" + woeId + "/" + calendar.get(Calendar.YEAR) + "/"
                + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.DAY_OF_MONTH);

        JSONArray fullForecast = new JSONArray(makeRequest(requestUrl));

        if (fullForecast.length() == 0) {
            return null;
        } else {
            return fullForecast.getJSONObject(0);
        }

    }
}
