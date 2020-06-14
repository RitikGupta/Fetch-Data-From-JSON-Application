package androidclass.fetchdatafromjson;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hp on 6/5/2020.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<ListItem> listItems;
    private Context context;


    public MyAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item , parent, false );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        final ListItem listItem =  listItems.get(position);

        holder.txtviewCountryName.setText(listItem.getCountryName());
        double lat = listItem.getLat();
        holder.txtviewlat.setText(String.valueOf(lat));
        double longitude = listItem.getLongitude();
        holder.txtviewlong.setText(String.valueOf(longitude));
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context ,Country.class);
                i.putExtra("regionName", listItem.getRegion());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return listItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView txtviewCountryName;
        public  TextView txtviewlat;
        public TextView txtviewlong;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {

            super(itemView);
            txtviewCountryName = (TextView)itemView.findViewById(R.id.txtviewCountry);
            txtviewlat = (TextView)itemView.findViewById(R.id.txtviewlat);
            txtviewlong = (TextView)itemView.findViewById(R.id.txtviewlong);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.linearlayout);
        }
    }
}
