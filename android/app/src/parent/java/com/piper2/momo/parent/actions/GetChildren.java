package com.piper2.momo.parent.actions;

import com.piper2.momo.android.digitalbursar.utils.network.GatewayService;
import com.piper2.momo.android.digitalbursar.models.Child;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetChildren {

    private String parentPhoneNUmber;

    private OnChildrenListFetchCompleteListener onChildrenListFetchCompleteLister;
    private OnChildrenListFetchFailedLister onChildrenListFetchFailedLister;

    public GetChildren(String parentPhoneNUmber) {
        this.parentPhoneNUmber = parentPhoneNUmber;
    }

    public GetChildren fetch(){
        Call<List<Child>> fetchChildrenRequest = GatewayService.getInstance().getApi().getChildren(parentPhoneNUmber);

        fetchChildrenRequest.enqueue(new Callback<List<Child>>() {
            @Override
            public void onResponse(Call<List<Child>> call, Response<List<Child>> response) {
                if (onChildrenListFetchCompleteLister != null){
                    onChildrenListFetchCompleteLister.onChildrenListFetchComplete(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Child>> call, Throwable t) {
                if (onChildrenListFetchFailedLister != null) {
                    onChildrenListFetchFailedLister.onChildrenListFetchFailed();
                }
            }
        });
        return null;
    }

    public GetChildren setOnChildrenListFetchCompleteLister(OnChildrenListFetchCompleteListener onChildrenListFetchCompleteLister) {
        this.onChildrenListFetchCompleteLister = onChildrenListFetchCompleteLister;
        return this;
    }

    public GetChildren setOnChildrenListFetchFailedLister(OnChildrenListFetchFailedLister onChildrenListFetchFailedLister) {
        this.onChildrenListFetchFailedLister = onChildrenListFetchFailedLister;
        return this;
    }

    public interface OnChildrenListFetchCompleteListener {
        void onChildrenListFetchComplete(List<Child> children);
    }

    public interface OnChildrenListFetchFailedLister {
        void onChildrenListFetchFailed();
    }
}
