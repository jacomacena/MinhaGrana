package br.com.grupoprojetar.minhagrana;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import br.com.grupoprojetar.minhagrana.auth.Cadastro;
import br.com.grupoprojetar.minhagrana.auth.Conexao;
import br.com.grupoprojetar.minhagrana.auth.ResetPass;

public class Login extends AppCompatActivity {

    private Button btnCadastrar, btnLogin;
    private EditText editEmail, editSenha;
    private TextView txtResetPass;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inicializarViews();

        acoesClicks();

    }

    //acoes dos botoes
    private void acoesClicks() {

        //LOGIN
        btnLogin.setOnClickListener(v -> {
            String email = editEmail.getText().toString().trim();
            String senha = editSenha.getText().toString().trim();

            login(email, senha);
        });

        //CADASTRO
        btnCadastrar.setOnClickListener(v -> {
            Intent a = new Intent(getApplicationContext(), Cadastro.class);
            startActivity(a);
        });

        //RESET
        txtResetPass.setOnClickListener(v -> {
            Intent a = new Intent(Login.this, ResetPass.class);
            startActivity(a);
        });

    }

    private void login(String email, String senha) {
        mAuth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(Login.this, task -> {
                    if (task.isSuccessful()) {
                        Intent a = new Intent(Login.this, MainActivity.class);
                        startActivity(a);
                    } else {
                        logs();
                    }
                });
    }

    private void logs() {
        Toast.makeText(Login.this, "Email e/ou Senha incorretos! :(", Toast.LENGTH_LONG).show();
    }

    //inicializacao de toda a estrutura da tela (login.xml)
    private void inicializarViews() {
        editEmail = findViewById(R.id.lgNome);
        editSenha = findViewById(R.id.lgPass);
        btnCadastrar = findViewById(R.id.btnCriar);
        btnLogin = findViewById(R.id.btnEntrar);
        txtResetPass = findViewById(R.id.txtReset);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth = Conexao.getFirebaseAuth();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
