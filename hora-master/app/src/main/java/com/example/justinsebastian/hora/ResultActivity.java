package com.example.justinsebastian.hora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class ResultActivity extends AppCompatActivity {

    //recyclerview object
    private RecyclerView recyclerView;

    //adapter object
    private RecyclerView.Adapter adapter;

    TextView textview;
    private   ListView listView;
    String sourcE;
    String desT;
    String timE;
    String start;
    String stop;
    String middle;
    private DatabaseReference databaseReference;
    List<Detail> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(null);
        setContentView(R.layout.activity_result);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //marequee

//         textview =(TextView) findViewById(R.id.text);
 //        textview.setSelected(true);

        //    listView = (ListView) findViewById( R.id.list1 );
        databaseReference = FirebaseDatabase.getInstance().getReference("busdetail");
        //databaseReference.child("busdetail");
        list = new ArrayList<>( );

        sourcE = getIntent().getStringExtra( "sourcee" );
        desT = getIntent().getStringExtra( "destinationn" );
        timE = getIntent().getStringExtra( "timee" );
        //Toast.makeText( this, "source is " + sourcE, Toast.LENGTH_SHORT ).show();


        String[] arrayString = timE.split(" ");

        start = arrayString[0];
        middle = arrayString[1];
        stop = arrayString[2];
//        Toast.makeText( this, "start is " + start, Toast.LENGTH_SHORT ).show();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot all : dataSnapshot.getChildren()){
                    for (DataSnapshot all_data : all.getChildren()) {
                        if (all_data != null) {
                            //Toast.makeText( ResultActivity.this, "Searching..", Toast.LENGTH_SHORT ).show();
                            Detail detail = all_data.getValue(Detail.class);

                            if (detail.getSource().equals( sourcE ) || detail.getStop1().equals( sourcE )|| detail.getStop2().equals( sourcE ) ) {
                                if (detail.getDestination().equals( desT )) {
                                    boolean timeChk = isTimeWithinInterval( start, stop, detail.getSourcetime() );
                                    if (timeChk) {
                                        // Success code
                                        list.add( detail );
                                    }
                                }
                            }
                        }
                    }
                }
                if(list.isEmpty())
                {
                    Toast.makeText( ResultActivity.this, "Sorry no bus available in this route ...", Toast.LENGTH_SHORT ).show();
                }
                adapter = new DetailsAdapter(getApplicationContext(), list);
                recyclerView.setAdapter( adapter );
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }


    public boolean isTimeWithinInterval(String lwrLimit, String uprLimit, String time){

        // Time 1 in string - Lower limit
        Date time_1 = null;
        try {
            time_1 = new SimpleDateFormat("HH:mm:ss").parse(lwrLimit);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar_1 = Calendar.getInstance();
        calendar_1.setTime(time_1);

        // Time 2 in string - Upper limit
        Date time_2 = null;
        try {
            time_2 = new SimpleDateFormat("HH:mm:ss").parse(uprLimit);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar_2 = Calendar.getInstance();
        calendar_2.setTime(time_2);

        // Time 3 in String - to be checked
        Date d = null;
        try {
            d = new SimpleDateFormat("HH:mm:ss").parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar_3 = Calendar.getInstance();
        calendar_3.setTime(d);

        Date x = calendar_3.getTime();
        if (x.after(calendar_1.getTime()) && x.before(calendar_2.getTime())) {
            //checkes whether the current time is between two times
           // Log.d(TAG,true+"");
            return true;
        }
        return false;
    }
}
