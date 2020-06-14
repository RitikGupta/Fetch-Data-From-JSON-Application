package androidclass.fetchdatafromjson;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hp on 6/10/2020.
 */

public class AdapterRegion extends RecyclerView.Adapter<AdapterRegion.ViewHolder> {


    List<ListItem> listItemRegions;
    Context context;

    public AdapterRegion(List<ListItem> listItemRegions , Context context)
    {
        this.listItemRegions = listItemRegions;
        this.context = context;
    }
    @Override
    public AdapterRegion.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_region , parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterRegion.ViewHolder holder, int position) {

        ListItem listItemRegion = listItemRegions.get(position);

        holder.txtviewCountryName.setText(listItemRegion.getCountryName());
        double lat = listItemRegion.getLat();
        holder.txtviewlat.setText(String.valueOf(lat));
        double longitude = listItemRegion.getLongitude();
        holder.txtviewlong.setText(String.valueOf(longitude));
        holder.txtviewRegion.setText(listItemRegion.getRegion());
    }

    @Override
    public int getItemCount()
    {
        return listItemRegions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtviewCountryName;
        public  TextView txtviewlat;
        public TextView txtviewlong;
        public TextView txtviewRegion;

        public ViewHolder(View itemView) {
            super(itemView);
            txtviewCountryName = (TextView)itemView.findViewById(R.id.txtviewCountry);
            txtviewlat = (TextView)itemView.findViewById(R.id.txtviewlat);
            txtviewlong = (TextView)itemView.findViewById(R.id.txtviewlong);
            txtviewRegion = (TextView)itemView.findViewById(R.id.textViewRegion);

        }
    }
}
