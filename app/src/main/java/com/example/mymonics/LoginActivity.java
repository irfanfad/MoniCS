package com.example.mymonics;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mymonics.api.APIClient;
import com.example.mymonics.api.APIInteface;
import com.example.mymonics.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mymonics.SessionManager.JABATAN;
import static com.example.mymonics.SessionManager.PREF_NAME;
import static com.example.mymonics.SessionManager.PRIVATE_MODE;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private EditText etUsername, etPassword;
    private Spinner spinner;
    private Button btnLogin;
    String jabatan;
    SessionManager sessionManager;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(this);

        etPassword = findViewById(R.id.password);
        etUsername = findViewById(R.id.username);
        btnLogin = findViewById(R.id.btnLogin);
        spinner = findViewById(R.id.spinner);

        btnLogin.setOnClickListener(this);
        spinner.setOnItemSelectedListener(this);


        sharedPreferences = getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        if (sessionManager.isLoggin()) {
            Log.d("login", String.valueOf(sessionManager.isLoggin()));
            String jabatan = sharedPreferences.getString(JABATAN, "");
            if (jabatan.equalsIgnoreCase("manager")) {
                Intent intent = new Intent(LoginActivity.this, ManagerActivity.class);
                startActivity(intent);
            } else if (jabatan.equalsIgnoreCase("cleaning service")) {
                Intent intent = new Intent(LoginActivity.this, CleaningServiceActivity.class);
                startActivity(intent);
            }
        }
    }

    @Override
    public void onClick(View v) {
        final String username = etUsername.getText().toString();
        final String password = etPassword.getText().toString();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Username dan Password Harus Diisi", Toast.LENGTH_SHORT).show();
        } else if (spinner.getSelectedItem().toString().trim().equals("Pilih Jabatan")) {
            Toast.makeText(this, "Jabatan Harus Dipilih", Toast.LENGTH_SHORT).show();
        } else {
            APIInteface apiInteface = APIClient.getApiClient().create(APIInteface.class);

            Call<User> call = apiInteface.getLogin(username, password, jabatan);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {

                    User user = response.body();
                    if (user.getMessage() != null) {
                        Toast.makeText(LoginActivity.this, user.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        if (user.getJabatan().equalsIgnoreCase("Manager")) {
                            sessionManager.createSession(user.getJabatan(), user.getNik(), user.getNama(),user.getPoint());
                            Intent intent = new Intent(LoginActivity.this, ManagerActivity.class);
                            startActivity(intent);
                        } else {
                            sessionManager.createSession(user.getJabatan(),user.getNik(),user.getNama(),user.getPoint());
                            Intent intent = new Intent(LoginActivity.this, CleaningServiceActivity.class);
                            startActivity(intent);
                        }
                    }

                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.e("Error", t.getLocalizedMessage());
                }
            });
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        jabatan = spinner.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
