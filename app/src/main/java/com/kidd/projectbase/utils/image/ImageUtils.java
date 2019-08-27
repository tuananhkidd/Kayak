package com.kidd.projectbase.utils.image;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import androidx.core.content.FileProvider;
import android.view.View;
import android.widget.ImageView;

import com.kidd.projectbase.utils.Define;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class ImageUtils {
    public static ArrayList<String> getAllImagesPath(Activity activity) {
        Uri uri;
        Cursor cursor;
        int column_index_data, column_index_folder_name;
        ArrayList<String> listOfAllImages = new ArrayList<String>();
        String absolutePathOfImage = null;
        uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {MediaStore.MediaColumns.DATA,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

        cursor = activity.getContentResolver().query(uri, projection, null,
                null, null);

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        column_index_folder_name = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data);

            listOfAllImages.add(absolutePathOfImage);
        }
        return listOfAllImages;
    }

    public static File createTempFile(Context context) {
        return new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                System.currentTimeMillis() + "_image.jpeg");
    }

    private String getImagePathFromGallery(Intent data, Context context) {
        Uri selectedImage = data.getData();
        if (selectedImage != null) {
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = context.getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();
                return picturePath;
            }
        }

        return null;
    }


    public static void showImageFromDrawable(String uri, ImageView imageView) {

        Context context = imageView.getContext();
        int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());

        if (imageResource != 0) {
            Drawable res = context.getResources().getDrawable(imageResource);
            imageView.setImageDrawable(res);

        } else {
            imageView.setImageResource(0);
        }

    }

    public static Bitmap overlay(Bitmap bmp1, Bitmap bmp2) {
        Bitmap bmOverlay = Bitmap.createBitmap(bmp1.getWidth(), bmp1.getHeight(), bmp1.getConfig());
        Canvas canvas = new Canvas(bmOverlay);
        canvas.drawBitmap(bmp1, new Matrix(), null);
        canvas.drawBitmap(bmp2, new Matrix(), null);
        return bmOverlay;
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    public static Bitmap cutBitmapTofitView(Bitmap bitmap, View view) {
        int widthBitmap = bitmap.getWidth();
        int heightBitmap = bitmap.getHeight();

        int widthView = view.getWidth();
        int heightView = view.getHeight();

        float ratioBitmap = (float) widthBitmap / heightBitmap;
        float ratioView = (float) widthView / heightView;
        if (ratioBitmap == ratioView) {
            return Bitmap.createScaledBitmap(bitmap, widthView, heightView, false);
        }

        if (ratioView > ratioBitmap) {
            //cut top and bottom
            int heightScaleFit = widthView * heightBitmap / widthBitmap;
            bitmap = Bitmap.createScaledBitmap(bitmap, widthView, heightScaleFit, false);
            bitmap = Bitmap.createBitmap(bitmap
                    , 0
                    , (heightScaleFit - heightView) / 2
                    , widthView
                    , heightView
            );
        } else {
            //cut left and right
            int widthScaleFit = heightView * widthBitmap / heightBitmap;
            bitmap = Bitmap.createScaledBitmap(bitmap, widthScaleFit, heightView, false);
            bitmap = Bitmap.createBitmap(bitmap
                    , (widthScaleFit - widthView) / 2
                    , 0
                    , widthView
                    , heightView
            );
        }
        return bitmap;
    }

    public static void shareImage(Context context, Bitmap bitmap, String content) throws FileNotFoundException {
        File cachePath = new File(context.getCacheDir(), Define.APP_FOLDER_CAPTURED);
        cachePath.mkdirs();
        FileOutputStream fileOutputStream = new FileOutputStream(cachePath + "/tetviet.png");
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);

        File imagePath = new File(context.getCacheDir(), Define.APP_FOLDER_CAPTURED);
        File newFile = new File(imagePath, "tetviet.png");
        Uri uri = FileProvider.getUriForFile(context, Define.APP_FOLDER_CAPTURED_PROVIDER, newFile);
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.setType("image/jpeg");
        sendIntent.putExtra(Intent.EXTRA_STREAM, uri);
        sendIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        context.startActivity(Intent.createChooser(sendIntent, content));
    }

    public static void addImageToGallery(ContentResolver cr, File filepath) {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "player");
        values.put(MediaStore.Images.Media.DISPLAY_NAME, "player");
        values.put(MediaStore.Images.Media.DESCRIPTION, "");
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/" + "jpeg");
        values.put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis());
        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
        values.put(MediaStore.Images.Media.DATA, filepath.toString());

        cr.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
    }
}
