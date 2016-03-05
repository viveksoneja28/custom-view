package com.example.customview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.example.customview.R;

/**
 * Custom Image View for demo
 * Created by viveksoneja on 05/03/16.
 */
public class CustomImageView extends View {
    private Bitmap bitmap;
    private int reflectionHeight;
    private Bitmap reflection;

    public CustomImageView(Context context) {
        super(context);
        init(context, null);
    }

    public CustomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CustomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomImageView, 0, 0);
        Drawable drawable = typedArray.getDrawable(R.styleable.CustomImageView_src);
        bitmap = ((BitmapDrawable) drawable).getBitmap();
        bitmap = bitmap.createScaledBitmap(bitmap, 1000, 1000 * bitmap.getHeight() / bitmap.getWidth(), false);

        reflectionHeight = 400;
        reflection = getReflection(bitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, 0, 0, null);
        canvas.drawBitmap(reflection, 0, bitmap.getHeight(), null);

    }

    public Bitmap getReflection(Bitmap originalBitmap) {
        Bitmap bitmap = Bitmap.createBitmap(originalBitmap.getWidth(), reflectionHeight, Bitmap.Config.ARGB_8888);

        LinearGradient linearGradient = new LinearGradient(0, 0, 0, reflectionHeight, new int[]{Color.parseColor("#80FFFFFF"), Color.TRANSPARENT}, new
                float[]{0.1f, 0.9f}, Shader.TileMode.CLAMP);

        BitmapShader bitmapShader = new BitmapShader(originalBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Matrix inverseMatrix = new Matrix();
        inverseMatrix.setScale(1.0f, -1.0f);
        inverseMatrix.preTranslate(0, -1 * originalBitmap.getHeight());
        bitmapShader.setLocalMatrix(inverseMatrix);


        ComposeShader composeShader = new ComposeShader(bitmapShader, linearGradient, PorterDuff.Mode.DST_IN);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setShader(composeShader);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawRect(0, 0, originalBitmap.getWidth(), reflectionHeight, paint);


        //Create Blur effect
        bitmap = Bitmap.createScaledBitmap(Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / 10, bitmap.getHeight() / 10, true), originalBitmap.getWidth(),
                reflectionHeight, true);
        return bitmap;
    }
}
