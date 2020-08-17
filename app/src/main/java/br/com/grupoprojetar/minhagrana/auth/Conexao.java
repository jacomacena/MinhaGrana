package br.com.grupoprojetar.minhagrana.auth;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Conexao {

    private static FirebaseAuth firebaseAuth;
    private static FirebaseUser firebaseUser;

    private Conexao() {
    }

    //inicializacao do login do firebase
    public static FirebaseAuth getFirebaseAuth() {
        if (firebaseAuth == null) {
            inicializaFirebaseAuth();
        }
        return firebaseAuth;
    }

    // metodo que verifica se o usuario esta cadastrado na base do firebase
    private static void inicializaFirebaseAuth() {
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseAuth.AuthStateListener authStateListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                firebaseUser = user;
            }
        };

        firebaseAuth.addAuthStateListener(authStateListener);
    }

    // fornece o usuario que esta logado
    public static FirebaseUser getFirebaseUser() {
        return firebaseUser;
    }

    // saida do usuario (logout)
    public static void logOut() {
        firebaseAuth.signOut();
    }
}
