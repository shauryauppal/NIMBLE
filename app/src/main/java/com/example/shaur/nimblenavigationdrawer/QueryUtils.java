package com.example.shaur.nimblenavigationdrawer;

import android.util.Log;
import android.util.TimeFormatException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.shaur.nimblenavigationdrawer.EarthquakeActivity.LOG_TAG;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {

    /** Sample JSON response for a USGS query */
    //private static final String SAMPLE_JSON_RESPONSE = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */

    public final static List<Earth> fetchEarthquakeData(String requestUrl) {

        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }

        // Extract relevant fields from the JSON response and create an {@link Event} object
       List<Earth> earthquake = extractFeatureFromJson(jsonResponse);

        // Return the {@link Event}
        return earthquake;
    }
    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private QueryUtils() {
    }

    private static final String Location_Seperator = "of";
    /**
     * Return a list of {@link Earth} objects that has been built up from
     * parsing a JSON response.
     */
    private static List<Earth> extractFeatureFromJson(String earthquakeJSON) {

        // Create an empty ArrayList that we can start adding earthquakes to
        List<Earth> earthquakes = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // TODO: Parse the response given by the earthquakeJSON string and
            // build up a list of Earthquake objects with the corresponding data.
            JSONObject root = new JSONObject(earthquakeJSON);
            JSONArray featureArray = root.optJSONArray("features");

            for(int i=0;i<featureArray.length();i++) {


                JSONObject c = featureArray.optJSONObject(i);
                JSONObject prop = c.optJSONObject("properties");

                Double magni = (prop.getDouble("mag"));//to parse decimal value from JSON

                //Convert Magnitude DecimalFormat
                DecimalFormat formatter = new DecimalFormat("0.0");
                Double magnitude = Double.valueOf(formatter.format(magni));



                String location = prop.getString("place");



                String location1,location2;
                /**
                 * stringname.split() splits the given string in parts by specific seperator and return array string back
                 */
                if(location.contains(Location_Seperator))
                {
                    String [] parts = location.split(Location_Seperator);
                    location1=parts[0]+Location_Seperator;
                    location2 = parts[1];
                }
                else{
                    location1 = "Near the";
                    location2 = location;
                }

                Long time = prop.getLong("time");
                //To Convert Unix time to different time zone
                //Date Formatter
                Date dateobject = new Date(time);
                SimpleDateFormat dateFormatter = new SimpleDateFormat("EEE, MMM d, ''yy");
                String datetoDisplay = dateFormatter.format(dateobject);

                //Time Format
                SimpleDateFormat timeFormatter = new SimpleDateFormat("h:mm a");
                String timedisplay = timeFormatter.format(dateobject);

                //Get url from JSON text
                String url = prop.getString("url");
                //Add Data to Earth Adapter
                earthquakes.add(new Earth(magnitude,location1,location2,datetoDisplay,timedisplay,url));

                //Log.e("Test",url);

            }


        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return earthquakes;
    }

}