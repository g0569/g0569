package com.example.g0569.view;

import android.content.Context;

public class CustomizationView extends BaseView {
    public CustomizationView(Context context) {
        super(context);
        paint.setTextSize(40);
        thread = new Thread(this);
    }
}
