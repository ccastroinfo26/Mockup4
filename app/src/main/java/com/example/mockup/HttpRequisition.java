package com.example.mockup;

import android.os.AsyncTask;
import android.os.Build;
import android.util.Base64;
import android.util.JsonReader;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.mockup.Posts;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class HttpRequisition extends AsyncTask<Void, Void, ArrayList<Posts>> {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override

    protected ArrayList<Posts> doInBackground(Void... voids) {
        try {
            //Create URL
            URL myApiEndPoint = new URL("https://app-subsea-homolog.wideds.com.br/wp-json/wp/v2/posts");
            //Autenticação
            // "Basic " (sem aspas) é obrigatório no inicio, depois é uma chave codificada em Base64*/
            String basicAuth = "Basic NzI5MDIxNzUwMDA6MTIzNDU2";

            //Create connection
            HttpsURLConnection myConnection = (HttpsURLConnection) myApiEndPoint.openConnection();

            // Inserir cabeçalho User-Agent e outros

            myConnection.setRequestProperty("Authorization", basicAuth);
            myConnection.setRequestMethod("GET");
            myConnection.setRequestProperty("Content-Type", "application/json");

            // Lista a ser retornada com todos os posts da API
            ArrayList<Posts> listPosts = new ArrayList<>();

            //Verifica API
            if (myConnection.getResponseCode() == 200) {
                Log.i("API", "Conectou com sucesso");
                InputStream responseBody = myConnection.getInputStream();
                InputStreamReader responseBodyHeader = new InputStreamReader(responseBody, StandardCharsets.UTF_8);

                //Transforma de InputStreamReader em String
                Scanner sc = new Scanner(responseBodyHeader);
                String data = null;
                while (sc.hasNext()) {
                    data = sc.nextLine();
                }

                JSONArray jsonArray = new JSONArray(data);
                for (int i = 0; i < jsonArray.length(); i++) {
                    //Obtém cada objeto de acordo com o index i
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    int id = jsonObject.getInt("id");

                    JSONObject objTitle = jsonObject.getJSONObject("title");
                    JSONArray arrTitle = objTitle.toJSONArray(objTitle.names());
                    assert arrTitle != null;
                    String title = arrTitle.getString(0);

                    JSONObject obgExcerpt = jsonObject.getJSONObject("excerpt");
                    JSONArray arrExcerpt = obgExcerpt.toJSONArray(obgExcerpt.names());
                    assert arrExcerpt != null;
                    String excerpt = arrExcerpt.getString(0);

                    String author = jsonObject.getString("author");

                    int comments_total = jsonObject.getInt("comments_total");

                    Posts posts = new Posts(id, title, excerpt, author, comments_total);
                    listPosts.add(posts);
                }
                // Retorna mesmo que não haja nada, portanto o tratamento é necessário ao chamar o método
                return listPosts;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }// Se houver alguma exceção, retornará null
        return null;
    }
}

