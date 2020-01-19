package com.piper2.momo.parent.actions;

import android.util.Log;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.piper2.momo.android.digitalbursar.models.ConfirmSendMoneyDAO;
import com.piper2.momo.android.digitalbursar.models.SendMoneyDAO;
import com.piper2.momo.android.digitalbursar.utils.network.GatewayService;
import com.piper2.momo.parent.models.ConfirmTransactionResponseDAO;
import com.piper2.momo.parent.models.InitiateTransactionResponseDAO;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendMoney {

    private float amount;

    private String reference;
    private String sender;
    private long reciever;

    private OnSendMoneyCompletedListener onSendMoneyCompletedListener;
    private OnSendMoneyFailedListener onSendMoneyFailedListener;
    private OnSendMoneyInitiatedListener onSendMoneyInitiatedListener;

    public void setOnSendMoneyInitiatedListener(OnSendMoneyInitiatedListener onSendMoneyInitiatedListener) {
        this.onSendMoneyInitiatedListener = onSendMoneyInitiatedListener;
    }



    public SendMoney(float amount){
        this.amount = amount;
    }

    public SendMoney execute(){

//        make request to withdraw from sender number
        Call<InitiateTransactionResponseDAO> sendMoneyRequest = GatewayService.getInstance().getApi().sendMoney(new SendMoneyDAO(getSender(),getAmount()));

        sendMoneyRequest.enqueue(new Callback<InitiateTransactionResponseDAO>() {
            @Override
            public void onResponse(Call<InitiateTransactionResponseDAO> call, Response<InitiateTransactionResponseDAO> response) {
//                if code entered, continue to complete transaction
//                TODO: get server response
                if (response.body().getMessage().contains("Success!")){
                    setReference(response.body().getReference());
                    if (onSendMoneyInitiatedListener != null){
                        onSendMoneyInitiatedListener.onSendInitiated();
                    }
                }

            }

            @Override
            public void onFailure(Call<InitiateTransactionResponseDAO> call, Throwable t) {
                if (onSendMoneyFailedListener != null){
                    onSendMoneyFailedListener.onSendFailed("Could not complete transaction.");
                }
            }
        });

        setOnSendMoneyInitiatedListener(this::confirmTransactionComplete);

        return this;
    }

    private void confirmTransactionComplete(){

        Call<ConfirmTransactionResponseDAO> sendMoneyConfirmRequest = GatewayService.getInstance().getApi().sendMoney(new ConfirmSendMoneyDAO(getReference(), getReciever()));
        sendMoneyConfirmRequest.enqueue(new Callback<ConfirmTransactionResponseDAO>(){
            @Override
            public void onResponse(Call<ConfirmTransactionResponseDAO> call, Response<ConfirmTransactionResponseDAO> response) {
                if (response.body().getMessage().equals("OK")){
                    if (onSendMoneyCompletedListener != null){
                        onSendMoneyCompletedListener.onSendComplete(response.body().getData());
                    }
                } else {
                    Log.d("sendmoney", "confirming transaction with unknown response");
                    if (onSendMoneyFailedListener != null){
                        onSendMoneyFailedListener.onSendFailed(response.body().getMessage());
                    }
                }

            }

            @Override
            public void onFailure(Call<ConfirmTransactionResponseDAO> call, Throwable t) {
                if (onSendMoneyFailedListener != null){
                    onSendMoneyFailedListener.onSendFailed("Could not confirm transaction. If you are sure the transaction was complete.. please contact customer support");
                }
            }
        });
    }

    public SendMoney setSender(String senderPhone){
        this.sender = senderPhone;
        return this;
    }

    public SendMoney setReciever(long recieverAccountNumber){
        this.reciever = recieverAccountNumber;
        return this;
    }

    public float getAmount() {
        return amount;
    }

    public long getReciever() {
        return reciever;
    }

    public String getSender() {
        return sender;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public SendMoney setOnSendMoneyCompletedListener(OnSendMoneyCompletedListener onSendMoneyCompletedListener) {
        this.onSendMoneyCompletedListener = onSendMoneyCompletedListener;
        return this;
    }

    public SendMoney setOnSendFailed(OnSendMoneyFailedListener onSendMoneyFailedListener){
        this.onSendMoneyFailedListener = onSendMoneyFailedListener;
        return this;
    }

    interface OnSendMoneyInitiatedListener {
        void onSendInitiated();
    }

    public interface OnSendMoneyCompletedListener {
        void onSendComplete(Object data);
    }

    public interface OnSendMoneyFailedListener{
        void onSendFailed(String message);
    }
}
