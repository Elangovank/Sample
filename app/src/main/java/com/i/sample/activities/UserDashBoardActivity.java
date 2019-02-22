package com.i.sample.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.i.sample.R;

public class UserDashBoardActivity extends AppCompatActivity {

    TextView mTakeQuiz, mLogout, mReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        init();
        listener();
    }

    private void listener() {
        mTakeQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserDashBoardActivity.this,SelectCategoryActivity.class));
            }
        });
    }

    private void init() {
        mTakeQuiz = findViewById(R.id.take_quiz);
        mReport = findViewById(R.id.view_result);
        mLogout = findViewById(R.id.logout);
    }
}
