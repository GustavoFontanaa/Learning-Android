package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnLogin;
    private CheckBox checkboxRemember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        checkboxRemember = findViewById(R.id.checkboxRemember);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                // Verifique se o nome de usuário e a senha são válidos
                if (username.equals("superman") && password.equals("loislane")) {
                    // Nome de usuário e senha válidos, redirecione para a tela de sucesso
                    Intent intent = new Intent(LoginActivity.this, WatchMoviesActivity.class);
                    startActivity(intent);
                    finish(); // Encerre a atividade de login
                } else {
                    // Exiba uma mensagem de erro
                    // Por exemplo: Toast.makeText(LoginActivity.this, "Nome de usuário ou senha inválidos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
