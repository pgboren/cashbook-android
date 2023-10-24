package com.soleap.cashbook.common.widget.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.tntkhang.fullscreenimageview.library.FullScreenImageViewActivity;
import com.google.android.material.imageview.ShapeableImageView;
import com.soleap.cashbook.common.activity.ViewDataActivity;
import com.soleap.cashbook.common.document.Media;
import com.soleap.cashbook.common.document.ViewData;
import com.soleap.cashbook.common.repository.DocumentRepository;
import com.soleap.cashbook.common.repository.RepositoryFactory;
import com.soleap.cashbook.common.util.FileUploader;
import com.soleap.cashbook.common.util.FileUploaderCallback;
import com.soleap.cashbook.restapi.APIClient;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import retrofit2.Response;

public class ViewPhotoFieldCreator extends FieldCreator {

    private static final String TAG = "ViewPhotoFieldCreator";
    private String photoFileName = "new_capture_product_image.jpg";
    private final static int PICK_IMAGE_REQUEST_CODE = 104;
    private Bitmap itemImageData = null;
    private ShapeableImageView imageView;

    public ViewPhotoFieldCreator(ViewDataActivity context, ViewData fieldData) {
        super(context, fieldData);
    }

    @Override
    public View createView() {
        final ViewData data = fieldData;
        return new PhotoView(activity);

//
//        LinearLayout valueContainer = new LinearLayout(activity);
//        valueContainer.setGravity(Gravity.CENTER);
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        params.setMargins(5, 60,0,60);
//        valueContainer.setLayoutParams(params);
//        valueContainer.setOrientation(LinearLayout.HORIZONTAL);
//
//        if (data.isLableVisible()) {
//            TextView textLabel = new TextView(activity);
//            textLabel.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.4f));
//            textLabel.setTextColor(activity.getColor(R.color.secondaryTextColor));
//            textLabel.setText(ResourceUtil.getStringResourceByName(activity, data.getLabel().toLowerCase()));
//            valueContainer.addView(textLabel);
//        }
//
//        int size = (int) activity.getResources().getDimension(R.dimen.view_photo_field_size);
//        imageView = new ShapeableImageView(activity);
//        ShapeAppearanceModel shapeAppearanceModel = imageView.getShapeAppearanceModel().toBuilder().setAllCornerSizes(size/2).build();
//        imageView.setShapeAppearanceModel(shapeAppearanceModel);
//        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
//        if (data.isLableVisible()) {
//            params =new LinearLayout.LayoutParams(size, size);
//        }
//        else {
//            params =new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 600);
//        }
//
//        params.gravity = Gravity.START;
//        imageView.setLayoutParams(params);
//
//        LinearLayout imageContainer = new LinearLayout(activity);
//        params.gravity = Gravity.START;
//        imageContainer.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.6f));
//        imageContainer.addView(imageView);
//        valueContainer.addView(imageContainer);
//
//        if (data.getValue() != null) {
//            MedialUtils.loadImage(activity, data.getValue().toString(), imageView);
//        }
//        else {
//            imageView.setImageDrawable(imageView.getContext().getDrawable(R.drawable.ic_photo_group));
//        }
//
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (data.getValue() != null) {
//                    if (data.getAction().equals(ViewData.PHOTO_VIEW)) {
//                        viewPhoto(data.getValue().toString());
//                    }
//                }
//            }
//        });
//
//        return valueContainer;
    }

    public void pickImage() {

        Intent galleryintent = new Intent(Intent.ACTION_GET_CONTENT, null);
        galleryintent.setType("image/*");

        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        File photoFile = getPhotoFileUri(photoFileName);
        Uri fileProvider = FileProvider.getUriForFile(this.activity, "com.soleap.cashbook", photoFile);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider);

        Intent chooser = new Intent(Intent.ACTION_CHOOSER);
        chooser.putExtra(Intent.EXTRA_INTENT, galleryintent);
        chooser.putExtra(Intent.EXTRA_TITLE, "Select from:");

        Intent[] intentArray = { cameraIntent };
        chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray);
        activity.startActivityForResult(chooser, PICK_IMAGE_REQUEST_CODE);

    }

    public File getPhotoFileUri(String fileName) {
        File mediaStorageDir = new File(activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES), TAG);
        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()){
            Log.d(TAG, "failed to create directory");
        }
        File file = new File(mediaStorageDir.getPath() + File.separator + fileName);
        return file;
    }

    private void viewPhoto(String path) {
        Uri uri = Uri.parse(APIClient.STATIC_URL + path);
        final ArrayList<String> uriString = new ArrayList<>();
        uriString.add(uri.toString());
        Intent fullImageIntent = new Intent(activity, FullScreenImageViewActivity.class);
        fullImageIntent.putExtra(FullScreenImageViewActivity.URI_LIST_DATA, uriString);
        fullImageIntent.putExtra(FullScreenImageViewActivity.IMAGE_FULL_SCREEN_CURRENT_POS, 0);
        activity.startActivity(fullImageIntent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        try {
            if (resultCode == Activity.RESULT_OK) {
                if (requestCode == PICK_IMAGE_REQUEST_CODE) {
                    if (intent != null) {
                        cropImage(intent.getData());
                    }
                    else {
                        cropImage(Uri.fromFile(getPhotoFileUri(photoFileName)));
                    }
                    return;
                }

                try {
                    CropImage.ActivityResult result = CropImage.getActivityResult(intent);
                    if (resultCode == Activity.RESULT_OK) {
                        itemImageData = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), result.getUri());
                        uploadPhoto(itemImageData);
                    } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                        Exception error = result.getError();
                    }
                }
                catch (Exception ex) {
                    Log.e("ProductViewActivity", ex.getMessage(), ex);
                }
            }
            return;
        }
        catch (Exception ex) {
            Log.e(TAG, ex.getMessage(), ex);
        }
        super.onActivityResult(requestCode, resultCode, intent);
    }


    private void cropImage(Uri contentUri) {
        CropImage.activity(contentUri)
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(activity);
    }

    private void uploadPhoto(final Bitmap itemImageData) {
        if (itemImageData != null) {
            activity.showLoadingScreen();
            final Bitmap newDaa = resizeBitmap(itemImageData, 500);
            FileUploader.upload(activity.getApplicationContext(), activity.docId + ".png", newDaa, new FileUploaderCallback() {
                @Override
                public void onSucess(Media media) {
                    Map<String, Object> attributeVaules = new ArrayMap<>();
                    attributeVaules.put("photo", media.getId());
                    RepositoryFactory.create().get(activity.documentName).patch(activity.documentName, activity.docId, attributeVaules, new DocumentRepository.DocumentEventListner() {
                        @Override
                        public void onError(Throwable t) {

                        }

                        @Override
                        public void onResponse(Response response) {

                        }
                    });
                    loadImage(media.getPath(), imageView);
                    activity.hideLoadingScreen();
                }

                @Override
                public void onFailure(Throwable t) {
                    activity.hideLoadingScreen();
                }
            });
        }
    }

    private void loadImage(String path, ImageView imageView) {
        if (path != null) {
            Uri uri = Uri.parse(APIClient.STATIC_URL + path);
            Glide.with(activity)
                    .load(uri)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .skipMemoryCache(true)
                    .into(imageView);
        }
    }

    public Bitmap resizeBitmap(Bitmap bm, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        int newWidth = 0;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleHeight, scaleHeight);
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }

}
