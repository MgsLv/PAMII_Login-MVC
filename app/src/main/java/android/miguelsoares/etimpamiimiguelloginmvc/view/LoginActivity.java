package android.miguelsoares.etimpamiimiguelloginmvc.view;

import android.content.Intent;
import android.miguelsoares.etimpamiimiguelloginmvc.R;
import android.miguelsoares.etimpamiimiguelloginmvc.controller.UsuarioController;
import android.miguelsoares.etimpamiimiguelloginmvc.model.Usuario;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {
    UsuarioController controller;
    Usuario usuario;
    Button cadastrar, entrar;
    EditText emailUsuario, senhaUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        initComponents();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(intent);
            }
        });

        entrar.setOnClickListener(view -> {
            if ( validaCampos() ) {
                usuario = new Usuario();
                String email = emailUsuario.getText().toString();
                String senha = senhaUsuario.getText().toString();

                usuario.setEmail(email);
                usuario.setSenha(senha);

                controller = new UsuarioController(this);

                boolean isCheckUser = controller.checkUser( email );
                boolean isCheckPass = controller.checkUserPassword( email, senha );
                if ( isCheckUser && isCheckPass ) {
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
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
        if( emailUsuario.getText().toString().isEmpty() ||
            senhaUsuario.getText().toString().isEmpty() ) {
            camposValidados = false;
        } else {
            camposValidados = true;
        }
        return camposValidados;
    }

    private void initComponents() {
        emailUsuario = findViewById(R.id.email);
        senhaUsuario = findViewById(R.id.senha);
        cadastrar    = findViewById(R.id.cadastrar);
        entrar       = findViewById(R.id.entrar);
    }
}