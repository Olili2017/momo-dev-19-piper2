package com.piper2.momo.parent.views;

import android.app.Dialog;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.piper2.momo.android.digitalbursar.R;
import com.piper2.momo.android.digitalbursar.models.SendMoneyDAO;
import com.piper2.momo.android.digitalbursar.tools.BottomSheet;
import com.piper2.momo.android.digitalbursar.utils.network.GatewayService;
import com.piper2.momo.parent.actions.SendMoney;
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

        setNextStepActionTag("Please wait while transaction is processed");
        if (moneyRecipients.size() == 1){
            TextView singleReciverTag = (TextView) getTemplate().findViewById(R.id.tag_reciever_name);
            singleReciverTag.setText(moneyRecipients.get(0).getName());
        }

    }

    public void setMoneyRecipients(Child... moneyRecipients) {
        this.moneyRecipients = Arrays.asList(moneyRecipients);
    }

    @Override
    public void doFinalThing() {
//        FIXME: add iteration for more students at once

        EditText sendAmountEditText = getTemplate().findViewById(R.id.et_amount_to_send);
        new SendMoney(Float.parseFloat(sendAmountEditText.getText().toString()))
                .setSender("0772649119")
                .setReciever(moneyRecipients.get(0).getAccount())
                .setOnSendFailed(message -> {
                    if (onConfirmingPasswordFailureListener != null){
                        onConfirmingPasswordFailureListener.onConfirmingPasswordFailed("Sending money operation failed. please try again!");
                    }
                })
                .execute();
    }
}
