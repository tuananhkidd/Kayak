package com.kidd.projectbase.utils;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Movie;
import android.graphics.Paint;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;


import com.kidd.projectbase.App;
import com.kidd.projectbase.entity.Photo;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.UUID;

import static com.kidd.projectbase.utils.Define.MAX_HEIGHT_IMAGE_UPLOAD;
import static com.kidd.projectbase.utils.Define.QUALITY_BITMAP_80;

/**
 * Created by WelbyDev.
 */

public class FileUtil<T> {
    private static FileUtil fileUtil;

    public static FileUtil getInstance() {
        if (fileUtil == null) {
            fileUtil = new FileUtil();
        }
        return fileUtil;
    }

    /**
     * decrease photo quality ~80% and save to app folder
     *
     * @param photoPath path of input photo
     * @param fileName  compressed photo filename
     * @return compressed photo path
     */
    public static String savePhoto(String photoPath, String fileName) {
        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath();
        File dir = new File(root, Define.APP_FOLDER_CAPTURED);
        dir.mkdirs();
        File file = new File(dir, fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            BitmapFactory.decodeFile(photoPath)
                    .compress(Bitmap.CompressFormat.JPEG, QUALITY_BITMAP_80, out);
            out.flush();
            out.close();
            return file.getAbsolutePath();
        } catch (Exception e) {
            Log.e(FileUtil.class.getSimpleName(), "savePhoto: ", e);
            return null;
        }
    }

