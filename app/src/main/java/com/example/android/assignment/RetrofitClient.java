package com.example.android.assignment;

import com.example.android.assignment.Interface.RegisterAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 300 on 6/20/2019.
 */

public class RetrofitClient {

    private static final String BASE_URL="https://app.deltaapp.in/";

    private static RetrofitClient mInstance;
    private Retrofit retrofit;

    private RetrofitClient(){
        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public static RetrofitClient getmInstance()
    {
        if(mInstance==null)
            mInstance=new RetrofitClient();

        return mInstance;
    }


    public RegisterAPI getRegisterApi()
    {
        return retrofit.create(RegisterAPI.class);
    }


}
