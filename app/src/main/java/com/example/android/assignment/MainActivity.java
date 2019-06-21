package com.example.android.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.android.assignment.Model.Response;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

   // public static ArrayList<Response> arr=new ArrayList<>();

   // public static LinkedHashSet<Response> set=new LinkedHashSet<>();

    public static ArrayList<Response> results=new ArrayList<>();

    public static boolean flag=false;

    public static Response res[];


//    public static com.example.android.assignment.Model.Response response[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*

        FetchResponse ft=new FetchResponse(this,"https://app.deltaapp.in/api/v2/meta/data");
        ft.execute();

*/



      /*  retrofit2.Call<ResponseBody> call=RetrofitClient
                .getmInstance()
                .getRegisterApi()
                .trulyMadlyRequest();





        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                JSONArray jarr=null;
                try {

                    Log.e("MainActivity.java", "" + response.body().string() + "");

                    JSONObject jsonObject = new JSONObject(response.body().string());

                    jarr=jsonObject.getJSONArray("compatibility_questions");



                }
                catch (Exception e) {

                    Log.e("MainActivity.java",e+"");
                }
                finally{


                    //Log.e("MainActivity.java","No. of records: " +jarr.length()+ "");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
        */


    }

    public static void check()
    {



      /*  Log.e("Resp: ",arr.get(0).getImage()+" ");

        Log.e("Resp: ",arr.get(1).getImage()+" ");*/

    }

    public void verticalShow(View view)
    {
        Intent i=new Intent(this,Vertical.class);
        startActivity(i);
    }

    public void horizontalShow(View view)
    {
        Intent i=new Intent(this,Horizontal.class);
        startActivity(i);

    }


}




