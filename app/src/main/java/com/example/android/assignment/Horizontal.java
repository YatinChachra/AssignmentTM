package com.example.android.assignment;

import android.animation.ArgbEvaluator;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;

import com.example.android.assignment.Adapters.HorizontalAdapter;
import com.example.android.assignment.Model.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;

public class Horizontal extends AppCompatActivity {

    public static ArrayList<Response> results = new ArrayList<>();

    public static Context horizontalContext;

    public static ProgressDialog progressDoalog;

    public static Response res[];

    public static ViewPager viewPager;
    public static HorizontalAdapter adapter;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal);

        progressDoalog = new ProgressDialog(Horizontal.this);
        progressDoalog.setMax(50);
        progressDoalog.setMessage("Please Wait");
        progressDoalog.setTitle("Fetching details");
        progressDoalog.setCancelable(false);
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // show it
        progressDoalog.show();

        horizontalContext = getApplicationContext();

        FetchResponse ft = new FetchResponse(this, "https://app.deltaapp.in/api/v2/meta/data");
        ft.execute();


        adapter = new HorizontalAdapter(results, this);

        viewPager = (ViewPager) findViewById(R.id.viewPager);


    }


    public static void attach() {


        viewPager.setAdapter(adapter);
        viewPager.setPadding(130, 0, 130, 0);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    /**
     * {@link AsyncTask} to perform the network request on a background thread, and then
     * update the UI with the first earthquake in the response.
     */
    class FetchResponse extends AsyncTask<URL, Void, Response> {


        WeakReference<Activity> mActivity;


        public String URL_TM;


        public FetchResponse(Activity activity, String URL_TM) {
            mActivity = new WeakReference<Activity>(activity);
            this.URL_TM = URL_TM;
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();

            progressDoalog.show();


        }

        @Override
        protected com.example.android.assignment.Model.Response doInBackground(URL... urls) {
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
            com.example.android.assignment.Model.Response earthquake = extractFeatureFromJson(jsonResponse);

            // Return the {@link Event} object as the result fo the {@link TsunamiAsyncTask}
            return earthquake;
        }

        /**
         * Update the screen with the given earthquake (which was the result of the
         * {@link }).
         */
        @Override
        protected void onPostExecute(com.example.android.assignment.Model.Response earthquake) {
       /* if (earthquake == null) {
            return;
        }*/

            Activity activity = mActivity.get();
/*
        if(activity!=null)
        {

        }*/


            attach();

            //progressDoalog.dismiss();


        }

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


        public String getPostDataString(JSONObject params) throws Exception {

            StringBuilder result = new StringBuilder();
            boolean first = true;

            Iterator<String> itr = params.keys();

            while (itr.hasNext()) {

                String key = itr.next();
                Object value = params.get(key);

                if (first)
                    first = false;
                else
                    result.append("&");

                result.append(URLEncoder.encode(key, "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(value.toString(), "UTF-8"));

            }
            return result.toString();
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

        /**
         * Return an {@link } object by parsing out information
         * about the first earthquake from the input earthquakeJSON string.
         */
        private com.example.android.assignment.Model.Response extractFeatureFromJson(String earthquakeJSON) {

            if (TextUtils.isEmpty(earthquakeJSON)) {
                return null;
            }

            try {
                JSONObject baseJsonResponse = new JSONObject(earthquakeJSON);

                Log.i("HistoryActivity.java", "Reading JSON" + baseJsonResponse);

                JSONArray jarr = baseJsonResponse.getJSONArray("compatibility_questions");

                Log.i("HistoryActivity.java", jarr.length() + " ");

                res = new Response[jarr.length()];

                for (int i = 0; i < res.length; i++) {
                    JSONObject obj = jarr.getJSONObject(i);

                    JSONObject img = obj.getJSONObject("style");

                    Log.e("MainActivity.java", obj.getString("id"));

                    res[i] = new Response(obj.getString("id"), obj.getString("question"), obj.getString("tick"), obj.getString("cross"), img.getString("medium"));

                    //Log.e("MainActivity.java",r+" ");

                    results.add(res[i]);

                }


                progressDoalog.dismiss();

                MainActivity.flag=true;
                MainActivity.results=results;
                MainActivity.res=res;

            } catch (JSONException e) {
                Log.e("GeneralSetting.java", "Problem parsing the earthquake JSON results", e);
            }
            return null;
        }
    }

// * * * * * N E T W O R K * * * * *//


}





