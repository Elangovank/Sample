package com.i.sample.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.i.sample.Prefs;
import com.i.sample.R;
import com.i.sample.adapter.ReportAdapter;
import com.i.sample.database.AppDatabase;
import com.i.sample.database.models.Results;
import com.i.sample.database.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserReportActivity extends AppCompatActivity {


    RecyclerView myRecycleView;
    ReportAdapter mReportAdapter;
    private List<Results> myResultsAL;
    private AppDatabase myAppDatabase = null;
    View mNoDataFound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_user);

        init();
        listener();
    }

    private void listener() {

    }

    private void init() {
        myResultsAL = new ArrayList<>();
        User myUserDetail = Prefs.getObject("user_details", User.class);
        myAppDatabase = AppDatabase.getDatabase(this);
        myRecycleView = findViewById(R.id.recycle);
        mNoDataFound = findViewById(R.id.layout_no_data_found);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myRecycleView.setLayoutManager(layoutManager);
        myRecycleView.setItemAnimator(new DefaultItemAnimator());
        myResultsAL = myAppDatabase.ResultDao().getresultbyUser(myUserDetail.id);
        mReportAdapter = new ReportAdapter(this, myResultsAL);
        myRecycleView.setAdapter(mReportAdapter);
        if (myResultsAL.size() > 0) {
            myRecycleView.setVisibility(View.VISIBLE);
            mNoDataFound.setVisibility(View.GONE);
        } else {
            myRecycleView.setVisibility(View.GONE);
            mNoDataFound.setVisibility(View.VISIBLE);
        }
    }
}
