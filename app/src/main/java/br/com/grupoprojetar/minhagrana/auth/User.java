package br.com.grupoprojetar.minhagrana.auth;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.util.Map;

public class User {

    private String id;
    private String name;
    private String email;
    private String password;

    public User() {
    }
    //GET/SET ID
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //GET/SET NOME
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private void setNameInMap(Map<String, Object> map) {
        if (getName() != null) {
            map.put("name", getName());
        }
    }

    public void setNameIfNull(String name) {
        if (this.name == null) {
            this.name = name;
        }
    }

    //GET/SET EMAIL
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private void setEmailInMap(Map<String, Object> map) {
        if (getEmail() != null) {
            map.put("email", getEmail());
        }
    }

    public void setEmailIfNull(String email) {
        if (this.email == null) {
            this.email = email;
        }
    }

    //GET/SET SENHA
    @Exclude
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void saveDB(DatabaseReference.CompletionListener... completionListener) {
        DatabaseReference firebase = LibraryClass.getFirebase().child("users").child(getId());

        if (completionListener.length == 0) {
            firebase.setValue(this);
        } else {
            firebase.setValue(this, completionListener[0]);
        }
    }
}
