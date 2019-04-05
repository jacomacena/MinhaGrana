package br.com.grupoprojetar.minhagrana;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
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
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString().trim();
                String senha = editSenha.getText().toString().trim();



                login(email,senha);
            }
        });

        //CADASTRO
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getApplicationContext(), Cadastro.class);
                startActivity(a);
            }
        });

        //RESET
        txtResetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(Login.this, ResetPass.class);
                startActivity(a);
            }
        });

    }

    private void login(String email, String senha) {
        mAuth.signInWithEmailAndPassword(email,senha)
                .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Intent a = new Intent(Login.this, MainActivity.class);
                            startActivity(a);
                        }else {
                            logs("Email e/ou Senha incorretos! :(");
                        }
                    }
                });
    }

    private void logs(String s) {
        Toast.makeText(Login.this,s,Toast.LENGTH_LONG).show();
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
