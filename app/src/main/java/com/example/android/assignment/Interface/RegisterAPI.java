package com.example.android.assignment.Interface;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by 300 on 6/20/2019.
 */

public interface RegisterAPI {


    @GET("api/v2/meta/data")
    Call<ResponseBody> trulyMadlyRequest();


  /*  @GET("api/v2/meta/data")
    Call<List<Response>> trulyMadlyRequest();
*/


}
