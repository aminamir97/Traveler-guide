package com.example.travelerguide;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;

public class CurrencyExchange extends AppCompatActivity {
    // json object response url
    private String urlJsonObj = "https://api.exchangerate-api.com/v4/latest/";

    private static String TAG = CurrencyExchange.class.getSimpleName();

    // Progress dialog
    private ProgressDialog pDialog;

    // temporary string to show the parsed response
    private String jsonResponse;

    EditText amount;
    Spinner fromCur,toCur;
    TextView resultshow;

    String fromcurrency,tocurrency;

    Double rateValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_exchange);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        amount = findViewById(R.id.moneyAmount);
        fromCur = findViewById(R.id.fromCurrency);
        toCur = findViewById(R.id.toCurrency);
        resultshow = findViewById(R.id.resultText);


        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

       // makeJsonObjectRequest();


    }

    public void makeJsonObjectRequest()
    {
        //pDialog.show();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlJsonObj, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("resp", response.toString());
                try
                {
                    JSONObject rates = response.getJSONObject("rates");
                    Log.e("inside",tocurrency);
                    rateValue = rates.getDouble(tocurrency);
                   // Toast.makeText(getApplicationContext(), rates.getString("USD")+"\n"+rateValue,Toast.LENGTH_LONG).show();

                    Double resultDouble = Double.parseDouble(amount.getText().toString()) * rateValue;
                    resultshow.setText("Currency Result = " + resultDouble);

                    pDialog.dismiss();



                }catch (Exception e)
                {
                    Log.d("resp", response.toString());
                }
                pDialog.dismiss();



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage()+"",Toast.LENGTH_LONG).show();


            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }

    public void exchnageBtn(View view) {

        if(!(fromCur.getSelectedItem().toString().equals(toCur.getSelectedItem().toString())))
        {
            if(!(amount.getText().toString().equals("")))
            {
                fromcurrency = fromCur.getSelectedItem().toString();
                urlJsonObj+=fromcurrency;
                Log.e("fromCurr",urlJsonObj);

                tocurrency = toCur.getSelectedItem().toString();
                makeJsonObjectRequest();
                Log.e("rateval",rateValue+"");

               // Double resultDouble = Double.parseDouble(amount.getText().toString()) * rateValue;
               // resultshow.setText("Currency Result = " + resultDouble);





            }else
            {
                amount.setError("Please fill");
            }

        }else
        {
            Toast.makeText(this,"Please Select different currencies !",Toast.LENGTH_LONG).show();
        }




    }
}
