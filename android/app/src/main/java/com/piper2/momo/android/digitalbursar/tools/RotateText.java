package com.piper2.momo.android.digitalbursar.tools;

import android.content.Context;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.TextView;

import com.piper2.momo.android.digitalbursar.R;

public class RotateText {
    public static void build(TextView textView, Context mContext){
        TextView t = textView;
        RotateAnimation ranim = (RotateAnimation) AnimationUtils.loadAnimation(mContext, R.anim.rotate_letters);
        ranim.setFillAfter(true); //For the textview to remain at the same place after the rotation
        t.setAnimation(ranim);
        t.setBottom(-35);
    }
}
