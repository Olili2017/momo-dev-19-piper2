package com.piper2.momo.parent.views;

import android.app.Dialog;
import android.widget.TextView;

import com.piper2.momo.android.digitalbursar.R;
import com.piper2.momo.android.digitalbursar.tools.BottomSheet;
import com.piper2.momo.parent.models.Child;

import java.util.Arrays;
import java.util.List;

public class SendMoneyBottomSheet extends BottomSheet {

    private List<Child> moneyRecipients;


    public SendMoneyBottomSheet(String title, String tag) {
        super(title, tag);
        setWorkerViewLayout(R.layout.worker_send_money);
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        if (moneyRecipients.size() == 1){
            TextView singleReciverTag = (TextView) getTemplate().findViewById(R.id.tag_reciever_name);
            singleReciverTag.setText(moneyRecipients.get(0).getName());
        }

    }

    public void setMoneyRecipients(Child... moneyRecipients) {
        this.moneyRecipients = Arrays.asList(moneyRecipients);
    }
}
