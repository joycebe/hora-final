package hora.justinsebastian.example.com.hora_bus;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Routeinsert extends AppCompatActivity implements View.OnClickListener {


   // private FirebaseAuth mAuth;
    private EditText source, sourcetime,destination,desttime;
    private EditText stop;
    private Button insert;
    private FirebaseAuth mAuth;
    private  int mHour,mMinute;
    details prof;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routeinsert);
         String uid = getIntent().getStringExtra( "id" );
        //Toast.makeText(getApplicationContext(), ""+ uid, Toast.LENGTH_SHORT).show();

        source = findViewById(R.id.sourcename);

        sourcetime= findViewById(R.id.sourcetime);
        sourcetime.setOnClickListener(this);
       // sourcetime.addTextChangedListener(new PatternedTextWatcher("###-###-####"));


        destination= findViewById(R.id.destinationname);
        desttime= findViewById(R.id.destinationtime);
        desttime.setOnClickListener(this);

       // stop=findViewById(R.id.stopno);

        insert = findViewById(R.id.insert);
        insert.setOnClickListener(this);





    }

    @Override
    public void onClick(View v) {
        if (v==insert)
        {
            final String sourcename = source.getText().toString();
            if (TextUtils.isEmpty(sourcename)) {
                Toast.makeText(getApplicationContext(), "Enter source name!", Toast.LENGTH_SHORT).show();
                return;
            }
            //Toast.makeText(getApplicationContext(), ""+ sourcename, Toast.LENGTH_SHORT).show();
            final String sourcetime1 = sourcetime.getText().toString();
            if (TextUtils.isEmpty(sourcetime1)) {
                Toast.makeText(getApplicationContext(), "Enter source time!", Toast.LENGTH_SHORT).show();
                return;
            }
            //Toast.makeText(getApplicationContext(), ""+ sourcetime1, Toast.LENGTH_SHORT).show();
            final String destination1= destination.getText().toString();
            if (TextUtils.isEmpty(destination1)) {
                Toast.makeText(getApplicationContext(), "Enter destination name!", Toast.LENGTH_SHORT).show();
                return;
            }
            //Toast.makeText(getApplicationContext(), ""+ destination1, Toast.LENGTH_SHORT).show();
            final String destinationtime1=desttime.getText().toString();
            if (TextUtils.isEmpty(destinationtime1)) {
                Toast.makeText(getApplicationContext(), "Enter destination time!", Toast.LENGTH_SHORT).show();
                return;
            }
            //Toast.makeText(getApplicationContext(), ""+ destinationtime1, Toast.LENGTH_SHORT).show();

            final String uid = getIntent().getStringExtra( "id" );

            final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("bus");
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        prof = postSnapshot.getValue(details.class);


                        if(uid.equals(postSnapshot.getKey())){
                            String name = prof.getname();
                            final String type=prof.gettype();
                            //Toast.makeText(getApplicationContext(), "" + name+prof.type, Toast.LENGTH_SHORT).show();
                            DatabaseReference mDataba = FirebaseDatabase.getInstance().getReference().child("busdetail/"+getIntent().getStringExtra( "id" ));

                            Map regi = new HashMap();
                            regi.put("source",sourcename);
                            regi.put("sourcetime",sourcetime1);
                            regi.put("destination",destination1);
                            regi.put("destinationtime",destinationtime1);
                            regi.put("name",name);
                            regi.put("type",type);
                            String id2 =mDatabase.push().getKey();
                            mDataba.child(id2).setValue(regi);
                            Intent intent= new Intent(Routeinsert.this,Route.class);

                            intent.putExtra( "uid",getIntent().getStringExtra( "id" ) );
                            intent.putExtra("routeid",id2);
                            Toast.makeText(getApplicationContext(), "!"+id2, Toast.LENGTH_SHORT).show();

                            // intent.putExtra("rnumber",0);
                            startActivity(intent);

                        }

                    }

                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    // Log.w(TAG, "Failed to read value.", error.toException());
                }
            });





            // Toast.makeText(getApplicationContext(), ""+ stopnumber, Toast.LENGTH_SHORT).show();





            //mDatabase.child("busdetail").child(getIntent().getStringExtra( "id" )).push().child("source").setValue(sourcename);
            //mDatabase.child("busdetail").child(getIntent().getStringExtra( "id" )).child(stopnumber).child("sourcetime").setValue(sourcetime1);
            //mDatabase.child("busdetail").child(getIntent().getStringExtra( "id" )).child(stopnumber).child("destination").setValue(destination1);
           // mDatabase.child("busdetail").child(getIntent().getStringExtra( "id" )).child(stopnumber).child("destinationtime").setValue(destinationtime1);




        }
        else if(v==sourcetime)
        {
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            sourcetime.setText(hourOfDay + ":" + minute+":00");
                        }
                    }, mHour, mMinute, true);
            timePickerDialog.show();

        }
        else if(v==desttime)
        {
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            desttime.setText(hourOfDay + ":" + minute+":00");
                        }
                    }, mHour, mMinute, true);
            timePickerDialog.show();
        }


    }
}
