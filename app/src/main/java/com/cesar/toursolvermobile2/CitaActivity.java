package com.cesar.toursolvermobile2;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.cesar.toursolvermobile2.model.Geocode;
import com.cesar.toursolvermobile2.model.OperationalOrderAchievement;
import com.cesar.toursolvermobile2.model.Order;
import com.cesar.toursolvermobile2.model.PlannedOrder;
import com.here.sdk.core.Color;
import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.core.GeoPolyline;
import com.here.sdk.core.engine.SDKNativeEngine;
import com.here.sdk.core.engine.SDKOptions;
import com.here.sdk.core.errors.InstantiationErrorException;
import com.here.sdk.mapview.LineCap;
import com.here.sdk.mapview.LocationIndicator;
import com.here.sdk.mapview.MapCamera;
import com.here.sdk.mapview.MapError;
import com.here.sdk.mapview.MapImage;
import com.here.sdk.mapview.MapImageFactory;
import com.here.sdk.mapview.MapMarker;
import com.here.sdk.mapview.MapMeasure;
import com.here.sdk.mapview.MapMeasureDependentRenderSize;
import com.here.sdk.mapview.MapPolyline;
import com.here.sdk.mapview.MapScene;
import com.here.sdk.mapview.MapScheme;
import com.here.sdk.mapview.MapView;
import com.here.sdk.mapview.RenderSize;
import com.here.sdk.routing.CalculateRouteCallback;
import com.here.sdk.routing.CarOptions;
import com.here.sdk.routing.Maneuver;
import com.here.sdk.routing.ManeuverAction;
import com.here.sdk.routing.PaymentMethod;
import com.here.sdk.routing.Route;
import com.here.sdk.routing.RoutingEngine;
import com.here.sdk.routing.RoutingError;
import com.here.sdk.routing.Section;
import com.here.sdk.routing.SectionNotice;
import com.here.sdk.routing.Span;
import com.here.sdk.routing.Toll;
import com.here.sdk.routing.TollFare;
import com.here.sdk.routing.TrafficSpeed;
import com.here.sdk.routing.Waypoint;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class CitaActivity extends AppCompatActivity {

    private ImageButton Back_button;

    private TextView nameLabel, zipCode, dateLabel, hourLabel, nameLabel2;

    private MapView mapView;

    private static final int REQUEST_INTERNET_PERMISSION = 100;
    private static final int REQUEST_LOCATION_PERMISSION = 101;

    private PlatformPositioningProvider positioningProvider;
    private LocationIndicator currentLocationIndicator;
    private RoutingExample routingExample;

    private double achievementLat;
    private double achievementLon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Usually, you need to initialize the HERE SDK only once during the lifetime of an application.
        initializeHERESDK();
        setContentView(R.layout.activity_cita);

        // Get a MapView instance from the layout.
        mapView = findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);

        // Solicitar permisos de internet y de localización
        requestInternetPermission();
        requestLocationPermission();

        // Initialize positioning provider
        positioningProvider = new PlatformPositioningProvider(this);

        loadMapScene();

        nameLabel = findViewById(R.id.nameLabel);
        zipCode = findViewById(R.id.idLabel);
        dateLabel = findViewById(R.id.dateLabel);
        hourLabel = findViewById(R.id.timeSlotLabel);
        nameLabel2 = findViewById(R.id.descriptionText);

        // Obtener los datos actuales del usuario
        Intent currentIntent = getIntent();
        int validIndex = currentIntent.getIntExtra("position", -1);
        String userName = currentIntent.getStringExtra("user_name");
        String userEmail = currentIntent.getStringExtra("user_email");
        // Obtener los datos de PlannedOrder y Order del intent
        List<PlannedOrder> plannedOrders = getIntent().getParcelableArrayListExtra("plannedOrders");
        List<Order> orders = getIntent().getParcelableArrayListExtra("orders");
        List<OperationalOrderAchievement> achievements = getIntent().getParcelableArrayListExtra("achievements");
        List<Geocode> geocodes = getIntent().getParcelableArrayListExtra("geocodes");
        String hour = currentIntent.getStringExtra("hora_exacta");
        Back_button = findViewById(R.id.backButton);

        if (achievements != null && orders != null && validIndex >= 0) {
            Order order = orders.get(validIndex); // Posición válida
            OperationalOrderAchievement operationalOrderAchievement = achievements.get(validIndex);
            Geocode geocode = geocodes.get(validIndex);
            Log.d(TAG, "operational " + operationalOrderAchievement.toString());
            Log.d(TAG, "orders " + order.toString());

            // Obtener el nombre de la cita
            String nombrecita = order.getLabel();
            String zipcode = geocode.getPostcode();

            nameLabel.setText(nombrecita);
            zipCode.setText(zipcode);
            nameLabel2.setText(nombrecita);

            long fecha_inicio = operationalOrderAchievement.getStart();
            long fecha_termino = operationalOrderAchievement.getEnd();

            Log.d(TAG, "start " + fecha_inicio);
            Log.d(TAG, "end " + fecha_termino);

            // Agregar una hora a fecha_inicio y fecha_termino (3600000 milisegundos = 1 hora)
            fecha_inicio += 3600000;
            fecha_termino += 3600000;

            // Convertir fecha y hora de Epoch a formato legible
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE d MMMM yyyy", new Locale("es", "MX"));
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", new Locale("es", "MX"));
            TimeZone timeZone = TimeZone.getTimeZone("America/Mexico_City"); // Cambia la zona horaria si es necesario
            dateFormat.setTimeZone(timeZone);
            timeFormat.setTimeZone(timeZone);

            Date startDate = new Date(fecha_inicio);
            Date endDate = new Date(fecha_termino);

            String formattedDate = dateFormat.format(startDate);
            String formattedStartTime = timeFormat.format(startDate);
            String formattedEndTime = timeFormat.format(endDate);

            dateLabel.setText(formattedDate);
            hourLabel.setText(formattedStartTime + " - " + formattedEndTime);

            // Obtener latitud y longitud de achievements
            achievementLat = operationalOrderAchievement.getLat();
            achievementLon = operationalOrderAchievement.getLon();

            Log.d(TAG,"latitud "+achievementLat);
            Log.d(TAG,"longitud "+achievementLon);

        }

        Back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CitaActivity.this, InicioActivity.class);
                //intent.putExtra("position", validIndex);
                intent.putExtra("user_name", userName);
                intent.putExtra("user_email", userEmail);
                intent.putParcelableArrayListExtra("plannedOrders", new ArrayList<>(plannedOrders));
                intent.putParcelableArrayListExtra("orders", new ArrayList<>(orders));
                intent.putParcelableArrayListExtra("geocodes", new ArrayList<>(geocodes));
                intent.putParcelableArrayListExtra("achievements", new ArrayList<>(achievements));
                intent.putExtra("hora_exacta", hour);
                startActivity(intent);
                finish();
            }
        });

    }

    private void initializeHERESDK() {
        // Set your credentials for the HERE SDK.
        String accessKeyID = "kFQ5gYJvmOwdoeA94GlfWw";
        String accessKeySecret = "l2XlfnoRv8eY4X40KfGOB6s5u820HsCARXLvLMBiM-wmDLcF6dLGWNLNR-Y1-cQ7Cr_PhrZIz1Aurjm245tEXg";
        SDKOptions options = new SDKOptions(accessKeyID, accessKeySecret);
        try {
            Context context = this;
            SDKNativeEngine.makeSharedInstance(context, options);
        } catch (InstantiationErrorException e) {
            throw new RuntimeException("Initialization of HERE SDK failed: " + e.error.name());
        }
    }

    private void loadMapScene() {
        // Verifica si tienes permisos para acceder a la ubicación del usuario
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // Obtén la última ubicación conocida del usuario
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (lastKnownLocation != null) {
                // Si se encuentra una ubicación conocida, mueve la cámara del mapa a esa ubicación
                GeoCoordinates userCoordinates = new GeoCoordinates(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());
                routingExample = new RoutingExample(CitaActivity.this, mapView,userCoordinates);

                //mapView.getCamera().lookAt(userCoordinates);
            }
        }
        // Verifica si es después de las 8:00 p.m. y antes de las 6:00 a.m.
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour >= 20 || hour < 6) {
            // Carga la escena del mapa con el esquema MapScheme.NORMAL_NIGHT
            mapView.getMapScene().loadScene(MapScheme.NORMAL_NIGHT, new MapScene.LoadSceneCallback() {
                @Override
                public void onLoadScene(@Nullable MapError mapError) {
                    if (mapError == null) {
                        // No se produjo ningún error al cargar la escena del mapa
                    } else {
                        Log.d("loadMapScene()", "Loading map failed: mapError: " + mapError.name());
                    }
                }
            });
        } else {
            // Carga la escena del mapa con el esquema MapScheme.NORMAL_DAY
            mapView.getMapScene().loadScene(MapScheme.NORMAL_DAY, new MapScene.LoadSceneCallback() {
                @Override
                public void onLoadScene(@Nullable MapError mapError) {
                    if (mapError == null) {
                        // No se produjo ningún error al cargar la escena del mapa
                    } else {
                        Log.d("loadMapScene()", "Loading map failed: mapError: " + mapError.name());
                    }
                }
            });
        }
    }

    private void disposeHERESDK() {
        // Free HERE SDK resources before the application shuts down.
        // Usually, this should be called only on application termination.
        // Afterwards, the HERE SDK is no longer usable unless it is initialized again.
        SDKNativeEngine sdkNativeEngine = SDKNativeEngine.getSharedInstance();
        if (sdkNativeEngine != null) {
            sdkNativeEngine.dispose();
            // For safety reasons, we explicitly set the shared instance to null to avoid situations,
            // where a disposed instance is accidentally reused.
            SDKNativeEngine.setSharedInstance(null);
        }
    }

    // Método para solicitar el permiso de Internet
    private void requestInternetPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, REQUEST_INTERNET_PERMISSION);
        } else {
            // El permiso ya está concedido
        }
    }

    // Método para solicitar el permiso de localización
    private void requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
        } else {
            // El permiso ya está concedido
        }
    }

    // Método para manejar las respuestas de las solicitudes de permisos
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_INTERNET_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // El permiso de Internet fue concedido
                } else {
                    // El permiso de Internet fue denegado
                }
                break;
            case REQUEST_LOCATION_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // El permiso de localización fue concedido
                } else {
                    // El permiso de localización fue denegado
                }
                break;
        }
    }

    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        disposeHERESDK();
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        mapView.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }




}