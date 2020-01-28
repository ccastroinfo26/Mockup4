package com.example.mockup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mockup.model.CEP;
import com.example.mockup.service.HttpService;

import java.util.concurrent.ExecutionException;

public class CEPActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cep);

        final EditText cep = findViewById(R.id.etCEP);
        final TextView resposta = findViewById(R.id.exibeCEP);

        Button btnBuscarCep = findViewById(R.id.btnBuscaCEP);

        btnBuscarCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cep.getText().toString().length() > 0 && !cep.getText().toString().equals("")
                        && cep.getText().toString().length() == 8) {
                    HttpService service = new HttpService(cep.getText().toString());
                    try {
                        //sempre referenciar corretamente a classe
                        CEP retorno = service.execute().get();
                        resposta.setText(retorno.toString());
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        });
    }
}

