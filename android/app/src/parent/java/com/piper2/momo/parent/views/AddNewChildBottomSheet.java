package com.piper2.momo.parent.views;

import android.app.Dialog;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.piper2.momo.android.digitalbursar.R;
import com.piper2.momo.android.digitalbursar.models.Child;
import com.piper2.momo.android.digitalbursar.tools.BottomSheet;
import com.piper2.momo.parent.actions.AddChild;
import com.piper2.momo.parent.constants.Hard;

public class AddNewChildBottomSheet extends BottomSheet {

    private boolean shouldUpdateOnDismisDialog = false;

    public AddNewChildBottomSheet(String title, String tag) {
        super(title, tag);
        setWorkerViewLayout(R.layout.worker_create_child);
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);

        setNextStepActionTag("Your child account is being created");
        Button btnContinue = getTemplate().findViewById(R.id.btn_continue);
        btnContinue.setText("Add child");
    }

    @Override
    public void dismiss() {
        if (onDismissingBottomSheet != null){
            onDismissingBottomSheet.onDismissingSheet(shouldUpdateOnDismisDialog);
        }
        super.dismiss();
    }

    @Override
    public void doFinalThing() {
        super.doFinalThing();

        EditText childNameTv = getTemplate().findViewById(R.id.et_new_child_name);
        Child child = new Child();
        child.setName(childNameTv.getText().toString());
        child.setParent(Hard.PARENT_PHONE);
        child.setPin(Hard.DEFAULT_PIN);

            new AddChild()
                    .setChild(child)
                    .setOnChildAddedListener(() -> {
                        if (onAllActionsCompletedListener != null){
                            shouldUpdateOnDismisDialog = true;
                            onAllActionsCompletedListener.onAllActionsCompleted();
                        }
                    })
                    .setOnAddChildFiledListener(() -> {

                    })
                    .execute();
    }

}
