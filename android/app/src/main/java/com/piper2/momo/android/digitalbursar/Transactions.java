package com.piper2.momo.android.digitalbursar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.piper2.momo.android.digitalbursar.tools.RotateText;
import com.piper2.momo.android.digitalbursar.utils.numbers.ConvertToCurrency;

public class Transactions extends AppCompatActivity {

    private String myBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        TextView appName = findViewById(R.id.appname);
        TextView myBalance = findViewById(R.id.tv_my_balance);
        RotateText.build(appName, Transactions.this);


        ConvertToCurrency convertToCurrency = new ConvertToCurrency();
        setMyBalance(convertToCurrency.number(50000));

        myBalance.setText(getMyBalance());


    }


    public String getMyBalance() {
        return myBalance;
    }

    public void setMyBalance(String myBalance) {
        this.myBalance = myBalance;
    }

    public static void startActivity(Context context){
        Intent intent = new Intent(context, Transactions.class);
        context.startActivity(intent);
    }
}
