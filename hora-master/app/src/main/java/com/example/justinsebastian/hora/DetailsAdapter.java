package com.example.justinsebastian.hora;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by JOYCE on 15-02-2018.
 */

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.ViewHolder> {

    private Context context;
    private List<Detail> list;

    public DetailsAdapter(Context context, List<Detail> list) {
        //super( activity,R.layout.custom_layout, list);
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.custom_layout, parent, false );
        ViewHolder viewHolder = new ViewHolder( v );
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Detail detail = list.get( position );

        holder.source_here.setText( detail.getSource() );
        holder.name_here.setText( detail.getName() );
        holder.time_here.setText( detail.getSourcetime() );
        holder.type_here.setText( detail.getType() );
        holder.destination_here.setText( detail.getDestination() );

        //Glide.with(context).load(products.getImage()).into(holder.imageView);
        holder.itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str = detail.getName();
                String str1 = detail.getDestination();
                String str2=detail.getSource();
                String str3=detail.getType();
                String str4=detail.getSourcetime();
          //      Toast.makeText( context, "Key" + str, Toast.LENGTH_SHORT ).show();
            //    Toast.makeText( context, "Key 1" + str1, Toast.LENGTH_SHORT ).show();
              //  Toast.makeText( context, "Key 2" + str2, Toast.LENGTH_SHORT ).show();
                //Toast.makeText( context, "Key .3" + str3, Toast.LENGTH_SHORT ).show();
                //Toast.makeText( context, "Key 4" + str4, Toast.LENGTH_SHORT ).show();

                Intent intent = new Intent( context, details_display.class );
                intent.putExtra( "key", str );
                intent.putExtra( "key1", str1 );
                intent.putExtra( "key2", str2 );
                intent.putExtra( "key3", str3 );
                intent.putExtra( "key4", str4 );
                context.startActivity( intent );
            }
        } );
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView source_here;
        public TextView time_here;
        public TextView type_here;
        public TextView name_here;
        public TextView destination_here;

        public ViewHolder(View itemView) {
            super( itemView );
            context = itemView.getContext();
            source_here = (TextView) itemView.findViewById( R.id.source_here );
            destination_here = (TextView) itemView.findViewById( R.id.destination_here );
            name_here = (TextView) itemView.findViewById( R.id.name_here );
            type_here = (TextView) itemView.findViewById( R.id.type_here );
            time_here = (TextView) itemView.findViewById( R.id.time_here );

            itemView.setClickable( true );
        }
    }
}