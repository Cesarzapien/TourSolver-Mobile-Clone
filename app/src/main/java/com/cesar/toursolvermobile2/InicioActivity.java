package com.cesar.toursolvermobile2;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cesar.toursolvermobile2.adapter.PlannedOrderAdapter;
import com.cesar.toursolvermobile2.databinding.ActivityInicioBinding;
import com.cesar.toursolvermobile2.model.ApiResponse;
import com.cesar.toursolvermobile2.model.OperationalOrderAchievement;
import com.cesar.toursolvermobile2.model.Order;
import com.cesar.toursolvermobile2.model.PlannedOrder;
import com.google.android.material.navigation.NavigationView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

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

    public String hora_global;

    private ImageButton boton_cita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityInicioBinding = ActivityInicioBinding.inflate(getLayoutInflater());
        setContentView(activityInicioBinding.getRoot());

        // Referenciar el TextView de la próxima cita
        citaHoraTextView = findViewById(R.id.cita_hora);
        horaa = findViewById(R.id.horaa);

        boton_cita = findViewById(R.id.arrow_button);

        // Obtener los datos del Intent
        Intent intent = getIntent();
        String userName = intent.getStringExtra("user_name");
        String userEmail = intent.getStringExtra("user_email");
        hora_global = intent.getStringExtra("hora_exacta");

        // Mostrar la hora exacta inicial
        horaa.setText("Actualizado hoy a las " + hora_global);



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

        boton_cita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioActivity.this, CitaActivity.class);
                startActivity(intent);
                finish();
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
            refreshData();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void refreshData() {
        // Mostrar el diálogo de carga
        actualizarAlert.startAlertDialog();

        // Obtener la fecha y hora actual
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();

        // Calcular la fecha para el día siguiente
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrowDate = calendar.getTime();

        // Formatear las fechas en el formato necesario para la llamada a la API
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm", Locale.getDefault());
        hora_global = sdf2.format(currentDate);
        String startDate = sdf.format(currentDate);
        String endDate = sdf.format(tomorrowDate);

        // Obtener el correo del usuario (este valor debe ser obtenido desde el intent o algún otro lugar)
        String userLogin = getIntent().getStringExtra("user_email"); // Asumiendo que es el correo

        // Crear una instancia de Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Login.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Crear una instancia del servicio de la API
        Login.ApiService apiService = retrofit.create(Login.ApiService.class);

        // Realizar la llamada a la API
        Call<ApiResponse> call = apiService.getFulfillment(
                Login.API_KEY,
                Login.ACCEPT,
                endDate, // Usar la fecha de mañana como endDate
                startDate, // Usar la fecha actual como startDate
                userLogin
        );

        // Ejecutar la llamada de manera asíncrona
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                actualizarAlert.closeAlertDialog();
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse apiResponse = response.body();

                    List<OperationalOrderAchievement> achievementsList = apiResponse.getOperationalOrderAchievements();
                    List<PlannedOrder> plannedOrders = new ArrayList<>();
                    List<Order> orders = new ArrayList<>();

                    // Obtener la lista de PlannedOrder de OperationalOrderAchievement y filtrar los elementos con stopId "Llegada"
                    for (OperationalOrderAchievement achievement : achievementsList) {
                        PlannedOrder plannedOrder = achievement.getPlannedOrder();
                        if (plannedOrder != null && !"Llegada".equals(plannedOrder.getStopId())) {
                            plannedOrders.add(plannedOrder);
                        }
                    }

                    // Obtener la lista de Orders de OperationalOrderAchievement
                    for (OperationalOrderAchievement achievement : achievementsList) {
                        Order order = achievement.getOrder();
                        orders.add(order);
                    }

                    Log.d(TAG, "Achievement: " + achievementsList.toString());
                    Log.d(TAG, "PlannedOrder: " + plannedOrders.toString());
                    Log.d(TAG, "Order: " + orders.toString());

                    // Actualizar el RecyclerView
                    adapter.updateData(plannedOrders, orders);

                    // Actualizar la hora exacta
                    horaa.setText("Actualizado hoy a las " + hora_global);

                    // Actualizar el Intent con la nueva hora
                    getIntent().putExtra("hora_exacta", hora_global);

                } else {
                    Log.e(TAG, "Error en la respuesta de la API: " + response.errorBody());
                    Toast.makeText(InicioActivity.this, "Error en la respuesta de la API", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                actualizarAlert.closeAlertDialog();
                Log.e(TAG, "Error en la llamada a la API", t);
                Toast.makeText(InicioActivity.this, "Error en la llamada a la API", Toast.LENGTH_SHORT).show();
            }
        });
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