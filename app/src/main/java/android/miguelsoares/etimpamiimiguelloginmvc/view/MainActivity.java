package android.miguelsoares.etimpamiimiguelloginmvc.view;

import android.miguelsoares.etimpamiimiguelloginmvc.R;
import android.miguelsoares.etimpamiimiguelloginmvc.controller.UsuarioController;
import android.miguelsoares.etimpamiimiguelloginmvc.model.Usuario;
import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    UsuarioController controller;
    Usuario usuario;
    Button cadastrar, entrar;
    EditText nomeUsuario, emailUsuario, senhaUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        initComponents();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        entrar.setOnClickListener(view -> {
            if ( validaCampos() ) {
                usuario = new Usuario();
                String nome  = nomeUsuario.getText().toString();
                String email = emailUsuario.getText().toString();
                String senha = senhaUsuario.getText().toString();

                usuario.setNome(nome);
                usuario.setEmail(email);
                usuario.setSenha(senha);

                controller = new UsuarioController(this);

                boolean isCheckUser = controller.checkUser( email );
                boolean isCheckPass = controller.checkUserPassword( email, senha );
                if ( isCheckUser && isCheckPass ) {
                    Toast.makeText(this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Usuário ou senha incorretos!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validaCampos() {
        boolean camposValidados = false;
        if( nomeUsuario.getText().toString().isEmpty() ||
            emailUsuario.getText().toString().isEmpty() ||
            senhaUsuario.getText().toString().isEmpty() ) {
            camposValidados = false;
        } else {
            camposValidados = true;
        }
        return camposValidados;
    }

    private void initComponents() {
        nomeUsuario  = findViewById(R.id.nome);
        emailUsuario = findViewById(R.id.email);
        senhaUsuario = findViewById(R.id.senha);
        cadastrar    = findViewById(R.id.cadastrar);
        entrar       = findViewById(R.id.entrar);
    }
}