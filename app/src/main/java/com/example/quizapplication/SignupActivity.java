package com.example.quizapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

public class SignupActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText username,password1,password2;
    TextView   loginLink;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

//initialize textview which re-directs to signIn  activity
        loginLink=findViewById(R.id.text);
        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignupActivity.this, LoginScreenActivity.class);
                    startActivity(intent);
                    finish();
            }
        });

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();


        //creating object for edittext and buttons
        username=findViewById(R.id.userid);
        password1=findViewById(R.id.password);
        password2=findViewById(R.id.cnfpassword);
        login=findViewById(R.id.login);

        //onclick listener
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String  username1=username.getText().toString();
                String passwordFirst=password1.getText().toString();
                String passwordSecond=password2.getText().toString();

                if(TextUtils.isEmpty(username1)) {
                    Toast.makeText(getApplicationContext(),"Username should not be empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(passwordSecond)) {
                    Toast.makeText(getApplicationContext(),"Password should not be empty",Toast.LENGTH_SHORT).show();
                return;
                }
                if(!passwordFirst.equals(passwordSecond))
                {
                    Toast.makeText(getApplicationContext(),"Entered Password is not equal to confirm password",Toast.LENGTH_SHORT).show();
               return;
                }


                mAuth.createUserWithEmailAndPassword(username1, passwordSecond)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                        Toast.makeText(getApplicationContext(),"Registered Successfully",Toast.LENGTH_SHORT).show();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(getApplicationContext(),"Registration Failed",Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
}
});
    }
}