    /**
     * decrease photo quality ~80% and save to app folder
     *
     * @param bitmap   input photo
     * @param fileName compressed photo filename
     * @return compressed photo path
     */
    public static String savePhoto(Bitmap bitmap, String fileName) {
        String root = Environment.getExternalStorageDirectory().getAbsolutePath();
        File dir = new File(root, Define.APP_FOLDER_CAPTURED);
        dir.mkdirs();
        File file = new File(dir, fileName + Define.IMAGE_JPG);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, Define.QUALITY_BITMAP_100, out);
            out.flush();
            out.close();
            return file.getAbsolutePath();
        } catch (Exception e) {
            Log.e(FileUtil.class.getSimpleName(), "", e);
            return null;
        }
    }

    public static String saveBitmapToFile(Bitmap bitmap, File file) {
//        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath();
//        File dir = new File(root, Define.APP_FOLDER_CAPTURED);
//        dir.mkdirs();
//        File file = new File(dir, fileName + Define.IMAGE_JPG);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, QUALITY_BITMAP_80, out);
            out.flush();
            out.close();
            return file.getAbsolutePath();
        } catch (Exception e) {
            Log.e(FileUtil.class.getSimpleName(), "", e);
            return null;
        }
    }

    public static void deleteFile(String fileName) {
        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath();
        File dir = new File(root, Define.APP_FOLDER_CAPTURED);
        dir.mkdirs();
        File file = new File(dir, fileName + Define.IMAGE_JPG);
        if (file.exists()) {
            file.delete();
        }
    }

    public static void removeTempFile() {
        String root = Environment.getExternalStorageDirectory().toString();
        File dir = new File(root, Define.APP_FOLDER_CAPTURED);
        removeRecursive(dir);
    }

    public static void removeRecursive(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory() && null != fileOrDirectory.listFiles()) {
            for (File child : fileOrDirectory.listFiles()) {
                removeRecursive(child);
            }
        }
        if (fileOrDirectory.exists()) {
            fileOrDirectory.delete();
        }
    }

    /**
     * create and return temp photo file
     *
     * @return photo file path
     */
    public static Photo getCapturedPhotoFile() {
        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath();
        File dir = new File(root, Define.APP_FOLDER_CAPTURED);
        dir.mkdirs();
        long createdTime = System.currentTimeMillis();
        File file = new File(dir, createdTime + Define.IMAGE_JPG);
        if (file.exists()) {
            file.delete();
        }
        return new Photo(file.getAbsolutePath(), createdTime);
    }

    /**
     * create and return app photo file
     *
     * @return photo file path
     */
    public static Photo getPhotoFile() {
        String root = Environment.getExternalStorageDirectory().toString();
        File dir = new File(root, Define.APP_FOLDER_NAME);
        dir.mkdirs();
        long createdTime = System.currentTimeMillis();
        File file = new File(dir, createdTime + Define.IMAGE_JPG);
        if (file.exists()) {
            file.delete();
        }
        return new Photo(file.getAbsolutePath(), createdTime);
    }

    public static String getPathFromImageUri(Context context, Uri uri) {
        String result = null;
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    public static ArrayList<Photo> getCameraPhotos(Context context) {
        ArrayList<Photo> result = new ArrayList<>();
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Images.ImageColumns.DATA,
                MediaStore.Images.ImageColumns.DATE_TAKEN};
        String orderBy = MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC";
        Cursor c = context.getContentResolver().query(uri, projection, null, null, orderBy);
        if (c != null) {
            while (c.moveToNext()) {
                String imagePath = c.getString(0);
                long takenTime = c.getLong(1);
                if (imagePath == null) {
                    continue;
                }
                if (new File(imagePath).exists()) {
                    result.add(new Photo(imagePath, takenTime));
                }
            }
            c.close();
        }
        return result;
    }

    /*
     * Max file size is 1MB
     * Width x Height x Bit Depth ÷ 8 ÷ 1024 ÷ 1024 = File Size in Megabytes (MB)
     * Use 16 as default Bit Depth
     * ===> Width x Height <= 1 / 1024 * 1024 / 2
     *
     */

    public static Bitmap resizeBitmapToLessThanOneMb(Bitmap src) {
        float sourceW = src.getWidth();
        float sourceH = src.getHeight();

        float srcRatio;
        double newW;
        double newH;

        if (sourceW > sourceH) {
            srcRatio = sourceW / sourceH;
            newH = MAX_HEIGHT_IMAGE_UPLOAD;
            newW = newH * srcRatio;
        } else {
            srcRatio = sourceH / sourceW;
            newW = MAX_HEIGHT_IMAGE_UPLOAD;
            newH = newW * srcRatio;
        }

        if (sourceH * sourceW <= (MAX_HEIGHT_IMAGE_UPLOAD
                * MAX_HEIGHT_IMAGE_UPLOAD * srcRatio)) {
            return src;
        }

        return getResizedBitmap(src, (int) newW, (int) newH);
    }

    public static Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {

        return Bitmap.createScaledBitmap(bm, newWidth, newHeight, false);
    }

    public static Bitmap getBitmapFromFile(String path) {
        File file = new File(path);
        Uri uri = Uri.fromFile(file);
        Bitmap bitmap = null;

        try {
            bitmap = MediaStore.Images.Media.getBitmap(App.getContext().getContentResolver(), uri);
            ExifInterface exif = new ExifInterface(path);
            int rotation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            int rotationInDegrees = exifToDegrees(rotation);
            if (rotationInDegrees % Define.ROTATION_180 == 0) {
                if (bitmap.getWidth() > MAX_HEIGHT_IMAGE_UPLOAD) {
                    bitmap = Bitmap.createScaledBitmap(bitmap, Define.MAX_WIDTH_IMAGE_UPLOAD, MAX_HEIGHT_IMAGE_UPLOAD, false);
                }
            } else {
                if (bitmap.getWidth() > MAX_HEIGHT_IMAGE_UPLOAD) {
                    bitmap = Bitmap.createScaledBitmap(bitmap, MAX_HEIGHT_IMAGE_UPLOAD, Define.MAX_WIDTH_IMAGE_UPLOAD, false);
                }
            }
            Matrix matrix = new Matrix();
            if (rotation != 0) {
                matrix.preRotate(rotationInDegrees);
            } else {
            }
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

        } catch (IOException | NullPointerException e) {
            Log.e(FileUtil.class.getSimpleName(), "", e);
        }

        return bitmap;
    }

    public static Bitmap getBitmapRatioFromFile(String path) {
        File file = new File(path);
        Uri uri = Uri.fromFile(file);
        Bitmap bitmap = null;

        try {
            bitmap = MediaStore.Images.Media.getBitmap(App.getContext().getContentResolver(), uri);
            ExifInterface exif = new ExifInterface(path);
            int rotation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            int rotationInDegrees = exifToDegrees(rotation);
            Matrix matrix = new Matrix();
            if (rotation != 0) {
                matrix.preRotate(rotationInDegrees);
            } else {
            }
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            return bitmap;

        } catch (IOException | NullPointerException e) {
            Log.e(FileUtil.class.getSimpleName(), "", e);
        }

        return bitmap;
    }

    public static ExifInterface getExifFromFile(String path) {
        File file = new File(path);
        Uri uri = Uri.fromFile(file);
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(uri.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return exif;
    }

    public static int exifToDegrees(int exifOrientation) {
        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
            return Define.ROTATION_90;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
            return Define.ROTATION_180;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
            return Define.ROTATION_270;
        }
        return 0;
    }

    public static Bitmap cutBitmapTofitView(Bitmap bitmap, View view) {
        int widthBitmap = bitmap.getWidth();
        int heightBitmap = bitmap.getHeight();

        int widthView = view.getWidth();
        int heightView = view.getHeight();

        float ratioBitmap = (float) widthBitmap / heightBitmap;
        float ratioView = (float) widthView / heightView;
        if (ratioBitmap == ratioView) {
//            return Bitmap.createScaledBitmap(bitmap, widthView, heightView, false);
            return bitmap;
        }

        if (ratioView > ratioBitmap) {
            //cut top and bottom
            int heightScaleFit = heightView * widthBitmap / widthView;
//            bitmap = Bitmap.createScaledBitmap(bitmap, widthView, heightScaleFit, false);
            bitmap = Bitmap.createBitmap(bitmap
                    , 0
                    , (heightBitmap - heightScaleFit) / 2
                    , widthBitmap
                    , heightScaleFit
            );
        } else {
            //cut left and right
//            int widthScaleFit = heightView * widthBitmap / heightBitmap;
//            bitmap = Bitmap.createScaledBitmap(bitmap, widthScaleFit, heightView, false);
//            bitmap = Bitmap.createBitmap(bitmap
//                    , (widthScaleFit - widthView) / 2
//                    , 0
//                    , widthView
//                    , heightView
//            );
            int widthScaleFit = heightBitmap * widthView / heightView;
//            bitmap = Bitmap.createScaledBitmap(bitmap, widthScaleFit, heightView, false);
            bitmap = Bitmap.createBitmap(bitmap
                    , (widthBitmap - widthScaleFit) / 2
                    , 0
                    , widthScaleFit
                    , heightBitmap
            );
        }
        return bitmap;
    }

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }

    public static Bitmap flipBitmap(Bitmap source) {
        Matrix matrix = new Matrix();
        matrix.postScale(-1, 1, source.getWidth() / 2f, source.getHeight() / 2f);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, int rotation) {
        Matrix matrix = new Matrix();
        matrix.setRotate(rotation, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
        Bitmap bitmap1 = Bitmap.createBitmap(bitmap.getHeight(), bitmap.getWidth(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap1);
        canvas.drawBitmap(bitmap, matrix, new Paint());
        return bitmap1;
    }

    public static String savePhotoToFileTemp(Bitmap bitmap, String fileName) {
        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath();
        File dir = new File(root, Define.APP_FOLDER_CAPTURED_TEMP);
        dir.mkdirs();
        File file = new File(dir, fileName + Define.IMAGE_JPG);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, QUALITY_BITMAP_80, out);
            out.flush();
            out.close();
            return file.getAbsolutePath();
        } catch (Exception e) {
            Log.e(FileUtil.class.getSimpleName(), "", e);
            return null;
        }
    }

    public static String copyBitmapAndResizeToTemp(Bitmap src, String fileName) {
        Bitmap bitmap = resizeBitmapToLessThanOneMb(src);

        return savePhotoToFileTemp(bitmap, fileName);
    }

    public static void deleteAllImageTemp() {
        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath();
        File dir = new File(root, Define.APP_FOLDER_CAPTURED);
        removeRecursive(dir);
    }

    // check file is .gif
    public static boolean isFileGif(String filePath) {
        try {
            File file = new File(filePath);
            FileInputStream fileInputStream = new FileInputStream(file);
            Movie gif = Movie.decodeStream(fileInputStream);
            return gif != null;
        } catch (IOException ie) {
        }
        return false;
    }
    public static String getPath(String fileName) {
        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath();
        File dir = new File(root, Define.APP_FOLDER_CAPTURED);
        dir.mkdirs();
        File file = new File(dir, fileName + Define.IMAGE_JPG);
        return file.getAbsolutePath();
    }

    //region -> filePath

    public static String getRealPathFromUri(Context context, final Uri uri) {
        // DocumentProvider
        if (DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {


                String fileName = getFilePath(context, uri);
                if (fileName != null) {
                    return Environment.getExternalStorageDirectory().toString() + "/Download/" + fileName;
                }

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);


//                final String id = DocumentsContract.getDocumentId(uri);
//                final Uri contentUri = ContentUris.withAppendedId(
//                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
//
//                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGoogleOldPhotosUri(uri)) {
                // return http path, then download file.
                return uri.getLastPathSegment();
            } else if (isGoogleNewPhotosUri(uri)) {
                // copy from uri. context.getContentResolver().openInputStream(uri);
                return copyFile(context, uri);
            }

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    public static String getFilePath(Context context, Uri uri) {

        Cursor cursor = null;
        final String[] projection = {
                MediaStore.MediaColumns.DISPLAY_NAME
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, null, null,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return null;
    }


    private static String getDataColumn(Context context, Uri uri, String selection,
                                        String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isGoogleOldPhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    public static boolean isGoogleNewPhotosUri(Uri uri) {
        return "com.google.android.apps.photos.contentprovider".equals(uri.getAuthority());
    }

    private static String copyFile(Context context, Uri uri) {

        String filePath;
        InputStream inputStream = null;
        BufferedOutputStream outStream = null;
        try {
            inputStream = context.getContentResolver().openInputStream(uri);
            File extDir = context.getExternalFilesDir(null);
            filePath = extDir.getAbsolutePath() + "/IMG_" + UUID.randomUUID().toString() + ".jpg";
            outStream = new BufferedOutputStream(new FileOutputStream
                    (filePath));

            byte[] buf = new byte[2048];
            int len;
            while ((len = inputStream.read(buf)) > 0) {
                outStream.write(buf, 0, len);
            }

        } catch (IOException e) {
            e.printStackTrace();
            filePath = "";
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (outStream != null) {
                    outStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return filePath;
    }

    public static void deleteFileSoundRecord() {
        String root = Environment.getExternalStorageDirectory().getAbsolutePath();
        File dir = new File(root, "SoundRecorder");
        removeRecursive(dir);
    }

    public static int getDrawableResource(Context context, String name) {
        return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    }

}
