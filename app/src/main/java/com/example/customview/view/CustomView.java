package com.example.customview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.customview.R;

/**
 * Its a customView for demp
 * Created by viveksoneja on 05/03/16.
 */
public class CustomView extends View {
    private static final String TAG = CustomView.class.getSimpleName();
    private String stringAttribute;
    private boolean booleanAttribute;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeAttributes(context, attrs);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeAttributes(context, attrs);
    }

    void initializeAttributes(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomView, 0, 0);
        stringAttribute = typedArray.getString(R.styleable.CustomView_attr1);
        booleanAttribute = typedArray.getBoolean(R.styleable.CustomView_attr2, false);

        Log.v(TAG, "StringAttribute:" + stringAttribute + ", BooleanAttribute:" + booleanAttribute);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.v(TAG, "onAttachedToWindow");
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.v(TAG, "onDetachedFromWindow");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.v(TAG, "onMeasure called");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.v(TAG, "onLayout called");
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    public void draw(Canvas canvas) {
        Log.v(TAG, "draw called");
        super.draw(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.v(TAG, "onDraw called");
        super.onDraw(canvas);
    }
}
