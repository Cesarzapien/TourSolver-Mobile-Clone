package com.cesar.toursolvermobile2;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.core.GeoOrientationUpdate;
import com.here.sdk.core.engine.SDKNativeEngine;
import com.here.sdk.core.engine.SDKOptions;
import com.here.sdk.core.errors.InstantiationErrorException;
import com.here.sdk.mapview.LocationIndicator;
import com.here.sdk.mapview.MapError;
import com.here.sdk.mapview.MapMeasure;
import com.here.sdk.mapview.MapPolyline;
import com.here.sdk.mapview.MapScene;
import com.here.sdk.mapview.MapScheme;
import com.here.sdk.mapview.MapView;
import com.here.sdk.transport.TruckSpecifications;
import com.here.sdk.transport.TruckType;

public class mapHelper{

    private static final int REQUEST_INTERNET_PERMISSION = 1;
    private static final int REQUEST_LOCATION_PERMISSION = 2;
    private PermissionResultCallback permissionResultCallback;
    private LocationIndicator currentLocationIndicator;
    private final List<MapPolyline> mapPolylines = new ArrayList<>();

    public void initializeHERESDK(Context context) {
        String accessKeyID = "kFQ5gYJvmOwdoeA94GlfWw";
        String accessKeySecret = "l2XlfnoRv8eY4X40KfGOB6s5u820HsCARXLvLMBiM-wmDLcF6dLGWNLNR-Y1-cQ7Cr_PhrZIz1Aurjm245tEXg";
        SDKOptions options = new SDKOptions(accessKeyID, accessKeySecret);
        try {
            SDKNativeEngine.makeSharedInstance(context, options);
        } catch (InstantiationErrorException e) {
            throw new RuntimeException("Initialization of HERE SDK failed: " + e.error.name());
        }
    }

    public void loadMapScene(MapView mapView) {
        mapView.getMapScene().loadScene(MapScheme.NORMAL_DAY, new MapScene.LoadSceneCallback() {
            @Override
            public void onLoadScene(@Nullable MapError mapError) {
                if (mapError == null) {
                    double distanceInMeters = 1000 * 10;
                    MapMeasure mapMeasureZoom = new MapMeasure(MapMeasure.Kind.DISTANCE, distanceInMeters);
                    mapView.getCamera().lookAt(new GeoCoordinates(21.144301, -101.691806), mapMeasureZoom);
                } else {
                    Log.d("loadMapScene()", "Loading map failed: mapError: " + mapError.name());
                }
            }
        });
    }

    public void permisoInternet(Activity activity, PermissionResultCallback callback) {
        this.permissionResultCallback = callback;
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.INTERNET}, REQUEST_INTERNET_PERMISSION);
        } else {
            callback.permisoConcedido();
        }
    }

    public void permisoLocalizacion(Activity activity, PermissionResultCallback callback) {
        this.permissionResultCallback = callback;
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
        } else {
            callback.permisoConcedido();
        }
    }

    public interface PermissionResultCallback {
        void permisoConcedido();
        void permisoDenegado();
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_INTERNET_PERMISSION:
            case REQUEST_LOCATION_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (permissionResultCallback != null) {
                        permissionResultCallback.permisoConcedido();
                    }
                } else {
                    if (permissionResultCallback != null) {
                        permissionResultCallback.permisoDenegado();
                    }
                }
                break;
        }
    }

    public void tiltMap(MapView mapView) {
        double bearing = mapView.getCamera().getState().orientationAtTarget.bearing;
        double tilt =  10;
        mapView.getCamera().setOrientationAtTarget(new GeoOrientationUpdate(bearing, tilt));
    }

    public void showGPSDisabledDialog(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("GPS desactivado");
        builder.setMessage("El GPS está desactivado. ¿Desea activarlo?");
        builder.setPositiveButton("Activar GPS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                activity.startActivity(intent);
            }
        });
        builder.setNegativeButton("Cancelar", null);
        builder.show();
    }

    public boolean isGPSEnabled(Activity activity) {
        LocationManager locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    public void disposeHERESDK() {
        SDKNativeEngine sdkNativeEngine = SDKNativeEngine.getSharedInstance();
        if (sdkNativeEngine != null) {
            sdkNativeEngine.dispose();
            SDKNativeEngine.setSharedInstance(null);
        }
    }

    public void moverCamaraAUbicacionActual(Activity activity, MapView mapView) {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationManager locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
            Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (lastKnownLocation != null) {
                GeoCoordinates userCoordinates = new GeoCoordinates(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());
                double distanceInMeters = 1000 * 0.5;
                MapMeasure mapMeasureZoom = new MapMeasure(MapMeasure.Kind.DISTANCE, distanceInMeters);
                mapView.getCamera().lookAt(userCoordinates, mapMeasureZoom);
            } else {
                Toast.makeText(activity, "No se pudo obtener la ubicación actual", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(activity, "Permiso de ubicación no concedido", Toast.LENGTH_SHORT).show();
        }
    }

    public GeoCoordinates generateCoords(Activity activity, Intent intent) {
        GeoCoordinates coordenadasDestino = null;
        try {
            String dat = "";
            if (intent != null && intent.getData() != null) {
                if (intent.getScheme().equals("geo")) {
                    Uri data = intent.getData();
                    dat = data.toString();
                    Log.d("mapHelper", "URI recibida: " + dat);
                    Pattern p = Pattern.compile("(-?\\d+\\.\\d+)");
                    Matcher m = p.matcher(dat);
                    List<Double> coordinates = new ArrayList<>();
                    while (m.find()) {
                        coordinates.add(Double.parseDouble(m.group()));
                    }
                    if (coordinates.size() >= 2) {
                        coordenadasDestino = new GeoCoordinates(coordinates.get(0), coordinates.get(1));
                    }
                }
            }
        } catch (Exception e) {
            Toast.makeText(activity.getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return coordenadasDestino;
    }



    public void generarRuta(Activity activity, MapView mapView, GeoCoordinates coordenadasDestino) {
        if (ContextCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationManager locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
            Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (lastKnownLocation != null) {
                GeoCoordinates coordenadasInicio = new GeoCoordinates(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());
                RoutingExample routingExample = new RoutingExample(activity, mapView, coordenadasInicio);
                routingExample.addRoute(coordenadasInicio, coordenadasDestino);
            } else {
                Toast.makeText(activity, "No se pudo obtener la ubicación actual", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(activity, "Permiso de ubicación no concedido", Toast.LENGTH_SHORT).show();
        }
    }
}
