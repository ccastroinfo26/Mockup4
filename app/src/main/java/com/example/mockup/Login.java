package com.example.mockup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void botaoLogIN(View view) {

        Intent intent = new Intent(this, telaComMapa.class);
        startActivity(intent);
    }

    public void buttonLogFB(View view) {

        Intent intent = new Intent(this, TelaSobre.class);
        startActivity(intent);
    }
    public void buttonLogG (View view) {

        Intent intent = new Intent(this, telaTermos.class);
        startActivity(intent);
    }

    public void cadastrar(View view) {

        Intent intent = new Intent(this, cadastrar.class);
        startActivity(intent);
    }

}
