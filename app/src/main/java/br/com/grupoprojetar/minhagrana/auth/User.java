package br.com.grupoprojetar.minhagrana.auth;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import java.util.Map;

public class User {

    private String id;
    private String name;
    private String email;
    private String password;

    private User() {
    }
    //GET/SET ID
    private String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    //GET/SET NOME
    private String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setNameInMap(Map<String, Object> map) {
        if (getName() != null) {
            map.put("name", getName());
        }
    }

    private void setNameIfNull(String name) {
        if (this.name == null) {
            this.name = name;
        }
    }

    //GET/SET EMAIL
    private String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    private void setEmailInMap(Map<String, Object> map) {
        if (getEmail() != null) {
            map.put("email", getEmail());
        }
    }

    private void setEmailIfNull(String email) {
        if (this.email == null) {
            this.email = email;
        }
    }

    //GET/SET SENHA
    @Exclude
    private String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }


    private void saveDB(DatabaseReference.CompletionListener... completionListener) {
        DatabaseReference firebase = LibraryClass.getFirebase().child("users").child(getId());

        if (completionListener.length == 0) {
            firebase.setValue(this);
        } else {
            firebase.setValue(this, completionListener[0]);
        }
    }
}
