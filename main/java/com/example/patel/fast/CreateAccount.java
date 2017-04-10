package com.example.patel.fast;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.json.JSONObject;

public class CreateAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }

    public void createUser(View v)throws Exception{

        final CharSequence pass1 = ((TextView) findViewById(R.id.password1)).getText();
        final CharSequence pass2 = ((TextView) findViewById(R.id.password2)).getText();
        //System.out.print(pass1.getText() + " " + pass2.getText());
        if(pass1.toString().equals(pass2.toString())) {
            findViewById(R.id.error).setVisibility(View.INVISIBLE);
            JSONObject j = new JSONObject();
            j.put("username", ((TextView) findViewById(R.id.username)).getText());
            j.put("password", ((TextView) findViewById(R.id.password1)).getText());
            j.put("firstname", ((TextView) findViewById(R.id.firstname)).getText());
            j.put("lastname", ((TextView) findViewById(R.id.lastname)).getText());
            j.put("housenum", ((TextView) findViewById(R.id.housenum)).getText());
            j.put("street", ((TextView) findViewById(R.id.street)).getText());
            j.put("city", ((TextView) findViewById(R.id.city)).getText());
            j.put("state", ((TextView) findViewById(R.id.state)).getText());
            j.put("zip", ((TextView) findViewById(R.id.zip)).getText());

            httpRequestTask http = new httpRequestTask(j, "/createCustomer");

            http.execute();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else{
            findViewById(R.id.error).setVisibility(View.VISIBLE);
        }
    }
}
