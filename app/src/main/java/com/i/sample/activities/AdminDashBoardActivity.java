package com.i.sample.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.i.sample.R;

public class AdminDashBoardActivity extends AppCompatActivity {

    TextView mAddQuestion, mAddCategory, mLogout, mReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        init();
        listener();
    }

    private void listener() {
        mAddQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashBoardActivity.this, AddQuestionsActivity.class));
            }
        });

        mAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashBoardActivity.this, AddCategoryActivity.class));
            }
        });

        mReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashBoardActivity.this, ReportActivity.class));
            }
        });
    }

    private void init() {
        mAddQuestion = findViewById(R.id.questions);
        mAddCategory = findViewById(R.id.category);
        mReport = findViewById(R.id.report);
        mLogout = findViewById(R.id.logout);
    }
}
