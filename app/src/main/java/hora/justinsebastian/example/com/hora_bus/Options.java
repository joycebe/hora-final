package hora.justinsebastian.example.com.hora_bus;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Options extends AppCompatActivity implements  View.OnClickListener{
    private Button stop,route,home;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        stop = findViewById(R.id.stopbtn);
        stop.setOnClickListener(this);

        route = findViewById(R.id.routebtn);
        route.setOnClickListener(this);

        home=findViewById(R.id.homebtn);
        home.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==stop)
        {

            Intent intent= new Intent(Options.this,Route.class);

            intent.putExtra( "uid",getIntent().getStringExtra( "id" ) );
            intent.putExtra("routeid",getIntent().getStringExtra( "rid" ));
            intent.putExtra("rnumber",getIntent().getExtras().getInt("rnumber"));
            startActivity(intent);
        }
        else if (v==route){
            Intent intent= new Intent(Options.this,Routeinsert.class);

            intent.putExtra( "id",getIntent().getStringExtra( "id" ) );
            startActivity(intent);
        }
        else {
            Intent intent= new Intent(Options.this,AdminprofileActivity.class);
            startActivity(intent);

        }

    }
}
