package com.soleap.cashbook.common.util;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.soleap.cashbook.restapi.APIClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtils {

    public static File getDocumentsDirectory(Context context) {
        return new File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "output.pdf");
    }

    public static void writeInputStreamToFile(InputStream inputStream, File file) throws IOException {
        FileOutputStream outputStream = null;
        try {
            byte[] buffer = new byte[8 * 1024]; // Buffer size (you can adjust it as per your needs)
            int bytesRead;
            outputStream = new FileOutputStream(file);
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    // Handle exception if needed
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    // Handle exception if needed
                }
            }
        }
    }

}

