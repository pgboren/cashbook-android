package com.soleap.cashbook.common.util;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class BitmapUtils {

    public static Bitmap resizeBitmap(Bitmap bm, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        int newWidth = 0;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleHeight, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }
}
