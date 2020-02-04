package com.piper2.momo.parent.actions;

import com.piper2.momo.android.digitalbursar.models.Child;
import com.piper2.momo.android.digitalbursar.utils.network.GatewayService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddChild {

    private Child child;

    private OnChildAddedListener onChildAddedListener;
    private OnAddChildFiledListener onAddChildFiledListener;

    public AddChild setChild(Child child) {
        this.child = child;
        return this;
    }

    public AddChild execute(){

        Call<Child> createChildRequest = GatewayService.getInstance().getApi().createChild(this.child);

        createChildRequest.enqueue(new Callback<Child>() {
            @Override
            public void onResponse(Call<Child> call, Response<Child> response) {
                if (onChildAddedListener != null) {
                    onChildAddedListener.onChildAdded();
                }
            }

            @Override
            public void onFailure(Call<Child> call, Throwable t) {
                if (onAddChildFiledListener != null){
                    onAddChildFiledListener.onAddChildFailed();
                }
            }
        });

        return this;
    }

    public AddChild setOnChildAddedListener(OnChildAddedListener onChildAddedListener) {
        this.onChildAddedListener = onChildAddedListener;
        return this;
    }

    public AddChild setOnAddChildFiledListener(OnAddChildFiledListener onAddChildFiledListener) {
        this.onAddChildFiledListener = onAddChildFiledListener;
        return this;
    }

    public interface OnChildAddedListener {
        void onChildAdded();
    }

    public interface OnAddChildFiledListener {
        void onAddChildFailed();
    }
}
