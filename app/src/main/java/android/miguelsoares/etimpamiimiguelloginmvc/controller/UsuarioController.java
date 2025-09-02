package android.miguelsoares.etimpamiimiguelloginmvc.controller;

import android.content.ContentValues;
import android.content.Context;
import android.miguelsoares.etimpamiimiguelloginmvc.datasource.AppDataBase;
import android.miguelsoares.etimpamiimiguelloginmvc.datamodel.UsuarioDataModel;
import android.miguelsoares.etimpamiimiguelloginmvc.model.Usuario;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class UsuarioController extends AppDataBase implements iCrud<Usuario> {

    ContentValues dadosDoObjeto;
    public UsuarioController(Context context) {
        super(context);
    }

    @Override
    public boolean incluir(Usuario obj) {
        dadosDoObjeto = new ContentValues() ;
        dadosDoObjeto.put(UsuarioDataModel.NOME, obj.getNome() );
        dadosDoObjeto.put(UsuarioDataModel.EMAIL, obj.getEmail());
        dadosDoObjeto.put(UsuarioDataModel.SENHA, obj.getSenha());

        return insert(UsuarioDataModel.TABELA, dadosDoObjeto);
    }

    @Override
    public boolean alterar(Usuario obj) {
        return false;
    }

    @Override
    public boolean deletar(Usuario obj) {
        return false;
    }

    @Override
    public List<Usuario> listar() {
        return Collections.emptyList();
    }

    public boolean usuarioSenha(String username, String password) {
        return checkUserPassword(username, password);
    }

    public boolean usuario(String user) {
        return checkUser(user);
    }
}
