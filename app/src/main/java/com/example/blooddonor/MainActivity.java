package com.example.blooddonor;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button signUp;
    EditText Name,Location,BloodGroup,Status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        signUp = findViewById(R.id.signup_button);
        Name = findViewById(R.id.Name);
        Location = findViewById(R.id.location);
        BloodGroup = findViewById(R.id.BloodGroup);
        Status = findViewById(R.id.Status);
        DAOUsers dao = new DAOUsers();
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //A2
//                DbHelper db = new DbHelper(MainActivity.this);
//                db.addUsers(Name.getText().toString().trim(),Location.getText().toString().trim()
//                        ,Status.getText().toString().trim(),BloodGroup.getText().toString().trim());
//                Intent intent = new Intent(MainActivity.this,usersActivity.class);
//                startActivity(intent);
                //A3
                User user = new User(Name.getText().toString().trim(),Location.getText().toString().trim()
                        ,Status.getText().toString().trim(),BloodGroup.getText().toString().trim());
                dao.addUser(user).addOnSuccessListener(suc -> {
                    Toast.makeText(MainActivity.this,"Record is inserted",Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(fail-> {
                    Toast.makeText(MainActivity.this,""+fail.getMessage(),Toast.LENGTH_SHORT).show();
                });
                Intent intent = new Intent(MainActivity.this,usersActivity.class);
                startActivity(intent);
            }
        }
        );
    }
}