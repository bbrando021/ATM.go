package com.example.patel.fast;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONObject;

public class DisplayAccounts extends AppCompatActivity {

    private static JSONObject json;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_accounts);

        Bundle b = getIntent().getExtras();
        try {
            json = new JSONObject(b.getString("resp"));
        }catch(Exception e){
            System.out.println(e);
        }

    }

    public void credit(View v)throws Exception{
        Intent intent = new Intent(this, choice.class);
        intent.putExtra("token", json.getString("token"));
        JSONArray accounts = json.getJSONArray("accounts");
        boolean pass = false;

        for (int i = 0; i < accounts.length(); i++) {
            if (accounts.getJSONObject(i).getString("type").equals("Credit Card")) {
                intent.putExtra("accNum", accounts.getJSONObject(i).getString("_id"));
                pass = true;
                break;
            }
        }

        if (pass)
            startActivity(intent);
        else
            System.out.println("Couldn't get account number.");
    }
    public void savings(View v)throws Exception{
        Intent intent = new Intent(this, choice.class);
        intent.putExtra("token",json.getString("token"));
        JSONArray accounts = json.getJSONArray("accounts");
        boolean pass = false;

        for (int i = 0; i < accounts.length(); i++) {
            if (accounts.getJSONObject(i).getString("type").equals("Savings")) {
                intent.putExtra("accNum", accounts.getJSONObject(i).getString("_id"));
                pass = true;
                break;
            }
        }

        if (pass)
            startActivity(intent);
        else
            System.out.println("Couldn't get account number.");
    }
    public void checking(View v)throws Exception{
        Intent intent = new Intent(this, choice.class);
        intent.putExtra("token",json.getString("token"));
        JSONArray accounts = json.getJSONArray("accounts");
        boolean pass = false;

        for (int i = 0; i < accounts.length(); i++) {
            if (accounts.getJSONObject(i).getString("type").equals("Checking")) {
                intent.putExtra("accNum", accounts.getJSONObject(i).getString("_id"));
                pass = true;
                break;
            }
        }

        if (pass)
            startActivity(intent);
        else
            System.out.println("Couldn't get account number.");
    }
}
