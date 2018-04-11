package hora.justinsebastian.example.com.hora_bus;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Route extends AppCompatActivity implements View.OnClickListener {
    private EditText st1,tym1,st2,tym2,st3,tym3;
    private Button save;
    private  int mHour,mMinute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);

        st1=findViewById(R.id.stop1);
        tym1=findViewById(R.id.time1);
        tym1.setOnClickListener(this);

        st2=findViewById(R.id.stop2);
        tym2=findViewById(R.id.time2);
        tym2.setOnClickListener(this);

        st3=findViewById(R.id.stop3);
        tym3=findViewById(R.id.time3);
        tym3.setOnClickListener(this);
        save = findViewById(R.id.save);
        save.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

       // String stopnumber=
        int i;
        Intent mIntent = getIntent();
        //int i=mIntent.getIntExtra("rnumber",3);
        i=getIntent().getExtras().getInt("rnumber");

        //Toast.makeText(getApplicationContext(), "Stops"+i, Toast.LENGTH_SHORT).show();

        if (v==save)
        {
            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
            String stop1=st1.getText().toString();
            String time1=tym1.getText().toString();
            if (TextUtils.isEmpty(stop1)&&TextUtils.isEmpty(time1)) {
                Toast.makeText(getApplicationContext(), "no Stops are added", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(Route.this,AdminprofileActivity.class);
                startActivity(intent);

            }
            else
            {
                i++;
                mDatabase.child("busdetail").child(getIntent().getStringExtra( "uid" )).child(getIntent().getStringExtra( "routeid" )).child("stop"+i).setValue(stop1);
                mDatabase.child("busdetail").child(getIntent().getStringExtra( "uid" )).child(getIntent().getStringExtra( "routeid" )).child("time"+i).setValue(time1);
                Toast.makeText(getApplicationContext(), "Stop1 are added", Toast.LENGTH_SHORT).show();

            }

            String stop2=st2.getText().toString();
            String time2=tym2.getText().toString();
            if (TextUtils.isEmpty(stop2)&&TextUtils.isEmpty(time2)) {
                Toast.makeText(getApplicationContext(), "no stops are added", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(Route.this,AdminprofileActivity.class);
                startActivity(intent);
            }
            else
            {
                i++;
                mDatabase.child("busdetail").child(getIntent().getStringExtra( "uid" )).child(getIntent().getStringExtra( "routeid" )).child("stop"+i).setValue(stop2);
                mDatabase.child("busdetail").child(getIntent().getStringExtra( "uid" )).child(getIntent().getStringExtra( "routeid" )).child("time"+i).setValue(time2);
                Toast.makeText(getApplicationContext(), "Stop2 are added", Toast.LENGTH_SHORT).show();


            }



            String stop3=st3.getText().toString();
            String time3=tym3.getText().toString();
            if (TextUtils.isEmpty(stop3)&&TextUtils.isEmpty(time3)) {
                Toast.makeText(getApplicationContext(), "no stops are added", Toast.LENGTH_SHORT).show();
                return;
            }
            else {
                i++;


                mDatabase.child("busdetail").child(getIntent().getStringExtra("uid")).child(getIntent().getStringExtra("routeid")).child("stop"+i).setValue(stop3);
                mDatabase.child("busdetail").child(getIntent().getStringExtra("uid")).child(getIntent().getStringExtra("routeid")).child("time"+i).setValue(time3);
                Toast.makeText(getApplicationContext(), "Stop3 are added", Toast.LENGTH_SHORT).show();


            }
            Toast.makeText(getApplicationContext(), "Stops are added", Toast.LENGTH_SHORT).show();

            Intent intent= new Intent(Route.this,Options.class);

            intent.putExtra( "id",getIntent().getStringExtra( "uid" ) );
            intent.putExtra("rid",getIntent().getStringExtra("routeid"));
            intent.putExtra("rnumber",i);
            //Toast.makeText(getApplicationContext(), "Stops"+i, Toast.LENGTH_SHORT).show();

            startActivity(intent);

        }
        else if (v==tym1)
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

                            tym1.setText(hourOfDay + ":" + minute+":00");
                        }
                    }, mHour, mMinute, true);
            timePickerDialog.show();
        }
        else if (v==tym2)
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

                            tym2.setText(hourOfDay + ":" + minute+":00");
                        }
                    }, mHour, mMinute, true);
            timePickerDialog.show();
        }
        else if (v==tym3)
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

                            tym3.setText(hourOfDay + ":" + minute+":00");
                        }
                    }, mHour, mMinute, true);
            timePickerDialog.show();
        }
    }
}
