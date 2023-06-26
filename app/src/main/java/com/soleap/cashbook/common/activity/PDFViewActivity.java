package com.soleap.cashbook.common.activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.github.barteksc.pdfviewer.PDFView;
import com.soleap.cashbook.R;
import com.soleap.cashbook.common.print.PdfPrintDocumentAdapter;

import java.io.InputStream;

public class PDFViewActivity extends AppCompatActivity {

    public static String URL = "URL";

    private Uri uri = null;

    private Toolbar toolbar;

    private InputStream inputStream = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);
        initToolbar();
        PDFView pdfView = findViewById(R.id.pdfView);
        this.uri = getIntent().getData();
        pdfView.fromUri(uri)
                .enableAnnotationRendering(true)
                .spacing(10) // in dp
                .enableAnnotationRendering(false)
                .enableAntialiasing(true)
                .load();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.pdf_view_option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.menu_print) {
            printPdf();
        }
        return super.onOptionsItemSelected(item);
    }

    private void printPdf() {
        PrintManager printManager = (PrintManager) getSystemService(Context.PRINT_SERVICE);
        String jobName = getString(R.string.app_name) + " Document";
        PrintDocumentAdapter printAdapter = new PdfPrintDocumentAdapter((Context) this, this.uri);
        PrintAttributes printAttributes = new PrintAttributes.Builder()
                .setMediaSize(PrintAttributes.MediaSize.ISO_A4)
                .setColorMode(PrintAttributes.COLOR_MODE_COLOR)
                .build();
        PrintJob printJob = printManager.print(jobName, printAdapter, printAttributes);
        if (printJob.isCompleted()) {
            Toast.makeText(getApplicationContext(), "Print job completed", Toast.LENGTH_SHORT).show();
        } else if (printJob.isFailed()) {
            Toast.makeText(getApplicationContext(), "Print job failed", Toast.LENGTH_SHORT).show();
        }
    }


    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_close);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setSupportActionBar(toolbar);
    }
}
