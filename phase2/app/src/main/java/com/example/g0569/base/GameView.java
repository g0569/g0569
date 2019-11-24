package com.example.g0569.base;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public abstract class GameView extends SurfaceView implements SurfaceHolder.Callback, Runnable  {
    public GameView(Context context) {
        super(context);
    }
}
