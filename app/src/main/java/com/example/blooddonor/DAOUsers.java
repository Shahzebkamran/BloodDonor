package com.example.blooddonor;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOUsers {
    private DatabaseReference dbRefrence;
    public DAOUsers(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        dbRefrence = db.getReference("User");
    }
    public Task<Void> addUser(User user){
       return dbRefrence.push().setValue(user);
    }
}
