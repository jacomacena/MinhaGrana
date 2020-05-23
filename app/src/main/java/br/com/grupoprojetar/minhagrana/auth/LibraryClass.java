package br.com.grupoprojetar.minhagrana.auth;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

final class LibraryClass {
    private static DatabaseReference firebase;

    private LibraryClass(){}

    static DatabaseReference getFirebase(){
        if( firebase == null ){
            firebase = FirebaseDatabase.getInstance().getReference();
        }

        return( firebase );
    }
}