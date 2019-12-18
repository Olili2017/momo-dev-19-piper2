package com.piper2.momo.android.digitalbursar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.piper2.momo.android.digitalbursar.models.User;
import com.piper2.momo.android.digitalbursar.tools.ConfirmPasswordbottomSheet;
import com.piper2.momo.android.digitalbursar.utils.network.GatewayService;

import org.json.JSONObject;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ConfirmPasswordbottomSheet confirmPasswordbottomSheet;
    private Button btn_withdraw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_withdraw = findViewById(R.id.btn_withdraw);

        btn_withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//      show confirm with password fragment
//        confirmPasswordbottomSheet.show(getFragmentManager(), "confirm_user");
                confirmPasswordbottomSheet.showNow(getSupportFragmentManager(),"confirm_user");
            }
        });



        confirmPasswordbottomSheet = new ConfirmPasswordbottomSheet();
        confirmPasswordbottomSheet.setOnSubmitListener(new ConfirmPasswordbottomSheet.OnSubmitListener() {
            @Override
            public void onSubmitted(String password) {
                //                        onPositive(password);
                //                        fragment.dismiss();
            }

            @Override
            public void onSubmitting() {
                confirmPasswordbottomSheet.getPassword_form().setVisibility(View.GONE);
                confirmPasswordbottomSheet.getLoading_linear_layout().setVisibility(View.VISIBLE);

                if (confirmPasswordbottomSheet.getOnConfirmingPasswordListener() != null) {
                    confirmPasswordbottomSheet.getOnConfirmingPasswordListener().onConfirmingPassword(confirmPasswordbottomSheet.getInputPassword());
                }
            }
        });

        confirmPasswordbottomSheet.setOnConfirmingPasswordListener(new ConfirmPasswordbottomSheet.OnConfirmingPasswordListener() {
            @Override
            public void onConfirmingPassword(String password) {
                confirmPasswordbottomSheet.getTv_actions_precision().setText("1/2");
                confirmPasswordbottomSheet.getTv_whats_happening().setText("Verifying your pin");
//                TODO: validateUserWithDbService(password);

//                if(GatewayService.canBeReached(MainActivity.this)) {
//                        GatewayService.getInstance().getUser(46746).enqueue(new Callback<User>() {
//                            @Override
//                            public void onResponse(Call<User> call, Response<User> response) {
//                                Log.d("networks_log", response.body().toString());
//                                Toast.makeText(MainActivity.this,"server has response", Toast.LENGTH_SHORT).show();
//                            }
//
//                            @Override
//                            public void onFailure(Call<User> call, Throwable t) {
//                                Toast.makeText(MainActivity.this,"server not reached", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }else{
//                        Toast.makeText(MainActivity.this,"no internet connection.", Toast.LENGTH_SHORT).show();
//                    }

            }
        });

        confirmPasswordbottomSheet.setOnConfirmingPasswordFailureListener(new ConfirmPasswordbottomSheet.OnConfirmingPasswordFailureListener() {
            @Override
            public void onConfirmingPasswordFailed(String message) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();

                confirmPasswordbottomSheet.getLoading_linear_layout().setVisibility(View.GONE);
                confirmPasswordbottomSheet.getPassword_form().setVisibility(View.VISIBLE);
            }
        });
    }
}
