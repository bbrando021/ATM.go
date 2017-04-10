package com.example.patel.fast;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class CodeDisplay extends AppCompatActivity {

    private String transactionToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        transactionToken = b.getString("code");
        setContentView(R.layout.activity_code_display);
        System.out.println("Token: " + transactionToken);
        ((EditText)findViewById(R.id.codeText)).setText(transactionToken);
    }

}
