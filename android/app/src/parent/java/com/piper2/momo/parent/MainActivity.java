package com.piper2.momo.parent;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.piper2.momo.android.digitalbursar.R;
import com.piper2.momo.android.digitalbursar.Transactions;
import com.piper2.momo.android.digitalbursar.tools.RotateText;
import com.piper2.momo.android.digitalbursar.utils.numbers.ConvertToCurrency;
import com.piper2.momo.parent.actions.GetChildren;
import com.piper2.momo.parent.adpters.ChildViewAdapter;
import com.piper2.momo.parent.constants.Hard;
import com.piper2.momo.parent.views.AddNewChildBottomSheet;
import com.piper2.momo.parent.views.SendMoneyToNewChildBottomSheet;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView childrenRecyclerView;
    private ChildViewAdapter childViewAdapter;
    private LinearLayout btnGotoTransactions;
    private TextView btnViewAllRecipients, myBalance, myLastTransactionAmount;
    private ImageButton btnCreateChild;
    private ImageButton btnSendMoney;
    private ImageButton btnSendToNew;

    public void setMyBalance(String myBalance) {
        this.myBalance.setText(myBalance);
    }

    public void setMyLastTransactionamount(String myLastTransactionAmount) {
        this.myLastTransactionAmount.setText(myLastTransactionAmount);
    }

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
        btnSendMoney = (ImageButton) findViewById(R.id.btn_send);
        btnSendToNew = (ImageButton) findViewById(R.id.btn_send_to_new);
        btnCreateChild = (ImageButton) findViewById(R.id.btn_create_child);
        btnViewAllRecipients = (TextView) findViewById(R.id.btn_view_all_recipients);
        myBalance = (TextView) findViewById(R.id.tv_my_balance);
        myLastTransactionAmount = (TextView) findViewById(R.id.tv_last_transction_amount);

        ConvertToCurrency convertToCurrency = new ConvertToCurrency();
        setMyBalance(convertToCurrency.number(50000));
        setMyLastTransactionamount(convertToCurrency.number(20));

        final List[] cc = new List[]{new ArrayList<>()};

//        FIXME: change parent source on integration
        new GetChildren(Hard.PARENT_PHONE)
                .setOnChildrenListFetchCompleteLister((children) -> {

                    cc[0] = children;
                    childViewAdapter = new ChildViewAdapter(this, cc[0]);
                    childrenRecyclerView.setHasFixedSize(true);
                    childrenRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                    childrenRecyclerView.setAdapter(childViewAdapter);
                })
                .setOnChildrenListFetchFailedLister(() ->{


                    childViewAdapter = new ChildViewAdapter(this, cc[0]);
                    childrenRecyclerView.setHasFixedSize(true);
                    childrenRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                    childrenRecyclerView.setAdapter(childViewAdapter);
                })
                .fetch();



        btnCreateChild.setOnClickListener(v -> {
            new AddNewChildBottomSheet("Create child account","Create a secret number for your child").showNow(getSupportFragmentManager(),"add_new_child");
        });

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

        btnSendToNew.setOnClickListener(v -> new SendMoneyToNewChildBottomSheet("Send money to;","").showNow(getSupportFragmentManager(),"btn_send_to_new"));


    }
}
