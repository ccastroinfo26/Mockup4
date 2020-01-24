package com.example.mockup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import static com.example.mockup.criaBanco.EMAIL;
import static com.example.mockup.criaBanco.SENHA;
import static com.example.mockup.criaBanco.TABELA;

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

        resultado = db.insert(TABELA, null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir registro";
        else
            return "Registro inserido com sucesso";
    }

    public Cursor fazerLogin(String email, String senha) {
        db = banco.getWritableDatabase();
        String sql = "SELECT* FROM " + TABELA + " WHERE " + EMAIL + "= ? AND " + SENHA + "=?";
        String[] selectionArgs = new String[]{email, senha};
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        if (cursor != null) {
            cursor.moveToFirst();
            return cursor;
        } else {
            return null;
        }
    }

}
