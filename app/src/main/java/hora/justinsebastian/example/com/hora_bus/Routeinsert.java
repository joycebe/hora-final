package hora.justinsebastian.example.com.hora_bus;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Routeinsert extends AppCompatActivity implements View.OnClickListener {


   // private FirebaseAuth mAuth;
    private EditText source, sourcetime,destination,desttime;
    private EditText stop;
    private Button insert;
    private FirebaseAuth mAuth;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routeinsert);
        String uid = getIntent().getStringExtra( "id" );
        //Toast.makeText(getApplicationContext(), ""+ uid, Toast.LENGTH_SHORT).show();

        source = findViewById(R.id.sourcename);

        sourcetime= findViewById(R.id.sourcetime);
        destination= findViewById(R.id.destinationname);
        desttime= findViewById(R.id.destinationtime);

        stop=findViewById(R.id.stopno);

        insert = findViewById(R.id.insert);
        insert.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v==insert)
        {
            String sourcename = source.getText().toString();
            //Toast.makeText(getApplicationContext(), ""+ sourcename, Toast.LENGTH_SHORT).show();
            String sourcetime1 = sourcetime.getText().toString();
            //Toast.makeText(getApplicationContext(), ""+ sourcetime1, Toast.LENGTH_SHORT).show();
            String destination1= destination.getText().toString();
            //Toast.makeText(getApplicationContext(), ""+ destination1, Toast.LENGTH_SHORT).show();
            String destinationtime1=desttime.getText().toString();
            //Toast.makeText(getApplicationContext(), ""+ destinationtime1, Toast.LENGTH_SHORT).show();
            String stopnumber=stop.getText().toString();
            Toast.makeText(getApplicationContext(), ""+ stopnumber, Toast.LENGTH_SHORT).show();

            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("busdetail/"+getIntent().getStringExtra( "id" ));

            Map regi = new HashMap();
            regi.put("source",sourcename);
            regi.put("sourcetime",sourcetime1);
            regi.put("destination",destination1);
            regi.put("destinationtime",destinationtime1);
            String id2 =mDatabase.push().getKey();
            mDatabase.child(id2).setValue(regi);



            //mDatabase.child("busdetail").child(getIntent().getStringExtra( "id" )).push().child("source").setValue(sourcename);
            //mDatabase.child("busdetail").child(getIntent().getStringExtra( "id" )).child(stopnumber).child("sourcetime").setValue(sourcetime1);
            //mDatabase.child("busdetail").child(getIntent().getStringExtra( "id" )).child(stopnumber).child("destination").setValue(destination1);
           // mDatabase.child("busdetail").child(getIntent().getStringExtra( "id" )).child(stopnumber).child("destinationtime").setValue(destinationtime1);
            Toast.makeText(getApplicationContext(), "inserted", Toast.LENGTH_SHORT).show();


            Intent intent= new Intent(Routeinsert.this,Route.class);

            intent.putExtra( "uid",getIntent().getStringExtra( "id" ) );
            intent.putExtra("routeid",id2);
            startActivity(intent);
        }


    }
}
