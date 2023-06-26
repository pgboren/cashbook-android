package com.soleap.cashbook.common.value.pdf;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.soleap.cashbook.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class InvoicePDFGenerator {

    public static void generate(Context context, File file, String content) {
        Document document = new Document();
        document.setPageSize(PageSize.A4);
        try {

        FontFactory.register("res/font/khmerosmoul.ttf");
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            Drawable drawable = context.getResources().getDrawable(R.drawable.logo);
            Image image = Image.getInstance(drawableToBytes(drawable));

            InputStream stream = context.getAssets().open("khmeros_sys.ttf");

//            OutputStream arquivo = new FileOutputStream("file:///android_asset/khmeros_sys.ttf");

            Font font = FontFactory.getFont("khmerosmoul");
//            BaseFont baseFont = BaseFont.createFont("res/font/khmerosmoul.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
//            Font font = new Font(baseFont, 12, Font.NORMAL, BaseColor.BLACK);
            Paragraph paragraph = new Paragraph(content, font);
            document.add(paragraph);
            document.close() ;
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    private static byte[] drawableToBytes(Drawable drawable) throws IOException {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

}
