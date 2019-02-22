package com.i.sample.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.i.sample.R;
import com.i.sample.database.AppDatabase;
import com.i.sample.database.models.Category;

import java.util.List;

public class AddCategoryActivity extends AppCompatActivity {

    String TAG = AddCategoryActivity.this.toString();

    EditText mCategoryEdit;

    private AppDatabase myAppDatabase = null;
    Button mSubmit;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        init();
        listener();
    }

    private void listener() {
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCategory();
            }
        });
    }

    private void addCategory() {

        String name = getEditTextString(mCategoryEdit);
        if (name != null) {
            if (name.length() > 0) {
                Category aCategory = new Category();
                aCategory.name = name;
                myAppDatabase.CategoryDao().insertCategory(aCategory);
                List<String> aString = myAppDatabase.CategoryDao().getCategoryNameList();
                for (int i = 0; i < aString.size(); i++) {
                    Log.d(TAG, "addCategory: " + aString.get(i));
                }
              //  Log.d(TAG, "addCategory: " + myAppDatabase.CategoryDao().getCategoryNameList().size());
            }
        }
    }

    private void init() {
        mContext = this;
        myAppDatabase = AppDatabase.getDatabase(mContext);
        mCategoryEdit = findViewById(R.id.category);
        mSubmit = findViewById(R.id.btn_submit);
    }

    private String getEditTextString(EditText aEdit) {
        return aEdit.getText().toString().trim();
    }
}
