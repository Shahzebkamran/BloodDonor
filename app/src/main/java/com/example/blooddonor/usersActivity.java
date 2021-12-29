package com.example.blooddonor;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;


public class usersActivity extends AppCompatActivity {
    DbHelper db;
    ArrayList<User> users;
    EditText Name,Location,BloodGroup,Status;
    ArrayList<Hashtable<String,String>> data;
    RecyclerView  donersList;
    FloatingActionButton call;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        donersList = (RecyclerView) findViewById(R.id.donersList);
        donersList.setLayoutManager(new LinearLayoutManager(this));
        call = findViewById(R.id.floatingActionButton2);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:03202434372"));
                startActivity(intent);
            }
        });

        db = new DbHelper(usersActivity.this);
        users = new ArrayList<User>();
        displayFirebase();
        donersList.setAdapter(new donerAdapter(users));

    }

    void displayFirebase(){
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data = new ArrayList<Hashtable<String,String>>();
                for(DataSnapshot snapshot1: snapshot.getChildren() ){
                    GenericTypeIndicator<HashMap<String,Object>> type = new GenericTypeIndicator<HashMap<String, Object>>() {};
                    HashMap<String,Object> map =  snapshot1.getValue(type);
                    Hashtable<String,String> obj = new Hashtable<String,String>();
                    for(String key : map.keySet()){
                        obj.put(key,map.get(key).toString());
                    }
                    data.add(obj);
                    for (String User: map.keySet()){
                        String Name = snapshot1.child(User).child("name").getValue().toString();
                        String BloodGroup = snapshot1.child(User).child("bloodGroup").getValue().toString();
                        String Location = snapshot1.child(User).child("location").getValue().toString();
                        String Status = snapshot1.child(User).child("status").getValue().toString();
                        User tempUser = new User(Name,Location,Status,BloodGroup);
                        users.add(tempUser);
                    }
                }
                donersList.setAdapter(new donerAdapter(users));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.print(error.getMessage());
            }
        });

    }
    void displayData(){
//        displayFirebase();
        Cursor cursor = db.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
              User tempUser = new User(cursor.getString(1),cursor.getString(2),cursor.getString(4),cursor.getString(3) );
              users.add(tempUser);
            }
        }
    }

}
