package com.piper2.momo.parent;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.piper2.momo.android.digitalbursar.R;
import com.piper2.momo.android.digitalbursar.Transactions;
import com.piper2.momo.android.digitalbursar.tools.ClosableRelativeLayout;
import com.piper2.momo.android.digitalbursar.tools.RotateText;
import com.piper2.momo.parent.adpters.ChildViewAdapter;
import com.piper2.momo.parent.models.Child;

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
//            TODO: create new person
        });

        List<Child> cc = new ArrayList<>();

        cc.add(new Child("alex mukula"));
        cc.add(new Child("Moses tumwebaze"));

        childViewAdapter = new ChildViewAdapter(this, cc, true);
        recentChildren.setHasFixedSize(true);
        recentChildren.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recentChildren.setAdapter(childViewAdapter);
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
