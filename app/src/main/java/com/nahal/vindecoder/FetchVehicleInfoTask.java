package com.nahal.vindecoder;

import android.graphics.Movie;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Sukhjinder on 2/4/17.
 */

public class FetchVehicleInfoTask extends AsyncTask<String, Void, Vehicle> {
    private final String LOG_TAG = FetchVehicleInfoTask.class.getSimpleName();

    private TextView make;
    TextView model;
    TextView interiorColor;
    TextView exteriorColor;
    TextView baseMSRP;
    TextView vehicleSize;
    TextView cityMPG;
    TextView highwayMPG;


    public FetchVehicleInfoTask(TextView make, TextView model, TextView interiorColor, TextView exteriorColor,
                                TextView baseMSRP, TextView vehicleSize, TextView cityMPG, TextView highwayMPG) {
        this.make = make;
        this.model = model;
        this.interiorColor = interiorColor;
        this.exteriorColor = exteriorColor;
        this.baseMSRP = baseMSRP;
        this.vehicleSize = vehicleSize;
        this.cityMPG = cityMPG;
        this.highwayMPG = highwayMPG;
    }

    @Override
    protected Vehicle doInBackground(String... params) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String jsonStr = null;

        try {
            final String BASE_URL = "https://api.edmunds.com/api/vehicle/v2/vins/";
            final String FORMAT = "fmt";
            final String API = "api_key";

            Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                    .appendPath(params[0])
                    .appendQueryParameter(FORMAT, "json")
                    .appendQueryParameter(API, BuildConfig.API_KEY)
                    .build();

            URL url = new URL(builtUri.toString());
            Log.d("URL", url.toString());

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                return null;
            }
            jsonStr = buffer.toString();
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }
        try {
            return getVehicleDataFromJson(jsonStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    //      JSON parser
    private Vehicle getVehicleDataFromJson(String jsonStr)
            throws JSONException {

        final ArrayList<Movie> movies = new ArrayList<>();

        final String MAKE = "make";
        final String NAME = "name";
        final String MODEL = "model";
        final String PRICE = "price";
        final String BASEMSRP = "baseMSRP";
        final String CATEGORIES = "categories";
        final String VEHICLESIZE = "vehicleSize";
        final String MPG = "MPG";
        final String HIGHWAY = "highway";
        final String CITY = "city";

        String make;
        String model;
        String interiorColor;
        String exteriorColor;
        String baseMSRP;
        String vehicleSize;
        String cityMPG;
        String highwayMPG;

        JSONObject vehicleJson = new JSONObject(jsonStr);
        JSONObject makeJson = vehicleJson.getJSONObject(MAKE);
        make = makeJson.getString(NAME);

//        JSONArray resultsArray = vehicleJson.getJSONArray(RESULTS);

        JSONObject modelJson = vehicleJson.getJSONObject(MODEL);
        model = modelJson.getString(NAME);

        interiorColor = "TEST";
        exteriorColor = "TEST";

        JSONObject priceJson = vehicleJson.getJSONObject(PRICE);
        baseMSRP = priceJson.getString(BASEMSRP);

        JSONObject categoriesMSRPJson = vehicleJson.getJSONObject(CATEGORIES);
        vehicleSize = categoriesMSRPJson.getString(VEHICLESIZE);

        JSONObject MPGJson = vehicleJson.getJSONObject(MPG);
        cityMPG = MPGJson.getString(CITY);
        highwayMPG = MPGJson.getString(HIGHWAY);


        Vehicle vehicle = new Vehicle(make, model, interiorColor, exteriorColor, baseMSRP, vehicleSize, cityMPG, highwayMPG);

        return vehicle;
    }

    @Override
    protected void onPostExecute(Vehicle vehicle) {

        make.setText(vehicle.getMake());
        model.setText(vehicle.getModel());
        interiorColor.setText(vehicle.getInteriorColor());
        exteriorColor.setText(vehicle.getExteriorColor());
        baseMSRP.setText(vehicle.getBaseMSRP());
        vehicleSize.setText(vehicle.getVehicleSize());
        cityMPG.setText("City: " + vehicle.getCityMPG());
        highwayMPG.setText("Highway: " + vehicle.getHighwayMPG());
    }
}