package com.piper2.momo.parent.actions;

import android.util.Log;

import com.piper2.momo.android.digitalbursar.models.SendMoneyDAO;
import com.piper2.momo.android.digitalbursar.utils.network.GatewayService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendMoney {

    private float amount;

    private String sender;
    private long recievever;

    private OnSendMoneyCompletedListener onSendMoneyCompletedListener;
    private OnSendMoneyFailedListener onSendMoneyFailedListener;
    private OnSendMoneyInitiatedListener onSendMoneyInitiatedListener;

    public SendMoney(float amount){
        this.amount = amount;
    }

    public SendMoney execute(){

//        make request to withdraw from sender number
        Call<Object> sendMoneyRequest = GatewayService.getInstance().getApi().sendMoney(new SendMoneyDAO(getSender(),getAmount()));

        sendMoneyRequest.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
//                if code entered, continue to complete transaction
//                TODO: get server response
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                if (onSendMoneyFailedListener != null){
                    onSendMoneyFailedListener.onSendFailed("Could not complete transaction.");
                }
            }
        });

        return this;
    }

    private boolean confirmTransactionComplete(){

        Call<Object> sendMoneyConfirmRequest = GatewayService.getInstance().getApi().sendMoney(new SendMoneyDAO(getSender(),getAmount()));
        sendMoneyConfirmRequest.enqueue(new Callback<Object>(){
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                if (onSendMoneyFailedListener != null){
                    onSendMoneyFailedListener.onSendFailed("Could not confirm transaction. If you are sure the transaction was complete.. please contact customer support");
                }
            }
        });
        return false;
    }

    public SendMoney setSender(String senderPhone){
        this.sender = senderPhone;
        return this;
    }

    public SendMoney setReciever(long recieverAccountNumber){
        this.recievever = recieverAccountNumber;

        return this;
    }

    public float getAmount() {
        return amount;
    }

    public long getRecievever() {
        return recievever;
    }

    public String getSender() {
        return sender;
    }

    public SendMoney setOnSendFailed(OnSendMoneyFailedListener onSendMoneyFailedListener){
        this.onSendMoneyFailedListener = onSendMoneyFailedListener;
        return this;
    }

    interface OnSendMoneyInitiatedListener {
        void onSendInitiated();
    }

    interface OnSendMoneyCompletedListener {
        void onSendComplete();
    }

    public interface OnSendMoneyFailedListener{
        void onSendFailed(String message);
    }
}
