package com.example.mockup;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class BancoController {
    private SQLiteDatabase db;
    private criaBanco banco;

    public BancoController(Context context) {
        banco = new criaBanco(context);
    }

    public String insereDado(String usuario, String email, String senha) {
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(criaBanco.USUARIO, usuario);
        valores.put(criaBanco.EMAIL, email);
        valores.put(criaBanco.SENHA, senha);

        resultado = db.insert(criaBanco.TABELA, null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir registro";
        else
            return "Registro inserido com sucesso";
    }
}
