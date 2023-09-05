package com.soleap.cashbook.common.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.soleap.cashbook.common.document.DocumentSnapshot;
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

public class FileUploader {

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

    public static void upload(Context context, String fileName, Bitmap mBitmap, final FileUploaderCallback callback) {
        try {
            File filesDir = context.getFilesDir();
            File file = new File(filesDir, fileName);
            OutputStream os;
            try {
                os = new FileOutputStream(file);
                mBitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
                os.flush();
                os.close();
            } catch (Exception e) {
                Log.e("FileUploadUtil", "Error writing bitmap", e);
            }

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            mBitmap.compress(Bitmap.CompressFormat.PNG, 0, bos);
            byte[] bitmapdata = bos.toByteArray();

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();

            RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), reqFile);
            RequestBody name = RequestBody.create(MediaType.parse("text/plain"), "file");

            APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
            Call<DocumentSnapshot.Media> req = apiInterface.mediaUpload(body, name);
            req.enqueue(new Callback<DocumentSnapshot.Media>() {
                @Override
                public void onResponse(Call<DocumentSnapshot.Media> call, Response<DocumentSnapshot.Media> response) {
                    if (response.code() == 201) {
                        if (callback != null) {
                            callback.onSucess(response.body());
                        }
                    }
                }

                @Override
                public void onFailure(Call<DocumentSnapshot.Media> call, Throwable t) {
                    if (callback != null) {
                        callback.onFailure(t);
                    }
                    t.printStackTrace();
                }
            });


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

