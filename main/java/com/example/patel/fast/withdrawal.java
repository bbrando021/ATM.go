package com.example.patel.fast;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.json.JSONObject;


public class withdrawal extends AppCompatActivity {

    private String token, accNumber;
    private double amount;
    private HTTPReq http;


    class HTTPReq extends httpRequestTask {
        public HTTPReq(JSONObject j, String e){
            super(j, e);
        }

        @Override
        protected void onPostExecute(Object result) {
            System.out.println("He-----");
            System.out.println(result);
            try {
                showCode();
            }catch(Exception e){

            }
        }
    }

    public void showCode() throws Exception{
        Intent intent = new Intent(this, CodeDisplay.class);
        System.out.println("IMHERE");
        System.out.println(http.getResponse());
        intent.putExtra("code", http.getResponse().getString("transactionToken"));
        startActivity(intent);
    }

    public void toggleBalance(View v){
        initiateRequest("balance");
    }

    public void toggleRewards(View v) {
        initiateRequest("rewards");
    }

    private void initiateRequest(String medium) {
        try {
            JSONObject j = new JSONObject();
            j.put("token", token);
            j.put("accountNumber", accNumber);
            j.put("amount", amount);
            j.put("type", "withdraw");
            j.put("medium", medium);

            http = new HTTPReq(j, "/transaction");
            http.execute();
        } catch (Exception e){
            System.out.println(e);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        token = b.getString("token");
        accNumber = b.getString("accNum");
        amount = b.getDouble("amount");

        setContentView(R.layout.activity_withdrawal);
    }
}
