package com.i.sample.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.i.sample.R;
import com.i.sample.database.AppDatabase;
import com.i.sample.database.models.Questions;
import com.i.sample.database.models.Results;

import java.util.ArrayList;
import java.util.List;

public class AttendQuizActivity extends AppCompatActivity {
    String TAG = AttendQuizActivity.this.toString();
    TextView mQuestionText;

    CheckBox mOptionA, mOptionB, mOptionC, mOptionD;

    private AppDatabase myAppDatabase = null;
    Button mSubmit;
    Context mContext;
    List<Questions> mQuestions;
    int NoOfQuestionAttended = 0;
    int NoOfCorrectAnswered = 0;
    String mCategoryName= "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attend_questions);
        init();
        listener();
        getData();
    }

    public void setQuestion() {
        if (mQuestions.size() > NoOfQuestionAttended) {
            mQuestionText.setText(mQuestions.get(NoOfQuestionAttended).question);
            mOptionA.setChecked(false);
            mOptionB.setChecked(false);
            mOptionC.setChecked(false);
            mOptionD.setChecked(false);
            mOptionA.setText(mQuestions.get(NoOfQuestionAttended).optionA);
            mOptionB.setText(mQuestions.get(NoOfQuestionAttended).optionB);
            mOptionC.setText(mQuestions.get(NoOfQuestionAttended).optionC);
            mOptionD.setText(mQuestions.get(NoOfQuestionAttended).optionD);
        }
    }

    private void getData() {
        mQuestions = myAppDatabase.QuestionsDao().getAllQuestions();
        setQuestion();
    }

    private void listener() {
        mOptionA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mOptionB.setChecked(false);
                    mOptionC.setChecked(false);
                    mOptionD.setChecked(false);
                }
            }
        });
        mOptionB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mOptionA.setChecked(false);
                    mOptionC.setChecked(false);
                    mOptionD.setChecked(false);
                }
            }
        });
        mOptionC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mOptionB.setChecked(false);
                    mOptionA.setChecked(false);
                    mOptionD.setChecked(false);
                }
            }
        });

        mOptionD.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mOptionB.setChecked(false);
                    mOptionC.setChecked(false);
                    mOptionA.setChecked(false);
                }
            }
        });

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mOptionA.isChecked()) {
                    if (mQuestions.get(NoOfQuestionAttended).answer.equalsIgnoreCase("A")) {
                        NoOfCorrectAnswered++;
                    }
                } else if (mOptionB.isChecked()) {
                    if (mQuestions.get(NoOfQuestionAttended).answer.equalsIgnoreCase("B")) {
                        NoOfCorrectAnswered++;
                    }
                } else if (mOptionC.isChecked()) {
                    if (mQuestions.get(NoOfQuestionAttended).answer.equalsIgnoreCase("C")) {
                        NoOfCorrectAnswered++;
                    }
                } else if (mOptionD.isChecked()) {
                    if (mQuestions.get(NoOfQuestionAttended).answer.equalsIgnoreCase("D")) {
                        NoOfCorrectAnswered++;
                    }
                } else {

                    Toast.makeText(mContext, "Please select answer", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (NoOfQuestionAttended >= 9) {
                    Results aResult = new Results();

                    aResult.categoryname = "";
                    aResult.noofCorrectAnswer = NoOfCorrectAnswered + "";
                    aResult.noofquestions = NoOfQuestionAttended + "";
                    aResult.userId = 0;
                    aResult.status = "";


                    myAppDatabase.ResultDao().insert(aResult);
                    Intent i = new Intent(AttendQuizActivity.this, ResultDashBoardActivity.class);
                    i.putExtra("result", NoOfCorrectAnswered + " / " + NoOfQuestionAttended);
                    startActivity(i);
                    finish();
                } else {
                    NoOfQuestionAttended++;
                    setQuestion();
                }
            }
        });
    }


    private void init() {
        try {
            mContext = this;
            Intent intent = getIntent();
            mCategoryName = intent.getExtras().getString("categoryName");
            mQuestions = new ArrayList<>();
            myAppDatabase = AppDatabase.getDatabase(mContext);
            mQuestionText = findViewById(R.id.question);
            mSubmit = findViewById(R.id.btn_submit);
            mOptionA = findViewById(R.id.answer_a);
            mOptionB = findViewById(R.id.answer_b);
            mOptionC = findViewById(R.id.answer_c);
            mOptionD = findViewById(R.id.answer_d);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private String getEditTextString(EditText aEdit) {
        return aEdit.getText().toString().trim();
    }
}
