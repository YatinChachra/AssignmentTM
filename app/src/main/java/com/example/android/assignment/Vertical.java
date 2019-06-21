package com.example.android.assignment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;

import com.example.android.assignment.Adapters.ResponseAdapter;
import com.example.android.assignment.Model.RequestHandler;
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

import static com.example.android.assignment.MainActivity.flag;

public class Vertical extends AppCompatActivity {


    public static RecyclerView historyList;

    public static ProgressDialog progressDoalog;

    public static Response res[];

    public static ArrayList<Response> results=new ArrayList<>();

    public static Context verticalContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical);



            verticalContext=getApplicationContext();



            progressDoalog = new ProgressDialog(Vertical.this);
            progressDoalog.setMax(50);
            progressDoalog.setMessage("Please Wait");
            progressDoalog.setTitle("Fetching details");
            progressDoalog.setCancelable(false);
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);


            // show it
            progressDoalog.show();

        RequestHandler request=new RequestHandler(this,"https://app.deltaapp.in/api/v2/meta/data");
       try {
           String response  = request.execute().get();


           Log.e("Vertical.java", response);

           convertResponse(response);

           progressDoalog.dismiss();

       }
       catch (Exception e)
       {
           progressDoalog.dismiss();
       }



        historyList=(RecyclerView)findViewById(R.id.historyList);
        historyList.setLayoutManager(new LinearLayoutManager(this));
        historyList.setAdapter(new ResponseAdapter(verticalContext,results));



    }

    public static void attach()
    {

        historyList.setAdapter(new ResponseAdapter(verticalContext,results));


    }


    public static void convertResponse(String response) throws JSONException
    {

        JSONObject baseJsonResponse = new JSONObject(response);

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




    }


}

// * * * * * N E T W O R K * * * * *//







