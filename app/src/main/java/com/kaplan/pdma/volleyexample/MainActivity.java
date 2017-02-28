package com.kaplan.pdma.volleyexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.StringBuilderPrinter;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String url = "http://api.fixer.io/latest?base=SGD";

        final TextView textView = (TextView) findViewById(R.id.textView);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject rates = response.getJSONObject("rates");
                            double aud = rates.getDouble("AUD");
                            double myr = rates.getDouble("MYR");
                            double php = rates.getDouble("PHP");
                            double cny = rates.getDouble("CNY");
                            double krw = rates.getDouble("KRW");
                            double thb = rates.getDouble("THB");

                            StringBuilder sb = new StringBuilder();
                            sb.append("Australia Dollar: " + aud +"\n");
                            sb.append("Chinese Yuan: " + cny +"\n");
                            sb.append("Korean Won: " + krw +"\n");
                            sb.append("Malaysia Ringgit: " + myr +"\n");
                            sb.append("Philippines Peso: " + php +"\n");
                            sb.append("Thai Baht: " + thb +"\n");
                            textView.setText(sb.toString());


                        } catch (JSONException e) {
                            textView.setText(e.getMessage());
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                    }
                });

// Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsObjRequest);

    }
}
