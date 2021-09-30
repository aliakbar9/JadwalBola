package com.mdr.sidiq.jadwalbola;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    //Deklarasi variabel
    private RecyclerView rvItem;
    private ProgressBar indicator;
    private String URL = "http://area54labs.net/api/bola/1/7";
    private ArrayList<JadwalItem> listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inisialisasi
        indicator = (ProgressBar)findViewById(R.id.indicator);
        rvItem = (RecyclerView)findViewById(R.id.rv_item);
        rvItem.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        rvItem.setLayoutManager(linearLayoutManager);

        listItem = new ArrayList<>();

        //Request ke server
        indicator.setVisibility(View.VISIBLE);
        rvItem.setVisibility(View.GONE);

        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //Parsing Response
                indicator.setVisibility(View.GONE);
                rvItem.setVisibility(View.VISIBLE);
                try{
                    JSONArray array = new JSONArray(response);
                    JadwalItem jadwalItem = null;

                    for(int i = 0; i < array.length();i++) {
                        JSONObject object = array.getJSONObject(i);

                        jadwalItem = new JadwalItem();
                        jadwalItem.setEvent(object.optString("event"));
                        jadwalItem.setKickOff(object.optString("kick_off"));
                        jadwalItem.setTeamAway(object.optString("team_away"));
                        jadwalItem.setTeamHome(object.optString("team_home"));
                        jadwalItem.setTv(object.optString("tv"));

                        listItem.add(jadwalItem);

                    }
                    JadwalAdapter adapter = new JadwalAdapter(MainActivity.this, listItem);
                    rvItem.setAdapter(adapter);
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                indicator.setVisibility(View.GONE);
                rvItem.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Gagal download data", Toast.LENGTH_LONG);
            }
        });

        queue.add(stringRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //hactoberfast2021
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
