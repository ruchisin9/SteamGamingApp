package com.assignedpro.nj.steam.utills;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.assignedpro.nj.steam.R;


/**
 * Created by Administrator on 09-Jan-17.
 */

public class CustomTextView extends TextView {

    private int font=0;
    public CustomTextView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(final Context context, final AttributeSet attributeSet, final int defStyleAttr) {
        final TypedArray typedArray=context.obtainStyledAttributes(attributeSet, R.styleable.CustomTextView,defStyleAttr,0);
        font=typedArray.getInteger(R.styleable.CustomTextView_textStyle,100);
        if (!isInEditMode()) {
            Typeface tf=null;
            switch (font) {
                case 0:
                    tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/proximanova-black.ttf");
                    break;
                case 1:
                    tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/proximanova-bold.ttf");
                    break;
                case 2:
                    tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/proximanova-boldIt.ttf");
                    break;
                case 3:
                    tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/proximanova-extrabold.ttf");
                    break;
                case 4:
                    tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/proximanova-light.ttf");
                    break;
                case 5:
                    tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/proximanova-lightitalic.ttf");
                    break;
                case 6:
                    tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/proximanova-regular.ttf");
                    break;
                case 7:
                    tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/proximanova-regularitalic.ttf");
                    break;
                case 8:
                    tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/proximanova-semibold.ttf");
                    break;
                case 9:
                    tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/proximanova-semibolditalic.ttf");
                    break;
                default:
                    tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/proximanova-regular.ttf");
                    break;
            }
            setTypeface(tf);
        }
    }

}
