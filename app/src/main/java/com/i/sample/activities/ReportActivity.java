package com.i.sample.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.i.sample.R;
import com.i.sample.adapter.ReportAdapter;
import com.i.sample.database.AppDatabase;
import com.i.sample.database.models.Results;

import java.util.ArrayList;
import java.util.List;

public class ReportActivity extends AppCompatActivity {


    RecyclerView myRecycleView;
    ReportAdapter mReportAdapter;
    private List<Results> myResultsAL;
    private AppDatabase myAppDatabase = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_admin);

        init();
        listener();
    }

    private void listener() {

    }

    private void init() {
        myResultsAL = new ArrayList<>();
        myAppDatabase = AppDatabase.getDatabase(this);
        myRecycleView = findViewById(R.id.recycle);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myRecycleView.setLayoutManager(layoutManager);
        myRecycleView.setItemAnimator(new DefaultItemAnimator());
        myResultsAL = myAppDatabase.ResultDao().getresult();
        mReportAdapter = new ReportAdapter(this, myResultsAL);
        myRecycleView.setAdapter(mReportAdapter);
    }
}
