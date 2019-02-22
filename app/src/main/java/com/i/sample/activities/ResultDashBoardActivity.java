package com.i.sample.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.i.sample.R;

public class ResultDashBoardActivity extends AppCompatActivity {

    TextView mREsultTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        init();
        listener();
    }

    private void listener() {

    }

    private void init() {
        Intent intent = getIntent();
        String result = intent.getExtras().getString("result");
        mREsultTxt = findViewById(R.id.result);
        mREsultTxt.setText(result);
    }
}
