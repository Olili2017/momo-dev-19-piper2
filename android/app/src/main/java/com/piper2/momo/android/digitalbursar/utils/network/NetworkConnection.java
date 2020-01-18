package com.piper2.momo.android.digitalbursar.utils.network;

import com.piper2.momo.android.digitalbursar.models.Piner;
import com.piper2.momo.android.digitalbursar.models.SendMoneyDAO;
import com.piper2.momo.android.digitalbursar.models.User;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
public interface NetworkConnection {

    String BASE_URL = "http://192.168.42.130:8902";

    //Check if internet is present or not
    //Boolean canBeReached(Context context);

    @GET("/rest/customers/customer/{account}")
    Call<User> getUser(@Path("account") int customerId);

    @POST("/rest/user/verify")
    Call<Piner> verifyPin(@Body User user);

    @POST("/parent/deposit/initiate")
    Call<Object> sendMoney(@Body SendMoneyDAO sendMoneyDAO);


}
