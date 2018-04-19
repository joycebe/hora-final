package com.example.justinsebastian.hora;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class details_display extends AppCompatActivity {
    TextView textview;
    private ListView listView;
    String Ss;
    String Dd;
    String Ty;
    String Ti;
    String Nn;
    private DatabaseReference databaseReference;
    List<Detail> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_details_display );

        listView = (ListView) findViewById( R.id.list1 );
        databaseReference = FirebaseDatabase.getInstance().getReference( "busdetail" );
        list = new ArrayList<>();

        Nn = getIntent().getStringExtra( "key" );
        Dd = getIntent().getStringExtra( "key1" );
        Ss = getIntent().getStringExtra( "key2" );
        Ty = getIntent().getStringExtra( "key3" );
        Ti = getIntent().getStringExtra( "key4" );
      //  Toast.makeText( this, "source is " + Ss, Toast.LENGTH_SHORT ).show();
      //  Toast.makeText( this, "Dest is " + Dd, Toast.LENGTH_SHORT ).show();
      //  Toast.makeText( this, "name is " + Nn, Toast.LENGTH_SHORT ).show();
      //  Toast.makeText( this, "type is " + Ty, Toast.LENGTH_SHORT ).show();
      //  Toast.makeText( this, "time is " + Ti, Toast.LENGTH_SHORT ).show();

    }
        @Override
        protected void onStart(){
            super.onStart();
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    list.clear();
                    for(DataSnapshot all:dataSnapshot.getChildren())
                    for (DataSnapshot all_data : all.getChildren()) {
                        if(all_data!=null) {
                            //Toast.makeText( com.example.justinsebastian.hora.details_display.this, ".", Toast.LENGTH_SHORT ).show();
                            Detail detail = all_data.getValue( Detail.class );
                            if (detail.getSource().equals( Ss )) {
                                if (detail.getDestination().equals( Dd )) ;
                                {
                                    if (detail.getName().equals( Nn )) {
                                        if (detail.getSourcetime().equals( Ti )) {
                                            if (detail.getType().equals( Ty )) {
                                                list.add( detail );
                                            }
                                        }
                                    }
                                }
                            }
                        }

                    }

                   // setListAdapter(new ArrayAdapter<String>(this,
                     //       android.R.layout.simple_list_item_1,
                       //     this.directoryEntries));

                    DetailsAdapterr detailsAdapterr = new DetailsAdapterr( com.example.justinsebastian.hora.details_display.this, list);
                    listView.setAdapter(detailsAdapterr);
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }


