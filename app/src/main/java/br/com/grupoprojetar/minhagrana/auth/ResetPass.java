package br.com.grupoprojetar.minhagrana.auth;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import br.com.grupoprojetar.minhagrana.R;

public class ResetPass extends AppCompatActivity {

    private EditText edtEmail;
    private Button btnResetPass;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);

        iniciarViews();

        acaoClick();
    }

    private void acaoClick() {
        btnResetPass.setOnClickListener(v -> {
            String email = edtEmail.getText().toString().trim();
            resetSenha(email);
        });
    }

    private void resetSenha(String email) {
        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(ResetPass.this, task -> {
                    if (task.isSuccessful()){
                        logs("Email de reset de senha enviado!");
                        finish();
                    }else {
                        logs("Email n�o encontrado! :(");
                    }
                });
    }

    private void logs(String s) {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

    private void iniciarViews() {
        edtEmail = findViewById(R.id.txtResetEmail);
        btnResetPass = findViewById(R.id.btnResetPass);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth = Conexao.getFirebaseAuth();
    }
}
