package hora.justinsebastian.example.com.hora_bus;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by lenovo on 26-03-2018.
 */

public class RouteAdapter extends RecyclerView.Adapter<RouteAdapter.ViewHolder> {

    private Context context;
    //RelativeLayout relativeLayoutNoInternet;
    View contentView;

    LayoutInflater inflater;

    public static List<StoreDetails> storeDetails;
    //
    // Log.i("test", "Hello");







    public RouteAdapter(List<StoreDetails> listArray, Route route, LayoutInflater inflater) {
        super();
        //Getting all
        this.storeDetails =storeDetails;
        this.context = context;
        this.inflater = inflater;
       //


    }


    @Override
    public RouteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.route_list_element, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RouteAdapter.ViewHolder holder, int position) {
        final StoreDetails storeDetailsBloodBanks =  storeDetails.get(position);

        holder.textViewBloodBankName.setText(storeDetailsBloodBanks.getBloodBankName());
        holder.buttonCallNow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + storeDetailsBloodBanks.getPhoneNumber()));
                    context.startActivity(intent);
                }catch (Exception e){

                }

            }

        });

    }

    @Override
    public int getItemCount() {
        return storeDetails == null ? 0 : storeDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewBloodBankName;
        public Button buttonCallNow;
        public ViewHolder(View itemView) {
            super(itemView);
            textViewBloodBankName = (TextView) itemView.findViewById(R.id.textViewName);
            buttonCallNow = (Button) itemView.findViewById(R.id.buttonCallNow);
        }
    }




}





