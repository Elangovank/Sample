package com.i.sample.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.i.sample.R;
import com.i.sample.database.AppDatabase;

public class AdminDashBoardActivity extends AppCompatActivity {

    TextView mAddQuestion, mAddCategory, mLogout, mReport;
    private AppDatabase myAppDatabase = null;
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
               if (myAppDatabase.CategoryDao().getCategoryList().size()>0)
               {
                   startActivity(new Intent(AdminDashBoardActivity.this, AddQuestionsActivity.class));
               }
               else{
                   Printmsg("Please add atleast one Category");
               }

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

        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashBoardActivity.this, MainActivity.class));
            }
        });
    }

    private void init() {
        myAppDatabase = AppDatabase.getDatabase(this);
        mAddQuestion = findViewById(R.id.questions);
        mAddCategory = findViewById(R.id.category);
        mReport = findViewById(R.id.report);
        mLogout = findViewById(R.id.logout);
    }
    private void Printmsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
