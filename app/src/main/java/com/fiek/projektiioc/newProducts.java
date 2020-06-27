package com.fiek.projektiioc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class newProducts extends AppCompatActivity {


    String productname , productcost , productcomment, relasedate;
    TextView productn , productc , relased , productcomm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_products);

        productn = (TextView)findViewById(R.id.productname);
        productc = (TextView)findViewById(R.id.productcost);
       // productcomm = (TextView)findViewById(R.id.productcomment);
        relased = (TextView)findViewById(R.id.productrealsedate);


        //duhet te shkruhet ip adresa lokale IP4
        getJSON("http://192.168.0.109/products/products.php");
    }

    private void getJSON(final String urlWebService) {

        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
               // Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                try {
                    loadIntoListView(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json).append("\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void loadIntoListView(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);

      //  String[] products = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject obj = jsonArray.getJSONObject(i);

            productname = obj.getString("productname");
//            productcomment = obj.getString("productcomment");
            relasedate= obj.getString("releasdate");
            productcost = obj.getString("productcost");

            productn.append("\n" + productname);
//            productcomm.append("\n" + productcomment);
            relased.append("\n" + relasedate);
           productc.append("\n" + productcost);

        }
    }
}