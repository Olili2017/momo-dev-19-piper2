package com.piper2.momo.android.digitalbursar.utils.numbers;

import java.text.DecimalFormat;

public class ConvertToCurrency {

    public String number(float amount){
        DecimalFormat df = new DecimalFormat();
        return "UGX: ".concat(df.format(amount));
    }

}

