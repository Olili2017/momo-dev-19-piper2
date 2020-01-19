package com.piper2.momo.student.views;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.piper2.momo.android.digitalbursar.R;

public class ConfirmPinViewResolver extends BottomSheetDialogFragment {

    private ConfirmPinViewResolver.OnSubmitListener onSubmitListener;
    private ConfirmPinViewResolver.OnCancelListener onCancelListener;
    private ConfirmPinViewResolver.OnPasswordConfirmedListener onPasswordConfirmedListener;
    private ConfirmPinViewResolver.OnConfirmingPasswordListener onConfirmingPasswordListener;
    private ConfirmPinViewResolver.OnConfirmingPasswordFailureListener onConfirmingPasswordFailureListener;

    private EditText et_password;
    private Button btnCancel, btnContinue;
    private Button btnSubmit;
    private LinearLayout loading_linear_layout, password_form, form_pin_input, form_money_input;
    private ImageView pass_loading_spinner;
    private TextView tv_actions_precision, tv_whats_happening;

    private String title, tag;
    private int layout;

    public ConfirmPinViewResolver(String title, String tag, int layout) {
        this.title = title;
        this.tag = tag;
        this.layout = layout;
    }

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

    public ConfirmPinViewResolver.OnConfirmingPasswordListener getOnConfirmingPasswordListener() {
        return onConfirmingPasswordListener;
    }

    public ConfirmPinViewResolver.OnConfirmingPasswordFailureListener getOnConfirmingPasswordFailureListener() {
        return onConfirmingPasswordFailureListener;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
//        setView(null);
        super.setupDialog(dialog, style);

        dialog.setContentView(this.build(getContext()));
    }

    public View build(Context context){

        //Set the custom view
        View view = LayoutInflater.from(context).inflate(this.layout, null);

        TextView titleView = view.findViewById(R.id.title);
        TextView tagView = view.findViewById(R.id.tag);

        tagView.setText(this.tag);
        titleView.setText(this.title);

        et_password = (EditText) view.findViewById(R.id.confirm_password_at_edit);
        btnSubmit = (Button) view.findViewById(R.id.btnSubmit);
        btnCancel = (Button) view.findViewById(R.id.btnCancel);
        btnContinue = (Button) view.findViewById(R.id.btn_continue);
        password_form = (LinearLayout) view.findViewById(R.id.password_form);
        form_pin_input = (LinearLayout) view.findViewById(R.id.form_pin_input);
        form_money_input = (LinearLayout) view.findViewById(R.id.form_money_input);
        loading_linear_layout = (LinearLayout) view.findViewById(R.id.loading_linear_layout);
        pass_loading_spinner = (ImageView) view.findViewById(R.id.pass_loading_spinner);
        tv_whats_happening = (TextView) view.findViewById(R.id.tv_whats_happening);
        tv_actions_precision = (TextView) view.findViewById(R.id.tv_actions_precision);

        btnContinue.setOnClickListener(v -> {
            form_money_input.setVisibility(View.GONE);
            form_pin_input.setVisibility(View.VISIBLE);
        });

        btnSubmit.setOnClickListener(v -> {
            Glide.with(context)
                    .asGif()
                    .load(R.drawable.spin_green)
                    .into(pass_loading_spinner);

            if(onSubmitListener != null){
                onSubmitListener.onSubmitting();
            }

        });

        btnCancel.setOnClickListener(v -> dismiss());
        
        return view;

    }


    public ConfirmPinViewResolver setOnCancelListener(OnCancelListener onCancelListener) {
        this.onCancelListener = onCancelListener;
        return this;
    }

    public ConfirmPinViewResolver setOnSubmitListener(OnSubmitListener onSubmitListener) {
        this.onSubmitListener = onSubmitListener;
        return this;
    }

    public ConfirmPinViewResolver setOnPasswordConfirmedListener(OnPasswordConfirmedListener onPasswordConfirmedListener) {
        this.onPasswordConfirmedListener = onPasswordConfirmedListener;
        return this;
    }

    public ConfirmPinViewResolver setOnConfirmingPasswordListener(OnConfirmingPasswordListener onConfirmingPasswordListener) {
        this.onConfirmingPasswordListener = onConfirmingPasswordListener;
        return this;
    }

    public ConfirmPinViewResolver setOnConfirmingPasswordFailureListener(OnConfirmingPasswordFailureListener onConfirmingPasswordFailedListener) {
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
