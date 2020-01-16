package com.piper2.momo.parent.views;

import android.app.Dialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.piper2.momo.android.digitalbursar.R;
import com.piper2.momo.android.digitalbursar.tools.BottomSheet;

import java.util.Objects;

public class SendMoneyToNewChildBottomSheet extends BottomSheet {
    public SendMoneyToNewChildBottomSheet(String title, String tag) {
        super(title, tag);
        setWorkerViewLayout(R.layout.worker_send_to_new);
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        TextView newChildNameTag = getTemplate().findViewById(R.id.tag_new_child_name);
        EditText newChildName = getTemplate().findViewById(R.id.et_new_child_name);

        newChildName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String tag;

                tag = count > 0 ? s.toString() : String.valueOf(Objects.requireNonNull(getContext()).getResources().getText(R.string.new_child));

                newChildNameTag.setText(tag);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
