package com.example.customview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
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
public class CustomImageView extends View{
    private Paint paint;
    private Bitmap bitmap;

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
        bitmap = ((BitmapDrawable)drawable).getBitmap();
        bitmap = bitmap.createScaledBitmap(bitmap, 1000, 1000 * bitmap.getHeight() / bitmap.getWidth(), false);

        paint = new Paint();
//        paint.setColor(Color.BLUE);
//        paint.setStrokeWidth(30);
        paint.setStyle(Paint.Style.FILL);

        LinearGradient linearGradient = new LinearGradient(0, 0, 800, 800, new int[] {Color.BLUE, Color.WHITE}, new float[] {0.2f, 0.8f}, Shader.TileMode
                .CLAMP);
        Matrix matrix = new Matrix();
        matrix.setRotate(45);
        linearGradient.setLocalMatrix(matrix);
        paint.setShader(linearGradient);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(500, 500, 400, paint);
//        canvas.drawBitmap(bitmap, 200, 200, paint);
    }
}
