package hora.justinsebastian.example.com.hora_bus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class NothingActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    details prof;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nothing);
        final TextView bname;
        final TextView btype;
        mAuth = FirebaseAuth.getInstance();
         bname=(TextView) findViewById(R.id.bname);
         btype=(TextView) findViewById(R.id.btype);
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        final String uid = currentFirebaseUser.getUid();

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("bus");

       // Toast.makeText(this, ""+uid, Toast.LENGTH_SHORT).show();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    prof = postSnapshot.getValue(details.class);

                    final String name = prof.getname();
                    final String type=prof.gettype();
                    if(uid.equals(postSnapshot.getKey())){
                    Toast.makeText(getApplicationContext(), "" + name+prof.type, Toast.LENGTH_SHORT).show();
                    bname.setText(""+bname.getText()+name);
                    btype.setText(""+btype.getText()+type);}

                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
               // Log.w(TAG, "Failed to read value.", error.toException());
            }
        });



    }
}
