package androidclass.fetchdatafromjson;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by hp on 6/9/2020.
 */

public class Country extends AppCompatActivity{

    private static String URL_DATA = "https://restcountries.eu/rest/v2/region/";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItemRegions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.rview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        listItemRegions = new ArrayList<>();

        Intent i = getIntent();
        String rgn = i.getStringExtra("regionName");
        URL_DATA = "https://restcountries.eu/rest/v2/region/";
        URL_DATA = URL_DATA + rgn;

        loadRecyclerViewData();


    }

    @Override
    protected void onResume() {
        super.onResume();


        listItemRegions = new ArrayList<>();

        Intent i = getIntent();
        String rgn = i.getStringExtra("regionName");

        URL_DATA = "https://restcountries.eu/rest/v2/region/";
        URL_DATA = URL_DATA + rgn;

        loadRecyclerViewData();

    }

    private void loadRecyclerViewData()
    {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data.....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        progressDialog.dismiss();
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = new JSONArray(s);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Log.d("TAG for array len", String.valueOf(jsonArray.length()));
                        for(int i=0; i < jsonArray.length(); i++)
                        {
                            JSONObject jsonObj = null;
                            try {
                                jsonObj = jsonArray.getJSONObject(i);
                            } catch (JSONException e) {
                                Log.d("jsonObj.getString('name')-->", "Not coming here");
                                e.printStackTrace();
                            }

                            ListItem listItemRegion = null;
                            JSONArray jsonArrlatlng = null;
                            try {
                                jsonArrlatlng = (JSONArray) jsonObj.get("latlng");
                                if (jsonArrlatlng.length() == 2) {
                                    listItemRegion = new ListItem(
                                            jsonObj.getString("name"),
                                            jsonArrlatlng.getDouble(0),
                                            jsonArrlatlng.getDouble(1),
                                            jsonObj.getString("region")
                                    );
                                }
                            } catch(JSONException e){
                                Log.d("jsonObj.getString('name')-->", "Error coming here");
                                e.printStackTrace();
                            }

                            if (jsonArrlatlng != null && jsonArrlatlng.length() == 2) {
                                listItemRegions.add(listItemRegion);
                            }

                        }
                        adapter =  new AdapterRegion(listItemRegions , getApplicationContext());
                        recyclerView.setAdapter(adapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext() , "No other country lies in this region" , Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
