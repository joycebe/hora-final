package hora.justinsebastian.example.com.hora_bus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Delete1 extends AppCompatActivity implements View.OnClickListener {

    private EditText inputEmail;
    private Button register;
    private FirebaseAuth mAuth;
    private int flag=1;
    details prof;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete1);
        inputEmail = findViewById(R.id.email);
        register = findViewById(R.id.emailreg);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==register)
        {
            final String email = inputEmail.getText().toString();
            //sourcE = getIntent().getStringExtra( "sourcee" );

            final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("bus");
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        prof = postSnapshot.getValue(details.class);

                        final String emaildb = prof.getemail();
                        Log.i("test","t1" + prof.getemail());
                        if(email.equals(prof.getemail())){

                            String myParentNode = dataSnapshot.getKey();

                            for (DataSnapshot child: dataSnapshot.getChildren())
                            {
                                String key = child.getKey().toString();

                                // Toast.makeText(getApplicationContext(), ""+ key, Toast.LENGTH_SHORT).show();
                                String value = child.getValue().toString();

                                prof = child.getValue(details.class);
                                final String emaildb1 = prof.getemail();
                                if(email.equals(emaildb1))
                                {

                                    //  Toast.makeText(getApplicationContext(), ""+ emaildb1, Toast.LENGTH_SHORT).show();
                                    //Toast.makeText(getApplicationContext(), ""+ key, Toast.LENGTH_SHORT).show();
                                    String uid = child.getKey().toString();

                                    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                                    mDatabase.child("busdetail").child(uid).removeValue();

                                    Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
                                    flag=0;
                                    Intent intent= new Intent(Delete1.this,AdminprofileActivity.class);
                                   // intent.putExtra( "id",uid );
                                    startActivity(intent);
                                }



                                //  Toast.makeText(getApplicationContext(), ""+ value, Toast.LENGTH_SHORT).show();
                                //map.put(key,value);
                                Log.i("test","key" + key);
                                Log.i("test","val" + value);
                            }


                            //Toast.makeText(getApplicationContext(), ""+ myParentNode, Toast.LENGTH_SHORT).show();
                            //Intent intent= new Intent(Insert.this,Routeinsert.class);
                            //intent.putExtra( "sourcee",email );
                            // startActivity(intent);

                        }

                    }
                    if(flag==1)
                    {
                        Toast.makeText(getApplicationContext(), "Invalid Email id.", Toast.LENGTH_LONG).show();

                        Intent intent= new Intent(Delete1.this,Delete1.class);
                        startActivity(intent);
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }


            });


            //Intent intent= new Intent(Delete1.this,Routedelete.class);
            //intent.putExtra( "sourcee",email );
            //startActivity(intent);
        }



    }
}
