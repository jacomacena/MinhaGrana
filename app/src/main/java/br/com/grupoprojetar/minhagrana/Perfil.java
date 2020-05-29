package br.com.grupoprojetar.minhagrana;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.com.grupoprojetar.minhagrana.auth.Conexao;

public class Perfil extends AppCompatActivity {

    private TextView txtEmail, txtID;
    private Button btnExit;

    private FirebaseAuth mAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        iniciaViews();

        acaoClick();

    }

    private void acaoClick() {
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Conexao.logOut();
                finish();
            }
        });
    }

    private void iniciaViews() {
        txtEmail = findViewById(R.id.txtPerfilEmail);
        txtID = findViewById(R.id.txtPerfilID);
        btnExit = findViewById(R.id.btnPerfilExit);
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth = Conexao.getFirebaseAuth();
        user = Conexao.getFirebaseUser();

        validaUser();
    }

    private void validaUser() {
        if (user == null){
            finish();
        }else{
            txtEmail.setText(user.getEmail());
            txtID.setText(user.getUid());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
