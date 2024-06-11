package com.cesar.toursolvermobile2;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cesar.toursolvermobile2.adapter.PlannedOrderAdapter;
import com.cesar.toursolvermobile2.databinding.ActivityInicioBinding;
import com.cesar.toursolvermobile2.model.ApiResponse;
import com.cesar.toursolvermobile2.model.Geocode;
import com.cesar.toursolvermobile2.model.LastKnownPosition;
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
    private static final long COUNTDOWN_TIME = 600000; // 10 minutos
    private boolean isPaused = true;
    private boolean updateData;

    // Define el formato de hora para parsear y formatear
    private SimpleDateFormat inputFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
    private SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

    // TextView para mostrar la próxima cita
    private TextView citaHoraTextView,horaa,citasDisponibles,citaNombre;

    private RecyclerView recyclerView;
    private PlannedOrderAdapter adapter;

    public String hora_global;

    private ImageButton boton_cita;
    private List<LastKnownPosition> lastKnownPositions2;
    private List<PlannedOrder> plannedOrders2;
    private List<Order> orders2;
    private List<OperationalOrderAchievement> achievements2;
    private List<Geocode> geocodes2 ;
    private Button agenda,sitios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityInicioBinding = ActivityInicioBinding.inflate(getLayoutInflater());
        setContentView(activityInicioBinding.getRoot());

        // Referenciar el TextView de la próxima cita
        agenda = findViewById(R.id.button_agenda);
        sitios = findViewById(R.id.button_sitios);
        citasDisponibles = findViewById(R.id.citas_disponibles);
        citaHoraTextView = findViewById(R.id.cita_hora);
        citaNombre = findViewById(R.id.cita_nombre);
        horaa = findViewById(R.id.horaa);
        boton_cita = findViewById(R.id.arrow_button);

        // Obtener los datos del Intent
        Intent intent = getIntent();
        String userName = intent.getStringExtra("user_name");
        String userEmail = intent.getStringExtra("user_email");
        hora_global = intent.getStringExtra("hora_exacta");

        updateData = false;

        // Mostrar la hora exacta inicial
        horaa.setText("Actualizado hoy a las " + hora_global);



        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Obtener los datos de PlannedOrder y Order del intent
        List<LastKnownPosition> lastKnownPositions = getIntent().getParcelableArrayListExtra("positioning");
        List<OperationalOrderAchievement> achievements = getIntent().getParcelableArrayListExtra("achievements");
        List<PlannedOrder> plannedOrders = getIntent().getParcelableArrayListExtra("plannedOrders");
        List<Order> orders = getIntent().getParcelableArrayListExtra("orders");
        List<Geocode> geocodes = getIntent().getParcelableArrayListExtra("geocodes");


        Log.d(TAG,"Positioning "+lastKnownPositions.toString());
        Log.d(TAG,"Orders Inicio 1 "+orders.toString());
        Log.d(TAG,"achievements Inicio "+achievements.toString());
        Log.d(TAG,"Geocode Inicio"+geocodes.toString());

        // Obtener el tamaño de la lista plannedOrders
        int numCitas = plannedOrders.size();

        // Restar 1 al tamaño de la lista
        numCitas -= 2;

        // Convertir el resultado a String
        String citasDisponiblesText = numCitas + " Cita(s)";

        // Establecer el texto en el TextView citasDisponibles
        citasDisponibles.setText(citasDisponiblesText);

        // Verificar si hay datos disponibles y obtener la próxima posición válida desde la segunda posición
        int validIndex = getNextValidIndex(achievements, 1); // Comenzar desde la segunda posición


        // Verificar si hay datos disponibles y obtener la segunda posición
        if (plannedOrders != null && orders != null && validIndex >= 0) {
            PlannedOrder plannedOrder = plannedOrders.get(validIndex); // Posición válida
            Order order = orders.get(validIndex); // Posición válida
            OperationalOrderAchievement operationalOrderAchievement = achievements.get(validIndex);

            //Log.d(TAG,"orders Inicio 2 "+ order.toString());

            View cita_rectangulo = findViewById(R.id.rectangulo_cita);

            if ("STARTED".equals(operationalOrderAchievement.getStatus())){
                // Obtener el drawable del background del View
                GradientDrawable drawable = (GradientDrawable) cita_rectangulo.getBackground();
                // Cambiar el color del drawable
                drawable.setColor(ContextCompat.getColor(this, R.color.amarillo));
            }else{
                // Obtener el drawable del background del View
                GradientDrawable drawable = (GradientDrawable) cita_rectangulo.getBackground();
                // Cambiar el color del drawable
                drawable.setColor(ContextCompat.getColor(this, R.color.rojo));
            }

            // Formatear la hora de inicio de la parada
            String startTimeFormatted = formatTime(plannedOrder.getStopStartTime());

            // Obtener el nombre de la cita
            String nombrecita = order.getLabel();

            // Formatear la hora de finalización y la duración
            String endTimeAndDurationFormatted = calculateEndTime(plannedOrder.getStopStartTime(), plannedOrder.getStopDuration());

            // Construir el texto para mostrar la próxima cita
            String citaHoraText = startTimeFormatted + " - " + endTimeAndDurationFormatted;
            citaNombre.setText(nombrecita);
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
        adapter = new PlannedOrderAdapter(this, plannedOrders, orders,achievements); // Pass orders to adapter
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
                if (updateData){
                    Intent intent = new Intent(InicioActivity.this, CitaActivity.class);
                    intent.putExtra("position",validIndex);
                    intent.putExtra("user_name", userName);
                    intent.putExtra("user_email", userEmail);
                    intent.putParcelableArrayListExtra("positioning",new ArrayList<>(lastKnownPositions2));
                    intent.putParcelableArrayListExtra("plannedOrders", new ArrayList<>(plannedOrders2));
                    intent.putParcelableArrayListExtra("orders", new ArrayList<>(orders2));
                    intent.putParcelableArrayListExtra("achievements",new ArrayList<>(achievements2));
                    intent.putParcelableArrayListExtra("geocodes",new ArrayList<>(geocodes2));
                    intent.putExtra("hora_exacta", hora_global);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(InicioActivity.this, CitaActivity.class);
                    intent.putExtra("position",validIndex);
                    intent.putExtra("user_name", userName);
                    intent.putExtra("user_email", userEmail);
                    intent.putParcelableArrayListExtra("positioning",new ArrayList<>(lastKnownPositions));
                    intent.putParcelableArrayListExtra("plannedOrders", new ArrayList<>(plannedOrders));
                    intent.putParcelableArrayListExtra("orders", new ArrayList<>(orders));
                    intent.putParcelableArrayListExtra("achievements",new ArrayList<>(achievements));
                    intent.putParcelableArrayListExtra("geocodes",new ArrayList<>(geocodes));
                    intent.putExtra("hora_exacta", hora_global);
                    startActivity(intent);
                    finish();
                }

            }
        });

        agenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (updateData){
                    Intent intent1 = new Intent(InicioActivity.this, AgendaActivity.class);
                    intent1.putExtra("position",validIndex);
                    intent1.putExtra("user_name", userName);
                    intent1.putExtra("user_email", userEmail);
                    intent1.putParcelableArrayListExtra("positioning",new ArrayList<>(lastKnownPositions2));
                    intent1.putParcelableArrayListExtra("plannedOrders", new ArrayList<>(plannedOrders2));
                    intent1.putParcelableArrayListExtra("orders", new ArrayList<>(orders2));
                    intent1.putParcelableArrayListExtra("achievements",new ArrayList<>(achievements2));
                    intent1.putParcelableArrayListExtra("geocodes",new ArrayList<>(geocodes2));
                    intent1.putExtra("hora_exacta", hora_global);
                    startActivity(intent1);
                    finish();
                } else {
                    Intent intent2 = new Intent(InicioActivity.this, AgendaActivity.class);
                    intent2.putExtra("position",validIndex);
                    intent2.putExtra("user_name", userName);
                    intent2.putExtra("user_email", userEmail);
                    intent2.putParcelableArrayListExtra("positioning",new ArrayList<>(lastKnownPositions));
                    intent2.putParcelableArrayListExtra("plannedOrders", new ArrayList<>(plannedOrders));
                    intent2.putParcelableArrayListExtra("orders", new ArrayList<>(orders));
                    intent2.putParcelableArrayListExtra("achievements",new ArrayList<>(achievements));
                    intent2.putParcelableArrayListExtra("geocodes",new ArrayList<>(geocodes));
                    intent2.putExtra("hora_exacta", hora_global);
                    startActivity(intent2);
                    finish();
                }
            }
        });

        sitios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (updateData){
                    Intent intent = new Intent(InicioActivity.this, SitesActivity.class);
                    intent.putExtra("position",validIndex);
                    intent.putExtra("user_name", userName);
                    intent.putExtra("user_email", userEmail);
                    intent.putParcelableArrayListExtra("positioning",new ArrayList<>(lastKnownPositions2));
                    intent.putParcelableArrayListExtra("plannedOrders", new ArrayList<>(plannedOrders2));
                    intent.putParcelableArrayListExtra("orders", new ArrayList<>(orders2));
                    intent.putParcelableArrayListExtra("achievements",new ArrayList<>(achievements2));
                    intent.putParcelableArrayListExtra("geocodes",new ArrayList<>(geocodes2));
                    intent.putExtra("hora_exacta", hora_global);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(InicioActivity.this, SitesActivity.class);
                    intent.putExtra("position",validIndex);
                    intent.putExtra("user_name", userName);
                    intent.putExtra("user_email", userEmail);
                    intent.putParcelableArrayListExtra("positioning",new ArrayList<>(lastKnownPositions));
                    intent.putParcelableArrayListExtra("plannedOrders", new ArrayList<>(plannedOrders));
                    intent.putParcelableArrayListExtra("orders", new ArrayList<>(orders));
                    intent.putParcelableArrayListExtra("achievements",new ArrayList<>(achievements));
                    intent.putParcelableArrayListExtra("geocodes",new ArrayList<>(geocodes));
                    intent.putExtra("hora_exacta", hora_global);
                    startActivity(intent);
                }
            }
        });

    }

    // Método para obtener el próximo índice válido comenzando desde la segunda posición
    private int getNextValidIndex(List<OperationalOrderAchievement> achievements, int startIndex) {
        for (int i = startIndex; i < achievements.size(); i++) {
            if (!"CANCELLED".equals(achievements.get(i).getStatus()) && !"FINISHED".equals(achievements.get(i).getStatus())) {
                return i;
            }
        }
        return -1; // Devuelve -1 si no se encuentra un índice válido
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
            // Call refreshData after a successful response
            updateData = true; // Indicar que se ha realizado un cambio
            refreshData(updateData); // Indicar que se ha realizado un cambio
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void refreshData(boolean updateData) {
        if (updateData) {
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

                        lastKnownPositions2 = apiResponse.getLastKnownPosition();
                        achievements2 = apiResponse.getOperationalOrderAchievements();
                        plannedOrders2 = new ArrayList<>();
                        orders2 = new ArrayList<>();
                        geocodes2 = new ArrayList<>();

                        // Obtener la lista de PlannedOrder de OperationalOrderAchievement y filtrar los elementos con stopId "Llegada"
                        for (OperationalOrderAchievement achievement : achievements2) {
                            PlannedOrder plannedOrder = achievement.getPlannedOrder();
                            if (plannedOrder != null && !"Llegada".equals(plannedOrder.getStopId())) {
                                plannedOrders2.add(plannedOrder);
                            }
                        }

                        // Obtener la lista de Orders de OperationalOrderAchievement
                        for (OperationalOrderAchievement achievement : achievements2) {
                            Order order = achievement.getOrder();
                            orders2.add(order);
                        }

                        for (OperationalOrderAchievement achievement : achievements2) {
                            Geocode geocode = achievement.getGeocode();
                            if (geocode != null) {
                                geocodes2.add(geocode);
                            }
                        }

                        // Verificar si hay datos disponibles y obtener la próxima posición válida desde la segunda posición
                        int validIndex = getNextValidIndex(achievements2, 1); // Comenzar desde la segunda posición


                        // Verificar si hay datos disponibles y obtener la segunda posición
                        if (plannedOrders2 != null && orders2 != null && validIndex >= 0) {
                            PlannedOrder plannedOrder = plannedOrders2.get(validIndex); // Posición válida
                            Order order = orders2.get(validIndex); // Posición válida
                            OperationalOrderAchievement operationalOrderAchievement = achievements2.get(validIndex);

                            //Log.d(TAG,"orders Inicio 2 "+ order.toString());

                            View cita_rectangulo = findViewById(R.id.rectangulo_cita);

                            if ("STARTED".equals(operationalOrderAchievement.getStatus())) {
                                // Obtener el drawable del background del View
                                GradientDrawable drawable = (GradientDrawable) cita_rectangulo.getBackground();
                                // Cambiar el color del drawable
                                drawable.setColor(ContextCompat.getColor(InicioActivity.this, R.color.amarillo));
                            } else {
                                // Obtener el drawable del background del View
                                GradientDrawable drawable = (GradientDrawable) cita_rectangulo.getBackground();
                                // Cambiar el color del drawable
                                drawable.setColor(ContextCompat.getColor(InicioActivity.this, R.color.rojo));
                            }
                        } else {
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

                        Log.d(TAG, "Achievement: " + achievements2.toString());
                        Log.d(TAG, "PlannedOrder: " + plannedOrders2.toString());
                        Log.d(TAG, "Order: " + orders2.toString());

                        // Actualizar el RecyclerView
                        adapter.updateData(plannedOrders2, orders2, achievements2);

                        // Actualizar la hora exacta
                        horaa.setText("Actualizado hoy a las " + hora_global);

                        // Crear un nuevo Intent
                    /*Intent newIntent = new Intent(InicioActivity.this, InicioActivity.class);
                    newIntent.putExtra("user_name", getIntent().getStringExtra("user_name"));
                    newIntent.putExtra("user_email", getIntent().getStringExtra("user_email"));
                    newIntent.putExtra("hora_exacta", hora_global);
                    newIntent.putParcelableArrayListExtra("positioning", new ArrayList<>(lastKnownPositions));
                    newIntent.putParcelableArrayListExtra("plannedOrders", new ArrayList<>(plannedOrders));
                    newIntent.putParcelableArrayListExtra("orders", new ArrayList<>(orders));
                    newIntent.putParcelableArrayListExtra("achievements", new ArrayList<>(achievementsList));
                    newIntent.putParcelableArrayListExtra("geocodes",new ArrayList<>(geocodes));
                    // Reemplazar el Intent actual con el nuevo Intent
                    setIntent(newIntent);*/

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