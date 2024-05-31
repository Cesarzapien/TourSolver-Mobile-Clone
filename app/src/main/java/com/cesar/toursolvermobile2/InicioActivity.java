package com.cesar.toursolvermobile2;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cesar.toursolvermobile2.adapter.PlannedOrderAdapter;
import com.cesar.toursolvermobile2.databinding.ActivityInicioBinding;
import com.cesar.toursolvermobile2.model.Order;
import com.cesar.toursolvermobile2.model.PlannedOrder;
import com.google.android.material.navigation.NavigationView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class InicioActivity extends DrawerBaseActivity {

    ActualizarAlert actualizarAlert = new ActualizarAlert(InicioActivity.this);
    TextView userNamee,userEmaill,profileName;
    ActivityInicioBinding activityInicioBinding;
    private CountDownTimer mCountDownTimer;
    private static final long COUNTDOWN_TIME = 180000; // 3 minutos
    private boolean isPaused = true;

    // Define el formato de hora para parsear y formatear
    private SimpleDateFormat inputFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
    private SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

    // TextView para mostrar la próxima cita
    private TextView citaHoraTextView,horaa;

    private RecyclerView recyclerView;
    private PlannedOrderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityInicioBinding = ActivityInicioBinding.inflate(getLayoutInflater());
        setContentView(activityInicioBinding.getRoot());

        // Referenciar el TextView de la próxima cita
        citaHoraTextView = findViewById(R.id.cita_hora);
        horaa = findViewById(R.id.horaa);

        // Obtener los datos del Intent
        Intent intent = getIntent();
        String userName = intent.getStringExtra("user_name");
        String userEmail = intent.getStringExtra("user_email");
        String hora_exact = intent.getStringExtra("hora_exacta");

        horaa.setText("Actualizado hoy a las " +hora_exact);



        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Obtener los datos de PlannedOrder y Order del intent
        List<PlannedOrder> plannedOrders = getIntent().getParcelableArrayListExtra("plannedOrders");
        List<Order> orders = getIntent().getParcelableArrayListExtra("orders");

        // Verificar si hay datos disponibles y obtener la segunda posición
        if (plannedOrders != null && plannedOrders.size() > 1 && orders != null && orders.size() > 1) {
            PlannedOrder plannedOrder = plannedOrders.get(1); // Segunda posición
            Order order = orders.get(1); // Segunda posición

            // Formatear la hora de inicio de la parada
            String startTimeFormatted = formatTime(plannedOrder.getStopStartTime());

            // Formatear la hora de finalización y la duración
            String endTimeAndDurationFormatted = calculateEndTime(plannedOrder.getStopStartTime(), plannedOrder.getStopDuration());

            // Construir el texto para mostrar la próxima cita
            String citaHoraText = startTimeFormatted + " - " + endTimeAndDurationFormatted;
            citaHoraTextView.setText(citaHoraText);
        }else{
            // Si las listas están vacías, ocultar los elementos y mostrar el texto "Ninguna cita planificada hoy"
            recyclerView.setVisibility(View.GONE);
            findViewById(R.id.proxima_cita_textview).setVisibility(View.GONE);
            findViewById(R.id.cita_detalles).setVisibility(View.GONE);
            findViewById(R.id.linea_cita).setVisibility(View.GONE);

            TextView noCitasTextView = findViewById(R.id.citas_disponibles);
            noCitasTextView.setVisibility(View.VISIBLE);
            noCitasTextView.setText("Ninguna cita planificada hoy");
            noCitasTextView.setTextColor(getResources().getColor(R.color.gray)); // Establece el color de texto a gris
        }

        // Configurar el RecyclerView con el adaptador
        adapter = new PlannedOrderAdapter(this, plannedOrders, orders); // Pass orders to adapter
        recyclerView.setAdapter(adapter);

        // Obtener la referencia del NavigationView
        NavigationView navigationView = findViewById(R.id.nav_view);

        // Obtener la referencia del HeaderView del NavigationView
        View headerView = navigationView.getHeaderView(0);

        // Obtener los TextView del HeaderView
        profileName = findViewById(R.id.profilename);
        userNamee = headerView.findViewById(R.id.username); // Asegúrate de tener este TextView en tu layout
        userEmaill = headerView.findViewById(R.id.useremail); // Asegúrate de tener este TextView en tu layout

        // Sobrescribir los strings
        if (userName != null) {
            userNamee.setText(userName);
            profileName.setText(userName);
        }

        if (userEmail != null) {
            userEmaill.setText(userEmail);
        }

        activityInicioBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });
    }

    // Método para formatear la hora en formato HH:mm
    private String formatTime(String timeString) {
        try {
            Date date = inputFormat.parse(timeString);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return timeString; // En caso de error, devolver la cadena original
        }
    }

    // Método para calcular la hora de finalización sumando la hora de inicio y la duración
    // Método para calcular la hora de finalización sumando la hora de inicio y la duración
    private String calculateEndTime(String startTime, String duration) {
        try {
            // Parsear la hora de inicio y la duración
            Date startDate = inputFormat.parse(startTime);
            String[] durationParts = duration.split(":"); // Dividir la duración en horas y minutos
            int durationHours = Integer.parseInt(durationParts[0]);
            int durationMinutes = Integer.parseInt(durationParts[1]);

            // Calcular la duración total en milisegundos
            long durationMillis = (durationHours * 60 * 60 * 1000) + (durationMinutes * 60 * 1000);

            // Calcular la hora de finalización sumando la hora de inicio y la duración
            Date endDate = new Date(startDate.getTime() + durationMillis);

            // Formatear la duración en minutos
            String durationFormatted = durationMinutes + " min";

            return outputFormat.format(endDate) + " (" + durationFormatted + ")";
        } catch (ParseException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
            e.printStackTrace();
            return ""; // En caso de error, devolver cadena vacía
        }
    }



    private List<Object> combineLists(ArrayList<PlannedOrder> plannedOrders, ArrayList<Order> orders) {
        List<Object> combinedList = new ArrayList<>();

        // Si plannedOrders es null o vacío, añadir un elemento por defecto
        if (plannedOrders == null || plannedOrders.isEmpty()) {
            combinedList.add("No hay datos disponibles");
        } else {
            // Si plannedOrders no es null, agregar los datos de plannedOrders
            combinedList.addAll(plannedOrders);
        }

        // Si orders no es null y tiene elementos, agregar los datos de orders
        if (orders != null && !orders.isEmpty()) {
            combinedList.addAll(orders);
        }

        return combinedList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_inicio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.item_settings) {
            actualizarAlert.startAlertDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onPause() {
        super.onPause();
        isPaused = true;
        startTimer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        isPaused = false;
        stopTimer();
    }

    @Override
    public void onBackPressed() {
        showExitDialog();
    }

    private void showExitDialog() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        ExitDialog exitDialog = new ExitDialog();
        exitDialog.setExitDialogListener(new ExitDialog.ExitDialogListener() {
            @Override
            public void onExitConfirmed() {
                finishAffinity();
            }
        });
        exitDialog.show(fragmentManager, "exit_dialog");
    }

    private void startTimer() {
        if (isPaused && mCountDownTimer == null) {
            mCountDownTimer = new CountDownTimer(COUNTDOWN_TIME, COUNTDOWN_TIME) {
                @Override
                public void onTick(long millisUntilFinished) {
                    // No necesitas hacer nada aquí para este caso
                }

                @Override
                public void onFinish() {
                    redirectToLoginActivity();
                }
            }.start();
        }
    }

    private void stopTimer() {
        if (!isPaused && mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
    }

    private void resetTimer() {
        stopTimer();
        startTimer();
    }

    private void redirectToLoginActivity() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }


}