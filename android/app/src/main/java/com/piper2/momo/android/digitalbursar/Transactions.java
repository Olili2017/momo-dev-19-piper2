package com.piper2.momo.android.digitalbursar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.piper2.momo.android.digitalbursar.tools.RotateText;

public class Transactions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        TextView appName = findViewById(R.id.appname);
        RotateText.build(appName, Transactions.this);

    }

    public static void startActivity(Context context){
        Intent intent = new Intent(context, Transactions.class);
        context.startActivity(intent);
    }
}
