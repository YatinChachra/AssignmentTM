package com.example.android.assignment.Model;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by 300 on 6/21/2019.
 */

public class RequestHandler extends AsyncTask<URL, Void, String> {


    WeakReference<Activity> mActivity;


    public String URL_TM;


    public RequestHandler(Activity activity, String URL_TM) {
        mActivity = new WeakReference<Activity>(activity);
        this.URL_TM = URL_TM;
    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();

        //progressDoalog.show();


    }

    @Override
    protected String doInBackground(URL... urls) {
        // Create URL object
        URL url = createUrl(URL_TM);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = "";
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            // TODO Handle the IOException
        }

        // Extract relevant fields from the JSON response and create an {@link Event} object
       // com.example.android.assignment.Model.Response earthquake = extractFeatureFromJson(jsonResponse);

        // Return the {@link Event} object as the result fo the {@link TsunamiAsyncTask}
        //return earthquake;

    return jsonResponse;
    }

    /**
     * Update the screen with the given earthquake (which was the result of the
     * {@link }).
     */
   /* @Override
    protected void onPostExecute() {
       /* if (earthquake == null) {
            return;
        }*/

      //  Activity activity = mActivity.get();
/*
        if(activity!=null)
        {

        }*/


        //attach();

        //progressDoalog.dismiss();


    //}

    /**
     * Returns new URL object from the given string URL.
     */
    private URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException exception) {
            Log.e("CreateActivity.java", "Error with creating URL", exception);
            return null;
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        if (url == null) {
            return jsonResponse;
        }

        try {


            JSONObject postDataParams = new JSONObject();
            // postDataParams.put("invoiceof", globalprofile.getEmail());


            //setting up a HTTP Request
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);


            urlConnection.connect();

            Log.e("HistoryActivity.java", "Connect check");
            int i = urlConnection.getResponseCode();
            String ii = String.valueOf(i);

            Log.e("HistoryActivity.java", ii);


            //getting response from server
            if (urlConnection.getResponseCode() == 200) {

                Log.e("HistoryActivity.java", "Status 200");


                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
        } catch (IOException e) {
            // TODO: Handle the exception
        } catch (Exception e) {
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // function must handle java.io.IOException here
                inputStream.close();
            }
        }
        return jsonResponse;
    }


    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private String readFromStream(InputStream inputStream) throws IOException {
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
}







