package com.example.viewmodel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.res.ResourcesCompat;

import com.example.viewmodel.R;

public class MyEditText extends AppCompatEditText {

    Drawable mClearButtonImage;

    public MyEditText(Context context) {
        super(context);
        initComp();
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initComp();
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initComp();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setHint("Masukkan nama anda");
        setTextAlignment(TEXT_ALIGNMENT_VIEW_START);
    }

    private void initComp(){
        mClearButtonImage = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_close_black_24dp, null);

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if ((getCompoundDrawablesRelative()[2] != null)) {
                    float clearButtonStart;
                    float clearButtonEnd;
                    boolean isClearButtonClicked = false;

                    if (getLayoutDirection() == LAYOUT_DIRECTION_RTL) {
                        clearButtonEnd = mClearButtonImage.getIntrinsicWidth() + getPaddingStart();

                        if (motionEvent.getX() < clearButtonEnd) {
                            isClearButtonClicked = true;
                        }
                    } else {
                        clearButtonStart = (getWidth() - getPaddingEnd() - mClearButtonImage.getIntrinsicWidth());

                        if (motionEvent.getX() > clearButtonStart) {
                            isClearButtonClicked = true;
                        }
                    }

                    if (isClearButtonClicked) {

                        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                            mClearButtonImage = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_close_black_24dp, null);
                            showClearButton();
                            return true;
                        } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                            mClearButtonImage = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_close_black_24dp, null);

                            if (getText() != null){
                                getText().clear();
                            }
                            hideClearButton();
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                }
                return false;
            }
        });

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().isEmpty()) {
                    showClearButton();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void showClearButton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, mClearButtonImage, null);
    }

    private void hideClearButton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null);
    }
}
