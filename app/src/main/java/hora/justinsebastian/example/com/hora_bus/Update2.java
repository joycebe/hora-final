package hora.justinsebastian.example.com.hora_bus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Update2 extends AppCompatActivity implements  View.OnClickListener{
    protected Spinner spinner;
    private Button update1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update2);
        String uid = getIntent().getStringExtra( "id" );
        Toast.makeText(getApplicationContext(), ""+ uid, Toast.LENGTH_LONG).show();
        spinner= (Spinner)findViewById( R.id.spinner2);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Update2.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.status));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myAdapter);

        update1 = findViewById(R.id.update1);
        update1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == update1) {
            final String status=spinner.getSelectedItem().toString();
            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();



            mDatabase.child("busdetail").child(getIntent().getStringExtra( "id" )).child("satus").setValue(status);

            Toast.makeText(getApplicationContext(), "STATUS UPDATED", Toast.LENGTH_LONG).show();

            Intent intent= new Intent(Update2.this,AdminprofileActivity.class);

            //        intent.putExtra( "number", (Parcelable) stop);
            startActivity(intent);
        }


    }
}
