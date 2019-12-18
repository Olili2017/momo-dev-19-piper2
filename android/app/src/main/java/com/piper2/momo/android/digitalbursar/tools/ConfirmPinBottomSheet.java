package com.piper2.momo.android.digitalbursar.tools;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.piper2.momo.android.digitalbursar.R;

public class ConfirmPinBottomSheet extends BottomSheetDialogFragment {

    private OnSubmitListener onSubmitListener;
    private OnCancelListener onCancelListener;
    private OnPasswordConfirmedListener onPasswordConfirmedListener;
    private OnConfirmingPasswordListener onConfirmingPasswordListener;
    private OnConfirmingPasswordFailureListener onConfirmingPasswordFailureListener;

    private EditText et_password;
    private Button btnCancel;
    private Button btnSubmit;
    private LinearLayout loading_linear_layout, password_form;
    private ImageView pass_loading_spinner;
    private TextView tv_actions_precision, tv_whats_happening;

    public String getInputPassword(){
        return et_password.getText().toString().trim();
    }

    public LinearLayout getLoading_linear_layout() {
        return loading_linear_layout;
    }

    public LinearLayout getPassword_form() {
        return password_form;
    }

    public TextView getTv_actions_precision() {
        return tv_actions_precision;
    }

    public TextView getTv_whats_happening() {
        return tv_whats_happening;
    }

    public ImageView getPass_loading_spinner() {
        return pass_loading_spinner;
    }

    public OnConfirmingPasswordListener getOnConfirmingPasswordListener() {
        return onConfirmingPasswordListener;
    }

    public OnConfirmingPasswordFailureListener getOnConfirmingPasswordFailureListener() {
        return onConfirmingPasswordFailureListener;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);

        //Set the custom view
        View view = LayoutInflater.from(getContext()).inflate(R.layout.user_cofirm_dialog, null);

        et_password = (EditText) view.findViewById(R.id.confirm_password_at_edit);
        btnSubmit = (Button) view.findViewById(R.id.btnSubmit);
        btnCancel = (Button) view.findViewById(R.id.btnCancel);
        password_form = (LinearLayout) view.findViewById(R.id.password_form);
        loading_linear_layout = (LinearLayout) view.findViewById(R.id.loading_linear_layout);
        pass_loading_spinner = (ImageView) view.findViewById(R.id.pass_loading_spinner);
        tv_whats_happening = (TextView) view.findViewById(R.id.tv_whats_happening);
        tv_actions_precision = (TextView) view.findViewById(R.id.tv_actions_precision);
        dialog.setContentView(view);

        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) view.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();

        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    String state = "";

                    switch (newState) {
                        case BottomSheetBehavior.STATE_DRAGGING: {
                            state = "DRAGGING";
                            break;
                        }
                        case BottomSheetBehavior.STATE_SETTLING: {
                            state = "SETTLING";
                            break;
                        }
                        case BottomSheetBehavior.STATE_EXPANDED: {
                            state = "EXPANDED";
                            break;
                        }
                        case BottomSheetBehavior.STATE_COLLAPSED: {
                            state = "COLLAPSED";
                            break;
                        }
                        case BottomSheetBehavior.STATE_HIDDEN: {
                            dismiss();
                            state = "HIDDEN";
                            break;
                        }
                    }
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                }
            });
        }

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(getContext())
                        .asGif()
                        .load(R.drawable.spin_green)
                        .into(pass_loading_spinner);

                if(onSubmitListener != null){
                    onSubmitListener.onSubmitting();
                }

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public ConfirmPinBottomSheet setOnCancelListener(OnCancelListener onCancelListener) {
        this.onCancelListener = onCancelListener;
        return this;
    }

    public ConfirmPinBottomSheet setOnSubmitListener(OnSubmitListener onSubmitListener) {
        this.onSubmitListener = onSubmitListener;
        return this;
    }

    public ConfirmPinBottomSheet setOnPasswordConfirmedListener(OnPasswordConfirmedListener onPasswordConfirmedListener) {
        this.onPasswordConfirmedListener = onPasswordConfirmedListener;
        return this;
    }

    public ConfirmPinBottomSheet setOnConfirmingPasswordListener(OnConfirmingPasswordListener onConfirmingPasswordListener) {
        this.onConfirmingPasswordListener = onConfirmingPasswordListener;
        return this;
    }

    public ConfirmPinBottomSheet setOnConfirmingPasswordFailureListener(OnConfirmingPasswordFailureListener onConfirmingPasswordFailedListener) {
        this.onConfirmingPasswordFailureListener = onConfirmingPasswordFailedListener;
        return this;
    }

    public interface OnSubmitListener {
        void onSubmitted(String password);
        void onSubmitting();
    }


    public interface OnCancelListener {
        void onCanceled();
    }

    public interface OnPasswordConfirmedListener {
        void onPasswordConfirmed();
    }

    public interface OnConfirmingPasswordListener {
        void onConfirmingPassword(String password);
    }

    public interface OnConfirmingPasswordFailureListener {
        void onConfirmingPasswordFailed(String message);
    }

}
