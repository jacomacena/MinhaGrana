package br.com.grupoprojetar.minhagrana.auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import br.com.grupoprojetar.minhagrana.Login;
import br.com.grupoprojetar.minhagrana.MainActivity;
import br.com.grupoprojetar.minhagrana.R;

public class Cadastro extends AppCompatActivity {

    EditText emailReg, passReg;
    Button btnReg, btnVoltar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        incializaViews();

        acoesClicks();

    }

    private void acoesClicks() {

        //REGISTRO
        btnReg.setOnClickListener(v -> {
            //recuperando dados passado pelo usuario
            String email = emailReg.getText().toString().trim();
            String senha = passReg.getText().toString().trim();

            criarUser(email, senha);
        });

        //VOLTAR
        btnVoltar.setOnClickListener(v -> {
            Intent back = new Intent(getApplicationContext(), Login.class);
            startActivity(back);
        });
    }

    private void criarUser(String email, String senha) {
        mAuth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(Cadastro.this, task -> {
                    if (task.isSuccessful()) {
                        logs("Usuario criado com sucesso! :D");
                        Intent a = new Intent(Cadastro.this, MainActivity.class);
                        startActivity(a);
                        finish();
                    } else {
                        logs("Erro ao cadastrar");
                    }
                });
    }

    private void logs(String msg) {
        Toast.makeText(Cadastro.this, msg, Toast.LENGTH_LONG).show();
    }

    //inicializacao de toda a estrutura da tela (cadastro.xml)
    private void incializaViews() {
        emailReg = findViewById(R.id.emailRegistro);
        passReg = findViewById(R.id.senhaRegistro);
        btnReg = findViewById(R.id.btnRegistro);
        btnVoltar = findViewById(R.id.btnVoltar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth = Conexao.getFirebaseAuth();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
