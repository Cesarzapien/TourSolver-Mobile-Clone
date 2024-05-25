package com.cesar.toursolvermobile2;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.cesar.toursolvermobile2.DB.DBHelper;
import com.cesar.toursolvermobile2.DB.User;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public class Login extends AppCompatActivity {

    //private static final String BASE_URL = "https://api.geoconcept.com/tsapi/";
    //private static final String API_KEY = "9e313fb763515473";
    //private static final String ACCEPT = "application/json";

    TextInputLayout correo, contrasenia;
    CheckBox rememberMeCheckBox;

    /*public interface ApiService {
        @GET("fulfillment")
        Call<ApiResponse> getFulfillment(
                @Header("tsCloudApiKey") String apiKey,
                @Header("Accept") String accept,
                @Query("endDate") String endDate,
                @Query("startDate") String startDate,
                @Query("userLogin") String userLogin
        );
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoadingAlert loadingAlert = new LoadingAlert(Login.this);

        correo = findViewById(R.id.etCorreo);
        contrasenia = findViewById(R.id.etPassword);
        rememberMeCheckBox = findViewById(R.id.checkBox2);

        // Cargar credenciales guardadas si existen
        loadCredentials();

        // Obtener referencia al botón
        Button button = findViewById(R.id.button);

        // Configurar el OnClickListener para el botón
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el correo y la contraseña del EditText
                String userLogin = correo.getEditText().getText().toString();
                String password = contrasenia.getEditText().getText().toString();

                // Validar si los campos están vacíos
                if (userLogin.isEmpty()) {
                    correo.setError("Por favor, ingrese el correo");
                    return;
                } else {
                    correo.setError(null); // Limpiar el error
                }

                if (password.isEmpty()) {
                    contrasenia.setError("Por favor, ingrese la contraseña");
                    return;
                } else {
                    contrasenia.setError(null); // Limpiar el error
                }
                loadingAlert.startAlertDialog();

                // Crear una instancia de DBHelper
                DBHelper dbHelper = new DBHelper(Login.this);

                // Verificar las credenciales del usuario
                if (dbHelper.checkUserCredentials(userLogin, password)) {
                    loadingAlert.closeAlertDialog();
                    // Guardar las credenciales si el checkbox está activado
                    if (rememberMeCheckBox.isChecked()) {
                        saveCredentials(userLogin, password);
                    } else {
                        clearCredentials();
                    }

                    // Obtener los detalles del usuario
                    User user = dbHelper.getUser(userLogin);
                    if (user != null) {
                        String fullName = user.getFirstName() + " " + user.getLastName();
                        Intent intent = new Intent(Login.this, InicioActivity.class);
                        intent.putExtra("user_name", fullName);
                        intent.putExtra("user_email", user.getEmail());
                        startActivity(intent);
                        finish();
                    }
                } else {
                    loadingAlert.closeAlertDialog();
                    // Mostrar un Toast si las credenciales son incorrectas
                    Toast.makeText(Login.this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }

                // Crear una instancia de Retrofit
                /*Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                // Crear una instancia del servicio de la API
                ApiService apiService = retrofit.create(ApiService.class);

                // Realizar la llamada a la API
                Call<ApiResponse> call = apiService.getFulfillment(
                        API_KEY,
                        ACCEPT,
                        "2024-05-18T00:00:00",
                        "2024-05-17T00:00:00",
                        userLogin
                );

                // Ejecutar la llamada de manera asíncrona
                call.enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            ApiResponse apiResponse = response.body();

                            // Imprimir la respuesta en el log
                            Log.d(TAG, "API Response: " + apiResponse.toString());

                            // Verificar la respuesta
                            if ("ERROR".equals(apiResponse.getStatus())) {
                                loadingAlert.closeAlertDialog();
                                // Mostrar un Toast si el correo es incorrecto
                                Toast.makeText(Login.this, "Correo incorrecto", Toast.LENGTH_SHORT).show();
                            } else {
                                // Iniciar MainActivity si la respuesta es válida
                                loadingAlert.closeAlertDialog();
                                Intent intent = new Intent(Login.this, InicioActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        } else {
                            Log.e(TAG, "Error en la respuesta de la API: " + response.errorBody());
                            Toast.makeText(Login.this, "Error en la respuesta de la API", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse> call, Throwable t) {
                        Log.e(TAG, "Error en la llamada a la API", t);
                        Toast.makeText(Login.this, "Error en la llamada a la API", Toast.LENGTH_SHORT).show();
                    }
                });*/
            }
        });

    }

    private void saveCredentials(String email, String password) {
        SharedPreferences sharedPreferences = getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", email);
        editor.putString("password", password);
        editor.putBoolean("rememberMe", true);
        editor.apply();
    }

    private void clearCredentials() {
        SharedPreferences sharedPreferences = getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("email");
        editor.remove("password");
        editor.putBoolean("rememberMe", false);
        editor.apply();
    }

    private void loadCredentials() {
        SharedPreferences sharedPreferences = getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE);
        boolean rememberMe = sharedPreferences.getBoolean("rememberMe", false);
        if (rememberMe) {
            String email = sharedPreferences.getString("email", "");
            String password = sharedPreferences.getString("password", "");
            correo.getEditText().setText(email);
            contrasenia.getEditText().setText(password);
            rememberMeCheckBox.setChecked(true);
        }

    }
}