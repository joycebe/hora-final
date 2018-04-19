package hora.justinsebastian.example.com.hora_bus;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Register extends AppCompatActivity implements  View.OnClickListener{
    private FirebaseAuth mAuth;
    private EditText inputEmail, inputPassword,inputname;
    protected Spinner spinner;
    //private ProgressBar progressBar;
    private Button register;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_register);
        inputEmail = findViewById(R.id.email);
        inputPassword = findViewById(R.id.password);
        inputname = findViewById(R.id.bname);

        spinner= (Spinner)findViewById( R.id.spinner);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Register.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.types));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myAdapter);
       // progressBar = findViewById(R.id.progressBar);



        register = findViewById(R.id.register);
        register.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == register) {
            String email = inputEmail.getText().toString();
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                return;
            }
            final String password = inputPassword.getText().toString();
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(getApplicationContext(), "Enter valid password!", Toast.LENGTH_SHORT).show();
                return;
            }
            final String bname = inputname.getText().toString();
            if (TextUtils.isEmpty(bname)) {
                Toast.makeText(getApplicationContext(), "Enter bus name!", Toast.LENGTH_SHORT).show();
                return;
            }
            final String type=spinner.getSelectedItem().toString();
            if (TextUtils.isEmpty(type)) {
                Toast.makeText(getApplicationContext(), "Enter valid bus type!", Toast.LENGTH_SHORT).show();
                return;
            }
            if(type.equals("Select bus type"))
            {
                Toast.makeText(getApplicationContext(), "Enter valid bus type!", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(getApplicationContext(), "" + email, Toast.LENGTH_SHORT).show();

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                               // Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();

                                FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                                mDatabase.child("bus").child(currentFirebaseUser.getUid()).child("name").setValue(bname);
                                mDatabase.child("bus").child(currentFirebaseUser.getUid()).child("type").setValue(type);
                                mDatabase.child("bus").child(currentFirebaseUser.getUid()).child("email").setValue(currentFirebaseUser.getEmail());
                                mDatabase.child("bus").child(currentFirebaseUser.getUid()).child("satus").setValue("RUNNING");


                                Toast.makeText(Register.this, "Registered.",
                                        Toast.LENGTH_SHORT).show();
                                mAuth.signOut();
                                startActivity(new Intent(getApplicationContext(),AdminprofileActivity.class));

                                updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                              //  Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(Register.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            }

                            // ...
                        }

                        private void updateUI(FirebaseUser user) {

                        }
                    });


        }

    }

}
