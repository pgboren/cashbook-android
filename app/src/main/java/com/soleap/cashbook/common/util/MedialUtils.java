package com.soleap.cashbook.common.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;
import com.soleap.cashbook.restapi.APIClient;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MedialUtils {


    public static void showImage(Context context) {
//        StfalconImageViewer.Builder<Image>(context, images) { view, image ->
//                Picasso.get().load(image.url).into(view)
//        }.show()
    }

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


//                final URL url = new URL("http://upload.wikimedia.org/wikipedia/commons/e/e8/Svg_example3.svg");
//                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//                InputStream inputStream = urlConnection.getInputStream();
//                SVG svg = SVG.getFromInputStream((inputStream);


}

