package com.beetech.kayak.utils;

import com.beetech.kayak.BuildConfig;

public class Define {

    public static final long CLICK_TIME_INTERVAL = 300L;
    public static final long DEFAULT_TIMEOUT = 30L;

    public static final String SERVICE = "service";

    public static final String APP_FOLDER_NAME = ".base";
    public static final String APP_FOLDER_CAPTURED = "base_captured";
    public static final String APP_FOLDER_CAPTURED_CODE = "code_base";
    public static final String APP_FOLDER_CAPTURED_OPEN = "base";
    public static final String APP_FOLDER_CAPTURED_TEMP = ".base_captured_temp";
    public static final String APP_FOLDER_CAPTURED_PROVIDER = BuildConfig.APPLICATION_ID + ".fileprovider";
    public static final int REQUEST_SERVER_SUCCESS = 200;


    //image
    public static final String IMAGE_JPG = ".jpg";
    public static final int QUALITY_BITMAP_100 = 100;
    public static final int QUALITY_BITMAP_80 = 80;
    public static final int MAX_HEIGHT_IMAGE_UPLOAD = 1600;
    public static final int MAX_WIDTH_IMAGE_UPLOAD = 1200;
    public static final int MB_SIZE = 1024 * 1024;
    public static final int MAX_SIZE_FILE_ULOAD = 10;
    public static final int MAX_SIZE_IMAGE_ULOAD = 2;
    public static final int MAX_QUANTILY_IMAGE_UPLOAD = 10;

    //rotation image bitmap
    public static final int ROTATION_90 = 90;
    public static final int ROTATION_180 = 180;
    public static final int ROTATION_270 = 270;
}
