package com.i.sample.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.i.sample.R;
import com.i.sample.adapter.AdminReportAdapter;
import com.i.sample.database.AppDatabase;
import com.i.sample.database.models.Category;
import com.i.sample.database.models.Results;

import java.util.ArrayList;
import java.util.List;

public class ReportActivity extends AppCompatActivity {


    RecyclerView myRecycleView;
    AdminReportAdapter mReportAdapter;
    private List<Results> myResultsAL;
    private AppDatabase myAppDatabase = null;
    View mNoDataFound;
    Spinner mSpinner;
    List<Category> mString;
    String mCategoryStr = "";
    Boolean isFirst = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_admin);

        init();
        listener();
    }

    private void listener() {
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!isFirst) {
                    mCategoryStr = mString.get(position).name;
                    myResultsAL = myAppDatabase.ResultDao().getresultbyCategory(mCategoryStr);
                    mReportAdapter.update(myResultsAL);
                    if (myResultsAL.size() > 0) {
                        myRecycleView.setVisibility(View.VISIBLE);
                        mNoDataFound.setVisibility(View.GONE);
                    } else {
                        myRecycleView.setVisibility(View.GONE);
                        mNoDataFound.setVisibility(View.VISIBLE);
                    }
                }
                isFirst = false;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setAdapter() {

        mString = myAppDatabase.CategoryDao().getCategoryList();
        String[] aData = new String[mString.size()];
        for (int i = 0; i < mString.size(); i++) {
            aData[i] = mString.get(i).name;
        }
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, aData);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(aa);
    }

    private void init() {
        myResultsAL = new ArrayList<>();
        mSpinner = findViewById(R.id.spinner);
        myAppDatabase = AppDatabase.getDatabase(this);
        setAdapter();
        myRecycleView = findViewById(R.id.recycle);
        mNoDataFound = findViewById(R.id.layout_no_data_found);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myRecycleView.setLayoutManager(layoutManager);
        myRecycleView.setItemAnimator(new DefaultItemAnimator());
        myResultsAL = myAppDatabase.ResultDao().getresult();
        mReportAdapter = new AdminReportAdapter(this, myResultsAL);
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
