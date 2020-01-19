package com.piper2.momo.parent.adpters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.piper2.momo.android.digitalbursar.R;
import com.piper2.momo.parent.models.Child;
import com.piper2.momo.parent.views.SendMoneyBottomSheet;

import java.util.List;

public class ChildViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<Child> children;
    private boolean isWideView;

    private SendMoneyBottomSheet sendMoneyBottomSheet;

    public ChildViewAdapter(Context context, List<Child> children, boolean... isWide){
        mContext = context;
        this.children = children;
        this.isWideView = isWide.length > 0;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ViewType.HORIZONTAL_LIST_VIEW){
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_child_square, parent, false);
            return new ChildViewHolder(view);
        } else if(viewType == ViewType.VERTICAL_LIST_VIEW) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_child_wide, parent, false);
            return new ChildViewHolder(view);
        }else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_no_child, parent, false);
            return new NoChildViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (children == null && getItemCount() == 1){
            buildNoChildViewHolder((NoChildViewHolder) holder);
        } else {
            buildChildViewHolder((ChildViewHolder) holder, this.children.get(position));
        }
    }

    private void buildChildViewHolder(ChildViewHolder childViewHolder, Child child){
        childViewHolder.childName.setText(child.getName());
        childViewHolder.childImage.setImageResource(R.drawable.ic_person_white_24dp);

//        event listeners for the wide child list
        if (isWideView){
            childViewHolder.childAccountNumber.setText(String.valueOf(child.getAccountNumber()));
        }

        childViewHolder.container.setOnClickListener(v -> {
            openSendMoneyBottomSheet(child);
        });

        childViewHolder.childImage.setOnClickListener(v -> {
            openSendMoneyBottomSheet(child);
        });

        childViewHolder.childName.setOnClickListener(v -> {
            openSendMoneyBottomSheet(child);
        });
    }

    private void openSendMoneyBottomSheet(Child child){
        sendMoneyBottomSheet = new SendMoneyBottomSheet("Send money to;","");
        sendMoneyBottomSheet.setMoneyRecipients(child);
        sendMoneyBottomSheet.showNow(((FragmentActivity) mContext).getSupportFragmentManager(),"set money");
    }

    private void buildNoChildViewHolder(NoChildViewHolder childViewHolder){
        childViewHolder.message.setText("No children added yet...");
    }

    @Override
    public int getItemCount() {
        if (children == null){
            return 1;
        }
        return children.size() > 0 ? children.size() : 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (children == null && getItemCount() == 1){
            return ViewType.EMPTY_LIST_VIEW;
        }else {
            return (!isWideView) ? ViewType.HORIZONTAL_LIST_VIEW : ViewType.VERTICAL_LIST_VIEW;

        }
//        return super.getItemViewType(position);
    }

    class ChildViewHolder extends RecyclerView.ViewHolder {

        private TextView childName;
        private TextView childAccountNumber;
        private ImageButton childImage;
        private CardView container;

        ChildViewHolder(@NonNull View childView) {
            super(childView);

            childImage = (ImageButton) childView.findViewById(R.id.ib_child_image);
            childName = (TextView) childView.findViewById(R.id.tv_child_name);
            childAccountNumber = (TextView) childView.findViewById(R.id.tv_child_number);
            container = (CardView) childView.findViewById(R.id.cv_child_view_container);
        }
    }


    class NoChildViewHolder extends RecyclerView.ViewHolder {

        private TextView message;

        NoChildViewHolder(@NonNull View childView) {
            super(childView);
            message = (TextView) childView.findViewById(R.id.tv_message);
        }
    }

    interface ViewType {
        int EMPTY_LIST_VIEW = 0;
        int HORIZONTAL_LIST_VIEW = 1;
        int VERTICAL_LIST_VIEW = 2;
    }
}
