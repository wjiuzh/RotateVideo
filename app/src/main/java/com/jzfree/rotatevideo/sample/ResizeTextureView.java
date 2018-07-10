package com.jzfree.rotatevideo.sample;

import android.content.Context;
import android.util.AttributeSet;
import android.view.TextureView;

/**
 * Created by Wang Jiuzhou on 2018/7/9 17:13
 */
public class ResizeTextureView extends TextureView {
    private int mVideoWidth = 100;
    private int mVideoHeight = 100;

    public ResizeTextureView(Context context) {
        super(context);
    }

    public ResizeTextureView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ResizeTextureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void resize(int videoWidth, int videoHeight) {
        mVideoWidth = videoWidth;
        mVideoHeight = videoHeight;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        setMeasuredDimension(getSize(mVideoWidth, width, widthMeasureSpec), getSize(mVideoHeight, height, heightMeasureSpec));
    }

    public int getSize(int size, int defaultSize, int measureSpec) {
        int result = size;
        int specMode = MeasureSpec.getMode(measureSpec);

        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                result = defaultSize;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
                result = size;
                break;
        }
        return result;
    }
}
