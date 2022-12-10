package com.example.designotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        EditText signupEmail = (EditText) findViewById(R.id.signupEmail);
        EditText signupUsername = (EditText) findViewById(R.id.signupUsername);
        EditText signupPassword = (EditText) findViewById(R.id.signupPassword);
        EditText signupConfirmPassword = (EditText) findViewById(R.id.signupConfirmPassword);
        Button signupBtn = (Button) findViewById(R.id.signupBtn);

        signupBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String dbURL = getString(R.string.dbURL);
                        FirebaseDatabase database = FirebaseDatabase.getInstance(dbURL);
                        DatabaseReference myRef = database.getReference("users");

                        String email = signupEmail.getText().toString();
                        String username = signupUsername.getText().toString();
                        String password = signupPassword.getText().toString();
                        String confirmPassword = signupConfirmPassword.getText().toString();

                        User user = new User(email, username, password);

                        String id = myRef.push().getKey();
                        myRef.child(id).setValue(user);

                        Toast.makeText(getApplicationContext(), "added", Toast.LENGTH_LONG).show();
                    }
                }
        );

    }
}