package com.piper2.momo.parent;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.piper2.momo.android.digitalbursar.R;
import com.piper2.momo.android.digitalbursar.Transactions;
import com.piper2.momo.android.digitalbursar.tools.BottomSheet;
import com.piper2.momo.android.digitalbursar.tools.RotateText;
import com.piper2.momo.parent.adpters.ChildViewAdapter;
import com.piper2.momo.parent.models.Child;
import com.piper2.momo.parent.tools.SendMoneyBottomSheet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView childrenRecyclerView;
    private ChildViewAdapter childViewAdapter;
    private LinearLayout btnGotoTransactions, btnSendMoney;
    private SendMoneyBottomSheet sendMoneyBottomSheet;
    private TextView btnViewAllRecipients;

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

        childrenRecyclerView = (RecyclerView) findViewById(R.id.rv_children_square_recycler);
        btnGotoTransactions = (LinearLayout) findViewById(R.id.btn_goto_transactions);
        btnSendMoney = (LinearLayout) findViewById(R.id.btn_send);
        btnViewAllRecipients = (TextView) findViewById(R.id.btn_view_all_recipients);

        List<Child> cc = new ArrayList<>();

        cc.add(new Child("alex mukula"));
        cc.add(new Child("Moses tumwebaze"));
        cc.add(new Child("ronald tumwebaze"));
        cc.add(new Child("george tumwebaze"));
        cc.add(new Child("Mesile tumwebaze"));
        cc.add(new Child("Givan tumwebaze"));

        childViewAdapter = new ChildViewAdapter(this, cc);
        childrenRecyclerView.setHasFixedSize(true);
        childrenRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        childrenRecyclerView.setAdapter(childViewAdapter);

        btnGotoTransactions.setOnClickListener(v -> {
            Transactions.startActivity(MainActivity.this);
            overridePendingTransition(0,0);
        });


        btnViewAllRecipients.setOnClickListener(v -> {
            SendMoneyActivity.startActivity(this);
            overridePendingTransition(R.anim.slide_in_up,R.anim.fade_out);
        });

        btnSendMoney.setOnClickListener(v -> {
            SendMoneyActivity.startActivity(this);
            overridePendingTransition(R.anim.slide_in_up,R.anim.fade_out);
        });
//        btnSendMoney.setOnClickListener(v -> new SendMoneyBottomSheet("Send money","Easy way to keep the young ones in shape").showNow(getSupportFragmentManager(), "send_money"));
//        btnSendMoney.setOnClickListener(v -> new BottomSheet("Send money","Easy way to keep the young ones in shape").showNow(getSupportFragmentManager(), "someTag"));

//        sendMoneyBottomSheet = new SendMoneyBottomSheet();


    }
}
