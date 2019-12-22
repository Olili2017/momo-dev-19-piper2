package com.piper2.momo.android.digitalbursar.tools;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.piper2.momo.android.digitalbursar.R;
import com.piper2.momo.android.digitalbursar.models.User;
import com.piper2.momo.android.digitalbursar.utils.network.GatewayService;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BottomSheet extends BottomSheetDialogFragment {

    private int workerViewLayout = 0;
    private View template;

    private Button btnCancel, btnContinue;
    private Button btnSubmit;
    private LinearLayout formContinue, workerViewParent, passwordForm, loadingLayout;
    private ImageView loadingSpinner;
    private EditText etPassword;
    private TextView currentStep, currentStepActionTag;
    private String title, tag;

    private OnSubmitListener onSubmitListener;
    private OnCancelListener onCancelListener;
    private OnPasswordConfirmedListener onPasswordConfirmedListener;
    private OnConfirmingPasswordListener onConfirmingPasswordListener;
    private OnConfirmingPasswordFailureListener onConfirmingPasswordFailureListener;

    public int getWorkerViewLayout() {
        return workerViewLayout;
    }

    public void setWorkerViewLayout(int workerViewLayout) {
        this.workerViewLayout = workerViewLayout;
    }

    public View getTemplate() {
        return template;
    }

    public BottomSheet(String title, String  tag){
        this.title = title;
        this.tag = tag;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);

        template = LayoutInflater.from(getContext()).inflate(R.layout.bottom_sheet_template, null);

        TextView titleView = template.findViewById(R.id.title);
        TextView tagView = template.findViewById(R.id.tag);

        titleView.setText(this.title);
        if (this.tag.length() > 0)
            tagView.setText(this.tag);
        else tagView.setVisibility(View.GONE);


//        add needed view to bottom sheet template
        LinearLayout worker_view = template.findViewById(R.id.worker_view);
        worker_view.addView(LayoutInflater.from(getContext()).inflate(getWorkerViewLayout(), null));

//        capture elements before send off to DOM
        btnContinue = (Button) template.findViewById(R.id.btn_continue);
        formContinue = (LinearLayout) template.findViewById(R.id.form_pin_input);
        workerViewParent = (LinearLayout) template.findViewById(R.id.worker_view_parent);
        loadingSpinner = (ImageView) template.findViewById(R.id.pass_loading_spinner);
        btnSubmit = (Button) template.findViewById(R.id.btnSubmit);
        btnCancel = (Button) template.findViewById(R.id.btnCancel);
        passwordForm = (LinearLayout) template.findViewById(R.id.password_form);
        loadingLayout = (LinearLayout) template.findViewById(R.id.loading_linear_layout);
        etPassword = (EditText) template.findViewById(R.id.confirm_password_at_edit);
        currentStep = (TextView) template.findViewById(R.id.tv_actions_precision);
        currentStepActionTag = (TextView) template.findViewById(R.id.tv_whats_happening);

        dialog.setContentView(template);

//        handle template actions
        btnContinue.setOnClickListener(v -> {
            workerViewParent.setVisibility(View.GONE);
            formContinue.setVisibility(View.VISIBLE);
        });

        btnSubmit.setOnClickListener(v -> {
            Glide.with(Objects.requireNonNull(getContext()))
                    .asGif()
                    .load(R.drawable.spin_green)
                    .into(loadingSpinner);

            if(onSubmitListener != null){
                onSubmitListener.onSubmitting();
            }

        });

        btnCancel.setOnClickListener(v -> dismiss());

        setOnSubmitListener(new OnSubmitListener() {
            @Override
            public void onSubmitted(String password) {

            }

            @Override
            public void onSubmitting() {
                passwordForm.setVisibility(View.GONE);
                loadingLayout.setVisibility(View.VISIBLE);

                if (onConfirmingPasswordListener != null) {
                    onConfirmingPasswordListener.onConfirmingPassword(etPassword.getText().toString().trim());
                }
            }
        });

        setOnConfirmingPasswordListener(password -> {
            currentStep.setText("1/2");
            currentStepActionTag.setText("Verifying your pin");
                if(GatewayService.canBeReached(getContext())) {
                    Call<User> getUser = GatewayService.getInstance().getApi().getUser(435545);
                    getUser.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
//                            TODO: handle response from server
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            if (onConfirmingPasswordFailureListener  != null){
                                onConfirmingPasswordFailureListener.onConfirmingPasswordFailed("Service temporarily unavailable. Please try again later");
                            }
                        }
                    });
                }else{
                    if (onConfirmingPasswordFailureListener  != null){
                        onConfirmingPasswordFailureListener.onConfirmingPasswordFailed("No internet! check your connection and try again.");
                    }
                }
        });

        setOnConfirmingPasswordFailureListener(message -> {


            currentStep.setText(message);
            currentStepActionTag.setText("");

            Glide.with(Objects.requireNonNull(getContext()))
                    .load(R.drawable.sad_face)
                    .into(loadingSpinner);
        });


        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) template.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();

        if (behavior instanceof BottomSheetBehavior) {
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
                        case BottomSheetBehavior.STATE_HALF_EXPANDED:
                            state = "HALF_EXPANDED";
                            break;
                    }
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                }
            });
        }

    }

    public void setOnCancelListener(OnCancelListener onCancelListener) {
        this.onCancelListener = onCancelListener;
    }

    public void setOnSubmitListener(OnSubmitListener onSubmitListener) {
        this.onSubmitListener = onSubmitListener;
    }

    public void setOnPasswordConfirmedListener(OnPasswordConfirmedListener onPasswordConfirmedListener) {
        this.onPasswordConfirmedListener = onPasswordConfirmedListener;
    }

    public void setOnConfirmingPasswordListener(OnConfirmingPasswordListener onConfirmingPasswordListener) {
        this.onConfirmingPasswordListener = onConfirmingPasswordListener;
    }

    public void setOnConfirmingPasswordFailureListener(OnConfirmingPasswordFailureListener onConfirmingPasswordFailedListener) {
        this.onConfirmingPasswordFailureListener = onConfirmingPasswordFailedListener;
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
