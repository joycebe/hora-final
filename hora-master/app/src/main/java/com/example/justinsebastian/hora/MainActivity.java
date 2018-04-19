package com.example.justinsebastian.hora;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    TextView textview;

    EditText textView7;   //destination
    EditText textView6;  //source
    Button button2;     //search

    Spinner spinner1;
    String src;
    String dest;
    String time;

    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//spinner code

        spinner1= (Spinner)findViewById( R.id.spinner1);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(myAdapter);


        //marequee
        textview =(TextView) findViewById(R.id.text1);
        textview.setSelected(true);
        // editfields
        textView6 = (EditText) findViewById(R.id.textView6);
        textView7 = (EditText) findViewById(R.id.textView7);

        databaseReference = FirebaseDatabase.getInstance().getReference("busdetail");

        //result page
        button2=(Button) findViewById( R.id.button2 );
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addInfoToDB();
                Intent intent= new Intent(MainActivity.this,ResultActivity.class);
                intent.putExtra( "sourcee",src );
                intent.putExtra( "destinationn",dest );
                intent.putExtra( "timee",time=spinner1.getSelectedItem().toString() );

                startActivity(intent);

            }
        });

       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private  void addInfoToDB() {
        if (TextUtils.isEmpty(src=textView6.getEditableText().toString()) ) {
            Toast.makeText(this, "Please insert all values", Toast.LENGTH_SHORT).show();
        return;
        }
        if (TextUtils.isEmpty(dest=textView7.getEditableText().toString()) ) {
            Toast.makeText(this, "Please insert all values", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
