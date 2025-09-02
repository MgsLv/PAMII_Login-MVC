package android.miguelsoares.etimpamiimiguelloginmvc.datamodel;

import java.util.Locale;

public class UsuarioDataModel {

    public static final String TABELA = "usuarios";

    public static final String ID = "id";
    public static final String NOME = "nome";
    public static final String EMAIL = "email";
    public static final String SENHA = "senha";

    public static String criarTabela() {
        return "CREATE TABLE " + TABELA + " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NOME + " TEXT, " +
                EMAIL + " TEXT, " +
                SENHA + " TEXT)";
    }
}
