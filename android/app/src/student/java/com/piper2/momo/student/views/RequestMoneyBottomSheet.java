package com.piper2.momo.student.views;

import android.app.Dialog;
import android.os.Handler;

import com.piper2.momo.android.digitalbursar.R;
import com.piper2.momo.android.digitalbursar.tools.BottomSheet;

public class RequestMoneyBottomSheet extends BottomSheet {

    public RequestMoneyBottomSheet(String title, String tag) {
        super(title, tag);
        setWorkerViewLayout(R.layout.request_money_confirm_dialog);
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        setNextStepActionTag("Please wait while request is processed");
    }

    @Override
    public void doFinalThing() {
        super.doFinalThing();

//        TODO: request money here
        new Handler().postDelayed(() -> {
            if (onAllActionsCompletedListener != null){
                onAllActionsCompletedListener.onAllActionsCompleted();
            }
        },1000);
    }
}
