package com.piper2.momo.student;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.piper2.momo.android.digitalbursar.R;
import com.piper2.momo.android.digitalbursar.models.User;
import com.piper2.momo.android.digitalbursar.tools.ConfirmPinBottomSheet;
import com.piper2.momo.android.digitalbursar.utils.network.GatewayService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ConfirmPinBottomSheet confirmPinBottomSheet;
    private Button btn_withdraw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_withdraw = findViewById(R.id.btn_withdraw);

        btn_withdraw.setOnClickListener(v -> {
            confirmPinBottomSheet.showNow(getSupportFragmentManager(),"confirm_user");
        });



        confirmPinBottomSheet = new ConfirmPinBottomSheet();
        confirmPinBottomSheet.setOnSubmitListener(new ConfirmPinBottomSheet.OnSubmitListener() {
            @Override
            public void onSubmitted(String password) {
                //                        onPositive(password);
                //                        fragment.dismiss();
            }

            @Override
            public void onSubmitting() {
                confirmPinBottomSheet.getPassword_form().setVisibility(View.GONE);
                confirmPinBottomSheet.getLoading_linear_layout().setVisibility(View.VISIBLE);

                if (confirmPinBottomSheet.getOnConfirmingPasswordListener() != null) {
                    confirmPinBottomSheet.getOnConfirmingPasswordListener().onConfirmingPassword(confirmPinBottomSheet.getInputPassword());
                }
            }
        });

        confirmPinBottomSheet.setOnConfirmingPasswordListener(new ConfirmPinBottomSheet.OnConfirmingPasswordListener() {
            @Override
            public void onConfirmingPassword(String password) {
                confirmPinBottomSheet.getTv_actions_precision().setText("1/2");
                confirmPinBottomSheet.getTv_whats_happening().setText("Verifying your pin");
//                TODO: validateUserWithDbService(password);

                if(GatewayService.canBeReached(MainActivity.this)) {
                    Call<User> getUser = GatewayService.getInstance().getApi().getUser(435545);
                    getUser.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            Toast.makeText(MainActivity.this,"done feetching", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Log.d("fail",call.toString());
                        }
                    });
                    }else{
                        Toast.makeText(MainActivity.this,"no internet connection.", Toast.LENGTH_SHORT).show();
                    }

            }
        });

        confirmPinBottomSheet.setOnConfirmingPasswordFailureListener(new ConfirmPinBottomSheet.OnConfirmingPasswordFailureListener() {
            @Override
            public void onConfirmingPasswordFailed(String message) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();

                confirmPinBottomSheet.getLoading_linear_layout().setVisibility(View.GONE);
                confirmPinBottomSheet.getPassword_form().setVisibility(View.VISIBLE);
            }
        });
    }
}
