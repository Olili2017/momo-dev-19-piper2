package com.piper2.momo.android.digitalbursar.utils.network;
//
//import android.content.Context;
//import com.piper2.momo.android.digitalbursar.models.User;
//
//import org.json.JSONObject;
//
//import retrofit2.Call;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;

public class GatewayService implements NetworkConnection {

//    private String endpoint;
//    private JSONObject response;
//    private Retrofit retrofit;//, retrofitLocationInstant;

    private static volatile NetworkConnection networkConnection;
    private static GatewayService dbService, dbServiceLoc;

//    start attributes

    /**
     * GatewayService Constructor
     */
//    public GatewayService() {
//        retrofit = new Retrofit.Builder()
//                .baseUrl(NetworkConnection.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//    }
//
//    //    start class functions
//    public static synchronized GatewayService getInstance(){
//        if (dbService == null)
//            dbService = new GatewayService();
//
//        return dbService;
//    }
//
//    public NetworkConnection getApi(){
//        return retrofit.create(NetworkConnection.class);
//    }
//
//    public static boolean canBeReached(Context context) {
//        return ConnectionManager.getInstance(context).hasInternetConnection();
//    }
//
//    @Override
//    public Call<User> getUser(int customerId) {
//        return null;
//    }

//    start properties

    /**
     * making the request
     */
//if(GatewayService.canBeReached(ChooseTransportActivity.this)) {
//        GatewayService.build().getUser().enqueue(new Callback<JSONObject>() {
//            @Override
//            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
//                Log.d("networks_log", response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<JSONObject> call, Throwable t) {
//                Toast.makeText(ChooseTransportActivity.this,"server not reached", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }else{
//        Toast.makeText(ChooseTransportActivity.this,"no internet connection.", Toast.LENGTH_SHORT).show();
//    }

}
