package com.piper2.momo.student;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.piper2.momo.android.digitalbursar.R;
import com.piper2.momo.android.digitalbursar.Transactions;
import com.piper2.momo.android.digitalbursar.models.User;
import com.piper2.momo.android.digitalbursar.tools.RotateText;
import com.piper2.momo.android.digitalbursar.utils.network.GatewayService;
import com.piper2.momo.android.digitalbursar.utils.numbers.ConvertToCurrency;
import com.piper2.momo.student.views.ConfirmPinViewResolver;
import com.piper2.momo.student.views.RequestMoneyBottomSheet;
import com.piper2.momo.student.views.WithdrawMoneyBottomSheet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private LinearLayout btn_withdraw, btn_request, btn_goto_transactions;
    private TextView tv_student_balance, tv_student_last_amount;


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
        tv_student_balance = findViewById(R.id.tv_student_balance);
        tv_student_last_amount = findViewById(R.id.tv_student_last_amount);

        tv_student_balance.setText(new ConvertToCurrency().number(15000));
        tv_student_last_amount.setText(new ConvertToCurrency().number(4500));

        btn_withdraw.setOnClickListener(v -> {
            new WithdrawMoneyBottomSheet("withdraw money","A secure way to keep your money")
                    .showNow(getSupportFragmentManager(),"withdraw money");
        });

        btn_request.setOnClickListener(v -> {
            new RequestMoneyBottomSheet("request money","Send a request to your parent/guardian")
                    .showNow(getSupportFragmentManager(),"request money");
        });

        btn_goto_transactions.setOnClickListener(v -> {
            Transactions.startActivity(MainActivity.this);
            overridePendingTransition(0,0);
        });
    }
}
