package com.piper2.momo.android.digitalbursar.utils.network;

import com.piper2.momo.android.digitalbursar.models.ConfirmSendMoneyDAO;
import com.piper2.momo.android.digitalbursar.models.Piner;
import com.piper2.momo.android.digitalbursar.models.SendMoneyDAO;
import com.piper2.momo.android.digitalbursar.models.User;
import com.piper2.momo.android.digitalbursar.models.Child;
import com.piper2.momo.android.digitalbursar.models.ConfirmTransactionResponseDAO;
import com.piper2.momo.android.digitalbursar.models.InitiateTransactionResponseDAO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
public interface NetworkConnection {

    String BASE_URL = "http://139.162.214.26";
//    String BASE_URL = "http://192.168.42.130:8902";

    //Check if internet is present or not
    //Boolean canBeReached(Context context);

    @GET("/rest/customers/customer/{account}")
    Call<User> getUser(@Path("account") int customerId);

    @POST("/rest/user/verify")
    Call<Piner> verifyPin(@Body User user);

    @POST("/parent/deposit/initiate")
    Call<InitiateTransactionResponseDAO> sendMoney(@Body SendMoneyDAO sendMoneyDAO);

    @PUT("/parent/deposit/confirm/")
    Call<ConfirmTransactionResponseDAO> sendMoney(@Body ConfirmSendMoneyDAO confirmSendMoneyDAO);

    @GET("/parent/{parentPhone}/children")
    Call<List<Child>> getChildren(@Path("parentPhone") String phone);

    @POST("/parent/child/add")
    Call<Child> createChild(@Body Child child);


}
