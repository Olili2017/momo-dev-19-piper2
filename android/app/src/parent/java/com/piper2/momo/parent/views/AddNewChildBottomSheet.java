package com.piper2.momo.parent.views;

import android.app.Dialog;
import android.widget.Button;

import com.piper2.momo.android.digitalbursar.R;
import com.piper2.momo.android.digitalbursar.tools.BottomSheet;

public class AddNewChildBottomSheet extends BottomSheet {
    public AddNewChildBottomSheet(String title, String tag) {
        super(title, tag);
        setWorkerViewLayout(R.layout.worker_create_child);
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);

        Button btnContinue = getTemplate().findViewById(R.id.btn_continue);
        btnContinue.setText("Add child");
    }
}
