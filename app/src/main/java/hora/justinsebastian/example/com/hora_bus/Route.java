package hora.justinsebastian.example.com.hora_bus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Route extends AppCompatActivity implements View.OnClickListener {
    private EditText st1,tym1,st2,tym2,st3,tym3;
    private Button save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);

        st1=findViewById(R.id.stop1);
        tym1=findViewById(R.id.time1);

        st2=findViewById(R.id.stop2);
        tym2=findViewById(R.id.time2);

        st3=findViewById(R.id.stop3);
        tym3=findViewById(R.id.time3);

        save = findViewById(R.id.save);
        save.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v==save)
        {
            String stop1=st1.getText().toString();
            String time1=tym1.getText().toString();

            String stop2=st2.getText().toString();
            String time2=tym2.getText().toString();

            String stop3=st3.getText().toString();
            String time3=tym3.getText().toString();

            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
            mDatabase.child("busdetail").child(getIntent().getStringExtra( "uid" )).child(getIntent().getStringExtra( "routeid" )).child("stop1").setValue(stop1);
            mDatabase.child("busdetail").child(getIntent().getStringExtra( "uid" )).child(getIntent().getStringExtra( "routeid" )).child("time1").setValue(time1);

            mDatabase.child("busdetail").child(getIntent().getStringExtra( "uid" )).child(getIntent().getStringExtra( "routeid" )).child("stop2").setValue(stop2);
            mDatabase.child("busdetail").child(getIntent().getStringExtra( "uid" )).child(getIntent().getStringExtra( "routeid" )).child("time2").setValue(time2);

            mDatabase.child("busdetail").child(getIntent().getStringExtra( "uid" )).child(getIntent().getStringExtra( "routeid" )).child("stop3").setValue(stop3);
            mDatabase.child("busdetail").child(getIntent().getStringExtra( "uid" )).child(getIntent().getStringExtra( "routeid" )).child("time3").setValue(time3);
            Toast.makeText(getApplicationContext(), "Stops are added", Toast.LENGTH_SHORT).show();

            Intent intent= new Intent(Route.this,Routeinsert.class);

            intent.putExtra( "id",getIntent().getStringExtra( "uid" ) );
           // intent.putExtra("routeid",id2);
            startActivity(intent);

        }
    }
}
