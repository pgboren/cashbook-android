package com.soleap.cashbook.common.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.Random;

public class RandomBGColorTextView extends androidx.appcompat.widget.AppCompatTextView {

    public RandomBGColorTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Random rnd = new Random();
        getBackground().setColorFilter(getRandomColor(), PorterDuff.Mode.SRC);
    }

    private int getRandomColor() {
        Random rnd = new Random();
        int red = rnd.nextInt(256);
        int green = rnd.nextInt(256);
        int blue = rnd.nextInt(256);
        return Color.rgb(red, green, blue);
    }


}
