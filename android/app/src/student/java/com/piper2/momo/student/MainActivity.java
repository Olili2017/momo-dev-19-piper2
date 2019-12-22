package com.piper2.momo.student;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.piper2.momo.android.digitalbursar.R;
import com.piper2.momo.android.digitalbursar.Transactions;
import com.piper2.momo.android.digitalbursar.models.User;
import com.piper2.momo.android.digitalbursar.tools.RotateText;
import com.piper2.momo.android.digitalbursar.utils.network.GatewayService;
import com.piper2.momo.student.views.ConfirmPinViewResolver;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ConfirmPinViewResolver confirmWithdrawPinViewResolver;
    ConfirmPinViewResolver confirmRequestMoneyPinViewResolver;
    private LinearLayout btn_withdraw, btn_request, btn_goto_transactions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        TextView appName = findViewById(R.id.appname);
        RotateText.build(appName, MainActivity.this);

        btn_withdraw = findViewById(R.id.btn_withdraw);
        btn_request = findViewById(R.id.btn_request);
        btn_goto_transactions = findViewById(R.id.btn_goto_transactions);

        btn_withdraw.setOnClickListener(v -> confirmWithdrawPinViewResolver.showNow(getSupportFragmentManager(),"withdraw_money"));
        btn_request.setOnClickListener(v -> confirmRequestMoneyPinViewResolver.showNow(getSupportFragmentManager(),"request_money"));
        btn_goto_transactions.setOnClickListener(v -> {
            Transactions.startActivity(MainActivity.this);
            overridePendingTransition(0,0);
        });


        confirmWithdrawPinViewResolver = new ConfirmPinViewResolver("Withdraw money", "A secure way to keep your money",R.layout.withdraw_money_confirm_dialog);
        confirmWithdrawPinViewResolver.setOnSubmitListener(new ConfirmPinViewResolver.OnSubmitListener() {
            @Override
            public void onSubmitted(String password) {
                //                        onPositive(password);
                //                        fragment.dismiss();
            }

            @Override
            public void onSubmitting() {
                confirmWithdrawPinViewResolver.getPassword_form().setVisibility(View.GONE);
                confirmWithdrawPinViewResolver.getLoading_linear_layout().setVisibility(View.VISIBLE);

                if (confirmWithdrawPinViewResolver.getOnConfirmingPasswordListener() != null) {
                    confirmWithdrawPinViewResolver.getOnConfirmingPasswordListener().onConfirmingPassword(confirmWithdrawPinViewResolver.getInputPassword());
                }
            }
        }).setOnConfirmingPasswordListener(password -> {
            confirmWithdrawPinViewResolver.getTv_actions_precision().setText("1/2");
            confirmWithdrawPinViewResolver.getTv_whats_happening().setText("Verifying your pin");
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

        }).setOnConfirmingPasswordFailureListener(message -> {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();

            confirmWithdrawPinViewResolver.getLoading_linear_layout().setVisibility(View.GONE);
            confirmWithdrawPinViewResolver.getPassword_form().setVisibility(View.VISIBLE);
        });


        confirmRequestMoneyPinViewResolver = new ConfirmPinViewResolver("Request money", "Send a request to your parent/guardian",R.layout.request_money_confirm_dialog);
        confirmRequestMoneyPinViewResolver.setOnSubmitListener(new ConfirmPinViewResolver.OnSubmitListener() {
            @Override
            public void onSubmitted(String password) {
                //                        onPositive(password);
                //                        fragment.dismiss();
            }

            @Override
            public void onSubmitting() {
                confirmRequestMoneyPinViewResolver.getPassword_form().setVisibility(View.GONE);
                confirmRequestMoneyPinViewResolver.getLoading_linear_layout().setVisibility(View.VISIBLE);

                if (confirmRequestMoneyPinViewResolver.getOnConfirmingPasswordListener() != null) {
                    confirmRequestMoneyPinViewResolver.getOnConfirmingPasswordListener().onConfirmingPassword(confirmRequestMoneyPinViewResolver.getInputPassword());
                }
            }
        }).setOnConfirmingPasswordListener(password -> {
            confirmRequestMoneyPinViewResolver.getTv_actions_precision().setText("1/2");
            confirmRequestMoneyPinViewResolver.getTv_whats_happening().setText("Verifying your pin");
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

        }).setOnConfirmingPasswordFailureListener(message -> {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();

            confirmRequestMoneyPinViewResolver.getLoading_linear_layout().setVisibility(View.GONE);
            confirmRequestMoneyPinViewResolver.getPassword_form().setVisibility(View.VISIBLE);
        });
    }
}
