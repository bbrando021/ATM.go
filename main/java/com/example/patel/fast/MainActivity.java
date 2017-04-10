package com.example.patel.fast;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    httpRequestTask http;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void pickChoice(View v) throws Exception {

        JSONObject j = new JSONObject();
        j.put("username", ((TextView) findViewById(R.id.userName)).getText());
        j.put("password", ((TextView) findViewById(R.id.password)).getText());

        class HTTPTask extends httpRequestTask {

            public HTTPTask(JSONObject j, String e) {
                super(j, e);
            }

            @Override
            protected void onPostExecute(Object result) {
                doThat(result);
            }
        }

        http = new HTTPTask(j,"/login");

        http.execute();


    }

    public void doThat(Object result) {
        Intent intent = new Intent(this, DisplayAccounts.class);
        JSONObject resp = http.getResponse();
        try{
            System.out.println("IM AM PRINTING0");
            System.out.println(resp.getString("err"));

            if(resp.getString("err").equals("LOGERROR")){
                ((TextView)findViewById(R.id.loginError)).setVisibility(View.VISIBLE);
                return;
            }
        }catch(Exception e){
            ((TextView)findViewById(R.id.loginError)).setVisibility(View.INVISIBLE);
            System.out.println("DATA");
            System.out.println(resp);
            intent.putExtra("resp",resp.toString());
            startActivity(intent);
        }


    }

    public void newAccount(View v)throws Exception{
        System.out.println("hello");

        Intent intent = new Intent(this, CreateAccount.class);
        startActivity(intent);

    }
}
