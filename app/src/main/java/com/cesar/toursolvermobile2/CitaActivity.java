package com.cesar.toursolvermobile2;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.cesar.toursolvermobile2.model.Geocode;
import com.cesar.toursolvermobile2.model.OperationalOrderAchievement;
import com.cesar.toursolvermobile2.model.Order;
import com.cesar.toursolvermobile2.model.PlannedOrder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class CitaActivity extends AppCompatActivity {

    private ImageButton Back_button;

    private TextView nameLabel, zipCode, dateLabel, hourLabel, nameLabel2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cita);

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
}