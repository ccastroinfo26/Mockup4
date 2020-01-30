package com.example.mockup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class testesDeApi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testes_de_api);

        //Lista a ser exibida na tela
        ListView list = findViewById(R.id.listViewTesteApi);
        //Lista que será retornada da API
        ArrayList<Posts> retorno = null;

        try {
            retorno = new HttpRequisition().execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (retorno != null) {
            ArrayAdapter<Posts> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, retorno);
            list.setAdapter(adapter);
            Log.i("sucesso", "A lista foi exibida.");
        } else {
            Log.i("Erro", "A lista de retorno da API está vazia");
        }
    }
}
