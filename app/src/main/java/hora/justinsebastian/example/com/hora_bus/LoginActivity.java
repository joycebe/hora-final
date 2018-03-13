package hora.justinsebastian.example.com.hora_bus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity implements  View.OnClickListener{
    private FirebaseAuth mAuth;
    private EditText inputEmail, inputPassword;
    private ProgressBar progressBar;
    private Button  btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser()!=null)
        {
            //profile activity
            FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            String email1="admin@gmail.com";
            if(email1.contentEquals(currentFirebaseUser.getEmail()))
            {
                finish();
                startActivity(new Intent(getApplicationContext(),AdminprofileActivity.class));
            }
            else
            {
                finish();
                startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
            }

        }
        setContentView(R.layout.activity_login);
        inputEmail = findViewById(R.id.email);
        inputPassword = findViewById(R.id.password);
        progressBar = findViewById(R.id.progressBar);

        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);

    }
    public void uselogin(View view) {
        String email = inputEmail.getText().toString();
        final String password = inputPassword.getText().toString();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //start profile
                            finish();
                            FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                            //Toast.makeText(getApplicationContext(), "" + currentFirebaseUser.getEmail(), Toast.LENGTH_SHORT).show();
                            String email1="admin@gmail.com";
                            Toast.makeText(getApplicationContext(), "" + currentFirebaseUser.getEmail(), Toast.LENGTH_SHORT).show();
                            if(email1.contentEquals(currentFirebaseUser.getEmail()))
                            {
                                //Toast.makeText(getApplicationContext(), "test 1" , Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), AdminprofileActivity.class));
                            }
                            else {
                                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                            }
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Invalid email or password!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });
    }
    @Override
    public void onClick(View view){
        if(view == btnLogin)
        {
            uselogin( view);
        }
    }
}
