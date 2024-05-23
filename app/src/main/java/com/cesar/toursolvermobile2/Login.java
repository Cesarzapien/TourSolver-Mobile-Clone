package com.cesar.toursolvermobile2;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

    private static final String BASE_URL = "https://api.geoconcept.com/tsapi/";
    private static final String API_KEY = "9e313fb763515473";
    private static final String ACCEPT = "application/json";

    TextInputLayout correo, contraseña;

    public interface ApiService {
        @GET("fulfillment")
        Call<ApiResponse> getFulfillment(
                @Header("tsCloudApiKey") String apiKey,
                @Header("Accept") String accept,
                @Query("endDate") String endDate,
                @Query("startDate") String startDate,
                @Query("userLogin") String userLogin
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoadingAlert loadingAlert = new LoadingAlert(Login.this);

        correo = findViewById(R.id.etCorreo);
        contraseña = findViewById(R.id.etPassword);

        // Obtener referencia al botón
        Button button = findViewById(R.id.button);

        // Configurar el OnClickListener para el botón
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingAlert.startAlertDialog();
                // Obtener el correo y la contraseña del EditText
                String userLogin = correo.getEditText().getText().toString();
                String password = contraseña.getEditText().getText().toString();

                // Validar si los campos están vacíos
                if (userLogin.isEmpty()) {
                    correo.setError("Por favor, ingrese el correo");
                    return;
                } else {
                    correo.setError(null); // Limpiar el error
                }

                if (password.isEmpty()) {
                    contraseña.setError("Por favor, ingrese la contraseña");
                    return;
                } else {
                    contraseña.setError(null); // Limpiar el error
                }

                // Crear una instancia de Retrofit
                Retrofit retrofit = new Retrofit.Builder()
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
                                // Mostrar un Toast si el correo es incorrecto
                                Toast.makeText(Login.this, "Correo incorrecto", Toast.LENGTH_SHORT).show();
                            } else {
                                // Iniciar MainActivity si la respuesta es válida
                                Intent intent = new Intent(Login.this, InicioActivity.class);
                                startActivity(intent);
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
                });
            }
        });
    }
}