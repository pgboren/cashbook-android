package com.soleap.cashbook.common.util;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.widget.Toast;

import com.soleap.cashbook.R;
import com.soleap.cashbook.common.activity.PDFViewActivity;
import com.soleap.cashbook.common.print.PdfPrintDocumentAdapter;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PDFUtil {
    public static void downloadAndOpenPDF(Context context, String pdfUrl, String fileName) {
        DownloadFileTask task = new DownloadFileTask(context, fileName);
        task.execute(pdfUrl, pdfUrl);
    }

    private static class DownloadFileTask extends AsyncTask<String, Void, File> {
        private Context context;

        private String filename;

        public DownloadFileTask(Context context, String filename) {
            this.context = context;
            this.filename = filename;
        }

        @Override
        protected File doInBackground(String... params) {
            String pdfUrl = params[0];
            try {
                File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                String baseFilename = removeExtension(filename);
                String extension = getFileExtension(filename);
                int count = 1;
                File outputFile;
                do {
                    String suffixedFilename = baseFilename + "_" + count + extension;
                    outputFile = new File(storageDir, suffixedFilename);
                    count++;
                } while (outputFile.exists());

                downloadFile(pdfUrl, outputFile);
                return outputFile;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        private String removeExtension(String filename) {
            int lastDotIndex = filename.lastIndexOf(".");
            if (lastDotIndex != -1) {
                return filename.substring(0, lastDotIndex);
            }
            return filename;
        }

        private String getFileExtension(String filename) {
            int lastDotIndex = filename.lastIndexOf(".");
            if (lastDotIndex != -1) {
                return filename.substring(lastDotIndex);
            }
            return "";
        }

        @Override
        protected void onPostExecute(File file) {
            if (file != null) {
                printPdf(context, file);
            } else {
                Toast.makeText(context, "Error downloading PDF", Toast.LENGTH_SHORT).show();
            }
        }

        private void downloadFile(String fileUrl, File outputFile) throws IOException {
            URL url = new URL(fileUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            if (connection.getResponseCode() == 200) {
                FileOutputStream outputStream = new FileOutputStream(outputFile);
                InputStream inputStream = new BufferedInputStream(connection.getInputStream());
                byte[] buffer = new byte[8 * 1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                outputStream.flush();
                outputStream.close();
                inputStream.close();
            }
            connection.disconnect();
        }

        private void printPdf(Context context, File file) {
            Uri uri = Uri.fromFile(file);
            PrintManager printManager = (PrintManager) context.getSystemService(Context.PRINT_SERVICE);
            String jobName = context.getString(R.string.app_name) + " Document";
            PrintDocumentAdapter printAdapter = new PdfPrintDocumentAdapter(context, uri);
            PrintAttributes printAttributes = new PrintAttributes.Builder()
                    .setMediaSize(PrintAttributes.MediaSize.ISO_A4)
                    .setColorMode(PrintAttributes.COLOR_MODE_COLOR)
                    .build();
            PrintJob printJob = printManager.print(jobName, printAdapter, printAttributes);
            if (printJob.isCompleted()) {
                Toast.makeText(context.getApplicationContext(), "Print job completed", Toast.LENGTH_SHORT).show();
            } else if (printJob.isFailed()) {
                Toast.makeText(context.getApplicationContext(), "Print job failed", Toast.LENGTH_SHORT).show();
            }
        }

        private void openPDF(Context context, File file) {
            Uri uri = Uri.fromFile(file);
            Intent intent = new Intent(context, PDFViewActivity.class);
            intent.setDataAndType(uri, "application/pdf");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            try {
                context.startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(context, "No PDF viewer app found", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
