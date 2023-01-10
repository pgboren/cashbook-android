package com.soleap.cashbook.common.util;

import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class FirebaseFileStorageUtil {

    private static com.soleap.cashbook.common.util.FirebaseFileStorageUtil instance;

    public static com.soleap.cashbook.common.util.FirebaseFileStorageUtil getInstance() {
        if (instance == null) {
            instance = new com.soleap.cashbook.common.util.FirebaseFileStorageUtil();
        }
        return instance;
    }

    public void uploadBitmap(String path, Bitmap bitmap, final OnUploadPassedHandler onPassedCallBack, final OnUploadFailHandler onFialeCallBack) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] data = baos.toByteArray();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        final StorageReference imagesRef = storageRef.child(path);
        UploadTask uploadTask = imagesRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                onFialeCallBack.onFailure(exception);
                Log.e("ERROR", exception.getMessage(), exception);
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                onPassedCallBack.onPassed(imagesRef.getDownloadUrl());
                Log.i("File Upload", imagesRef.getDownloadUrl().toString());
            }
        });
    }

    public interface OnUploadPassedHandler {
        void onPassed(Task<Uri> downloadUrl);
    }

    public interface OnUploadFailHandler {
        void onFailure(@NonNull Exception var1);
    }

}
