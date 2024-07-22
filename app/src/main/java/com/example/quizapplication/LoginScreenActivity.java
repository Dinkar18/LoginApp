package com.example.quizapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginScreenActivity extends AppCompatActivity {
TextView textView;
EditText username1,password1;
Button login;
    private FirebaseAuth mAuth;
    @Override
  protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_screen);

        //initialization of FireBase
        mAuth=FirebaseAuth.getInstance();

        //initialize
        textView=findViewById(R.id.textvie);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginScreenActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
        //initialize edittext and button
        username1=findViewById(R.id.userid);
        password1=findViewById(R.id.password);
        login=findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Initialize Firebase Auth
                mAuth = FirebaseAuth.getInstance();


                //creating object for edittext and buttons
                username1=findViewById(R.id.userid);
                password1=findViewById(R.id.password);
                login=findViewById(R.id.login);

                //onclick listener
                login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String  usernameNew=username1.getText().toString();
                        String passwordNew=password1.getText().toString();

                        if(TextUtils.isEmpty(usernameNew)) {
                            Toast.makeText(getApplicationContext(),"Email id should not be empty",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(TextUtils.isEmpty(passwordNew)) {
                            Toast.makeText(getApplicationContext(),"Password should not be empty",Toast.LENGTH_SHORT).show();
                            return;
                        }
                            mAuth.signInWithEmailAndPassword(usernameNew, passwordNew)
                                    .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                // Sign in success, update UI with the signed-in user's information
                                                Toast.makeText(LoginScreenActivity.this, "Authentication successfull.",
                                                        Toast.LENGTH_SHORT).show();
                                                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                                                startActivity(intent);

                                            } else {
                                                // If sign in fails, display a message to the user.
                                                Toast.makeText(LoginScreenActivity.this, "Authentication failed.",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
        }
    });
}
    });
    }
}

