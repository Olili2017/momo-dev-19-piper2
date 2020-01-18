package com.piper2.momo.parent.views;

import android.app.Dialog;
import android.util.Log;
import android.widget.TextView;

import com.piper2.momo.android.digitalbursar.R;
import com.piper2.momo.android.digitalbursar.models.SendMoneyDAO;
import com.piper2.momo.android.digitalbursar.tools.BottomSheet;
import com.piper2.momo.android.digitalbursar.utils.network.GatewayService;
import com.piper2.momo.parent.models.Child;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    public boolean sendMoney(){
        Call<Object> sendMoneyRequest = GatewayService.getInstance().getApi().sendMoney(new SendMoneyDAO("0772649119",5000.0F));

        sendMoneyRequest.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
//                TODO: get server response
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });

        return false;
    }
}
