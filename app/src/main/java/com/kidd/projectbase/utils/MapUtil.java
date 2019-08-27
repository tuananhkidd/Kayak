package com.kidd.projectbase.utils;

import androidx.annotation.DrawableRes;
import androidx.annotation.RawRes;

//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.model.BitmapDescriptorFactory;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.Marker;
//import com.google.android.gms.maps.model.MarkerOptions;
//import com.google.maps.android.geojson.GeoJsonLayer;
//import com.google.maps.android.geojson.GeoJsonPolygonStyle;


public class MapUtil {

//    public static void addLineBoundCurrentProvince(Context context, GoogleMap googleMap) {
//        MapUtil.addLineBoundProvince(context, googleMap, Define.OpinionProvince.getCurrentProvince().geoJsonLayerFile);
//    }
//
//    public static void addLineBoundProvince(Context context, GoogleMap googleMap, @RawRes int jsonRawFile) {
//        try {
//            GeoJsonLayer layer = new GeoJsonLayer(googleMap, jsonRawFile, context);
//            GeoJsonPolygonStyle style = layer.getDefaultPolygonStyle();
//            style.setFillColor(Color.argb(90, 225, 225, 153));
//            style.setStrokeColor(Color.RED);
//            style.setStrokeWidth(5f);
//
//            layer.addLayerToMap();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void showFocus(GoogleMap googleMap, LatLng latLng, int range) {
//        new Handler().postDelayed(() -> {
//            if (googleMap != null) {
//                googleMap.animateCamera(LocationUtil.getZoomForDistance(latLng, range, 0, 0, 0));
//            }
//        }, 500);
//    }
//
//    public static void moveCamera(GoogleMap googleMap, LatLng latLng, int range) {
//        new Handler().postDelayed(() -> {
//            if (googleMap != null) {
//                googleMap.moveCamera(LocationUtil.getZoomForDistance(latLng, range, 0, 0, 0));
//            }
//        }, 500);
//    }
//
//    public static void showFocusWithoutAnimation(GoogleMap googleMap, LatLng latLng, int range) {
//        new Handler().postDelayed(() -> {
//            if (googleMap != null) {
//                googleMap.moveCamera(LocationUtil.getZoomForDistance(latLng, range, 0, 0, 0));
//            }
//        }, 500);
//    }
//
//    public static Marker showMarker(GoogleMap googleMap, LatLng latLng, Bitmap bitmap) {
//        if (googleMap != null) {
//            return googleMap.addMarker(new MarkerOptions()
//                    .position(latLng)
//                    .icon(BitmapDescriptorFactory.fromBitmap(bitmap)));
//        }
//        return null;
//    }
//
//    public static Marker showMarker(GoogleMap googleMap, LatLng latLng, @DrawableRes int drawID) {
//        if (googleMap != null) {
//            return googleMap.addMarker(new MarkerOptions()
//                    .position(latLng)
//                    .icon(BitmapDescriptorFactory.fromResource(drawID)));
//        }
//        return null;
//    }
//
//    public static Marker showMarker(GoogleMap googleMap, LatLng latLng, String title, @DrawableRes int drawID) {
//        if (googleMap != null) {
//            return googleMap.addMarker(new MarkerOptions()
//                    .position(latLng)
//                    .title(title)
//                    .icon(BitmapDescriptorFactory.fromResource(drawID)));
//        }
//        return null;
//    }
}
