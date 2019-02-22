package com.i.sample.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.i.sample.R;
import com.i.sample.database.AppDatabase;
import com.i.sample.database.models.Category;

import java.util.List;

public class SelectCategoryActivity extends AppCompatActivity {

    String TAG = SelectCategoryActivity.this.toString();

    Spinner mCategorySpinner;

    private AppDatabase myAppDatabase = null;
    Button mSubmit;
    Context mContext;
    List<Category> mString;
    String mCategoryStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_category);
        init();
        listener();
    }

    private void listener() {
        mCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mCategoryStr = mString.get(position).name;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SelectCategoryActivity.this, AttendQuizActivity.class);
                i.putExtra("categoryName", mCategoryStr);
                startActivity(i);
                finish();
              //  startActivity(new Intent(SelectCategoryActivity.this, AttendQuizActivity.class));
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
        mCategorySpinner.setAdapter(aa);
    }

    private void init() {
        mContext = this;
        myAppDatabase = AppDatabase.getDatabase(mContext);
        mCategorySpinner = findViewById(R.id.category);
        mSubmit = findViewById(R.id.btn_submit);
        setAdapter();
    }


}
