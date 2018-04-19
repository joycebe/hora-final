package com.example.justinsebastian.hora;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

/**
 * Created by JOYCE on 15-02-2018.
 */

public class DetailsAdapterr extends ArrayAdapter {

    Activity activity;
    List<Detail>list;
    Button button4;
    String name;

    public DetailsAdapterr(Activity activity, List<Detail> list) {
        super( activity,R.layout.customm_layout, list);
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=activity.getLayoutInflater();
        convertView = layoutInflater.inflate( R.layout.customm_layout,null,true );

        TextView NAMEE =(TextView) convertView.findViewById( R.id.name_heree );
        TextView SOURCEE =(TextView) convertView.findViewById( R.id.source_heree );
        TextView TIMEEE =(TextView) convertView.findViewById( R.id.time_heree );
        TextView DESTINATIONN =(TextView) convertView.findViewById( R.id.destination_heree );
        TextView TYPEE =(TextView) convertView.findViewById( R.id.type_heree );
        TextView TIME1 =(TextView) convertView.findViewById( R.id.time1_heree );
        TextView STOP1 =(TextView) convertView.findViewById( R.id.stop1_heree );
        TextView TIME2 =(TextView) convertView.findViewById( R.id.time2_heree );
        TextView STOP2 =(TextView) convertView.findViewById( R.id.stop2_heree );
        TextView TableSource=(TextView)convertView.findViewById( R.id.tablesource );
        TextView Tablestime=(TextView)convertView.findViewById( R.id.tablestime );

        TextView TableDest=(TextView)convertView.findViewById( R.id.tabledest );
        TextView Tabledtime=(TextView)convertView.findViewById( R.id.tabledtime );
        button4=(Button) convertView.findViewById( R.id.button4 );

        Detail detail= list.get(position);
        name=detail.getName();

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getContext();
                Intent i = new Intent(context, DisplayActivity.class);
                i.putExtra( "busname", name );

                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //add this line

                context.startActivity(i);
            }
        });

        NAMEE.setText( detail.getName() );
        SOURCEE.setText( detail.getSource() );
        TIMEEE.setText( detail.getSourcetime() );
        DESTINATIONN.setText( detail.getDestination() );
        TYPEE.setText( detail.getType() );
        TIME1.setText( detail.getTime1() );
        STOP1.setText( detail.getStop1() );
        TIME2.setText( detail.getTime2() );
        STOP2.setText( detail.getStop2());
        TableSource.setText( detail.getSource() );
        Tablestime.setText( detail.getSourcetime() );
        TableDest.setText( detail.getDestination() );
        Tabledtime.setText( detail.getDestinationtime() );
        return convertView ;

    }

}
