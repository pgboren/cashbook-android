package com.soleap.cashbook.common.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.soleap.cashbook.document.Media;
import com.soleap.cashbook.restapi.APIClient;
import com.soleap.cashbook.restapi.APIInterface;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedialUtils {

    public static void loadImage(Context context, String path, ImageView imageView) {
        if (path != null) {
            Uri uri = Uri.parse(APIClient.STATIC_URL + path);
            Glide.with(context)
                    .load(uri)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .skipMemoryCache(true)
                    .into(imageView);
        }
    }

    }

