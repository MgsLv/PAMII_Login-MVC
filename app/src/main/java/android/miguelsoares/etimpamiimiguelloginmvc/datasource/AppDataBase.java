package android.miguelsoares.etimpamiimiguelloginmvc.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.miguelsoares.etimpamiimiguelloginmvc.datamodel.UsuarioDataModel;

public class AppDataBase extends SQLiteOpenHelper {
    SQLiteDatabase sqLiteDataBase;
    public static final String NAME = "app.sqlite";
    public static int version = 1;
    public AppDataBase(Context context) {
        super(context, NAME, null, version);
        sqLiteDataBase = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UsuarioDataModel.criarTabela());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insert(String tabela, ContentValues dados) {
        boolean retorno = false;

        try {
            retorno = sqLiteDataBase.insert(tabela, null, dados) > 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return retorno;
    }

    public boolean checkUserPassword(String username, String password) {
        sqLiteDataBase = getWritableDatabase();
        boolean retorno = false;
        Cursor cursor = sqLiteDataBase.rawQuery("SELECT *FROM " + UsuarioDataModel.TABELA + " WHERE username = ? " +
                " AND password = ?",
                new String[]{username, password});

        return cursor.getCount() > 0;
    }

    public boolean checkUser(String username) {
        sqLiteDataBase = getWritableDatabase();
        boolean retorno = false;
        Cursor cursor = sqLiteDataBase.rawQuery("SELECT *FROM " +
                        UsuarioDataModel.TABELA + " WHERE email = ?",
                new String[]{username});

        return cursor.getCount() > 0;
    }
}
