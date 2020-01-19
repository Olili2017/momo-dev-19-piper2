package com.piper2.momo.parent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.piper2.momo.android.digitalbursar.R;
import com.piper2.momo.parent.actions.GetChildren;
import com.piper2.momo.parent.adpters.ChildViewAdapter;
import com.piper2.momo.parent.constants.Hard;
import com.piper2.momo.parent.views.SendMoneyToNewChildBottomSheet;

import java.util.ArrayList;
import java.util.List;

public class SendMoneyActivity extends AppCompatActivity {


    private LinearLayout btnSendToNew;
    private RecyclerView recentChildren;
    private ChildViewAdapter childViewAdapter;
    private RelativeLayout rlActivityContainer;



    boolean isClosing = false;
    boolean isScrollingUp = false;
    boolean isScrollingDown = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_money);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }


        btnSendToNew = (LinearLayout) findViewById(R.id.btn_send_to_new);
        recentChildren = (RecyclerView) findViewById(R.id.rv_children_wide_recycler);

        btnSendToNew.setOnClickListener(v -> {
            new SendMoneyToNewChildBottomSheet("Send money to;","").showNow(getSupportFragmentManager(),"btn_send_to_new");
        });

        final List[] cc = new List[]{new ArrayList<>()};

//        FIXME: change parent source on integration
        new GetChildren(Hard.PARENT_PHONE)
                .setOnChildrenListFetchCompleteLister((children) -> {

                    cc[0] = children;
                    childViewAdapter = new ChildViewAdapter(this, cc[0], true);
                    recentChildren.setHasFixedSize(true);
                    recentChildren.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
                    recentChildren.setAdapter(childViewAdapter);
                })
                .setOnChildrenListFetchFailedLister(() ->{


                    childViewAdapter = new ChildViewAdapter(this, cc[0], true);
                    recentChildren.setHasFixedSize(true);
                    recentChildren.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
                    recentChildren.setAdapter(childViewAdapter);
                })
                .fetch();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.slide_out_down);
    }

    public static void startActivity(Context context){
        Intent intent = new Intent(context, SendMoneyActivity.class);
        context.startActivity(intent);
    }

}
