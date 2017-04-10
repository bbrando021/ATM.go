package com.example.patel.fast;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class choice extends AppCompatActivity {

    private String token, accountNum;
    private double amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        token = b.getString("token");
        accountNum = b.getString("accNum");

        setContentView(R.layout.activity_choice);
    }

    public void gotoDeposit(View v)
    {
        String text = ((EditText)findViewById(R.id.amountTextField)).getText().toString();
        amount = Double.parseDouble(text);
        Intent intent = new Intent(this, deposit.class);
        intent.putExtra("amount", amount);
        intent.putExtra("accNum", accountNum);
        intent.putExtra("token", token);
        startActivity(intent);
    }

    public void gotoWithdrawal(View v)
    {
        String text = ((EditText)findViewById(R.id.amountTextField)).getText().toString();
        amount = Double.parseDouble(text);
        Intent intent = new Intent(this, withdrawal.class);
        intent.putExtra("amount", amount);
        intent.putExtra("accNum", accountNum);
        intent.putExtra("token", token);
        startActivity(intent);
    }
}
