package com.i.sample.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.i.sample.R;
import com.i.sample.database.AppDatabase;
import com.i.sample.database.models.Category;
import com.i.sample.database.models.Questions;

import java.util.List;

public class AddQuestionsActivity extends AppCompatActivity {
    String TAG = AddQuestionsActivity.this.toString();
    EditText mQuestionEdit, mAnswer_A_Edit, mAnswer_B_Edit, mAnswer_C_Edit, mAnswer_D_Edit;
    RadioGroup mRadioGroup;
    RadioButton a, b, c, d;
    private AppDatabase myAppDatabase = null;
    Button mSubmit;
    Context mContext;
    Spinner mSpinner;
    String mCategoryStr;
    List<Category> mString;
    String mAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_questions);
        init();
        listener();
    }

    private void listener() {
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                int selectedId = mRadioGroup.getCheckedRadioButtonId();
                if (selectedId != -1) {
                    RadioButton radioButton = (RadioButton) findViewById(selectedId);
                    mAnswer = radioButton.getText().toString().toLowerCase();
                    addQuestion();
                } else {

                }
            }
        });
    }

    private void Printmsg(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    private void addQuestion() {

        Questions aQuestions = new Questions();
        aQuestions.question = getEditTextString(mQuestionEdit);
        aQuestions.optionA = getEditTextString(mAnswer_A_Edit);
        aQuestions.optionB = getEditTextString(mAnswer_B_Edit);
        aQuestions.optionC = getEditTextString(mAnswer_C_Edit);
        aQuestions.optionD = getEditTextString(mAnswer_D_Edit);
        aQuestions.answer = mAnswer;
        aQuestions.categoryName = mCategoryStr;
        myAppDatabase.QuestionsDao().insert(aQuestions);
        Printmsg("Added successfully");
        mQuestionEdit.setText("");
        mAnswer_A_Edit.setText("");
        mAnswer_B_Edit.setText("");
        mAnswer_C_Edit.setText("");
        mAnswer_D_Edit.setText("");
        a.setChecked(false);
        b.setChecked(false);
        c.setChecked(false);
        d.setChecked(false);
        //    Log.d(TAG, "addQuestion: " + myAppDatabase.QuestionsDao().getAllQuestions().size());
    }

    private void init() {
        mContext = this;
        myAppDatabase = AppDatabase.getDatabase(mContext);
        mQuestionEdit = findViewById(R.id.question);
        mAnswer_A_Edit = findViewById(R.id.option_a_answer);
        mAnswer_B_Edit = findViewById(R.id.option_b_answer);
        mAnswer_C_Edit = findViewById(R.id.option_c_answer);
        mAnswer_D_Edit = findViewById(R.id.option_d_answer);
        mAnswer_D_Edit = findViewById(R.id.option_d_answer);

        a = findViewById(R.id.answer_a);
        b = findViewById(R.id.answer_b);
        c = findViewById(R.id.answer_c);
        d = findViewById(R.id.answer_d);

        mSubmit = findViewById(R.id.btn_submit);
        mSpinner = findViewById(R.id.spinner);
        mRadioGroup = findViewById(R.id.radioGroup1);
        setAdapter();
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

    private String getEditTextString(EditText aEdit) {
        return aEdit.getText().toString().trim();
    }
}
