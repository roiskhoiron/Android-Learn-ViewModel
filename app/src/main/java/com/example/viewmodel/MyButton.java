package com.example.viewmodel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import static android.view.Gravity.CENTER;

public class MyButton extends AppCompatButton {

    private Drawable enableBackground, disableBackground;
    private int textColor;

    public MyButton(Context context) {
        super(context);
        initComp();
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initComp();
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initComp();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackground(isEnabled() ? enableBackground : disableBackground);
        setTextColor(textColor);
        setTextSize(12.f);
        setGravity(CENTER);
        setText(isEnabled() ? "Submit" : "isi Dulu");
    }

    private void initComp() {
        textColor = ContextCompat.getColor(getContext(), android.R.color.background_light);
        enableBackground = getResources().getDrawable(R.drawable.bg_button);
        disableBackground = getResources().getDrawable(R.drawable.bg_button_disable);
    }
}
