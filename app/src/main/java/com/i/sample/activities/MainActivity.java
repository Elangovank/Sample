package com.i.sample.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.i.sample.R;
import com.i.sample.database.AppDatabase;
import com.i.sample.database.models.User;

public class MainActivity extends AppCompatActivity {
    String TAG = MainActivity.this.toString();
    EditText mUsernameEdt, mPasswordEdt;
    private AppDatabase myAppDatabase = null;
    Context mContext;
    CheckBox mCheckbox;
    Button mLoginBtn, mSignupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = this;
        init();
        Listener();
    }

    private void Listener() {

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });

        mSignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignupActivity.class));

            }
        });
    }

    private void validate() {

        if (getEditTextString(mUsernameEdt).length() == 0) {
            return;
        } else if (getEditTextString(mPasswordEdt).length() == 0) {
            return;
        } else {
            if (mCheckbox.isChecked()) {
                if (getEditTextString(mUsernameEdt).equals("admin") && getEditTextString(mPasswordEdt).equals("admin")) {
                    startActivity(new Intent(MainActivity.this, AdminDashBoardActivity.class));
                }
            } else {
                User aUser = myAppDatabase.UserDao().getLoginUser(getEditTextString(mUsernameEdt), getEditTextString(mPasswordEdt));
                if (aUser != null)
                    startActivity(new Intent(MainActivity.this, UserDashBoardActivity.class));
            }
        }


    }

    private boolean isValidMail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidMobile(String phone) {
        return Patterns.PHONE.matcher(phone).matches();
    }

    private void init() {

        myAppDatabase = AppDatabase.getDatabase(mContext);
        mUsernameEdt = findViewById(R.id.username);
        mPasswordEdt = findViewById(R.id.password);
        mCheckbox = findViewById(R.id.checkbox);
        mLoginBtn = findViewById(R.id.btn_login);
        mSignupBtn = findViewById(R.id.btn_signup);


        Log.d("User size", "init: " + myAppDatabase.UserDao().getUsers().size());
        ;
    }

    private String getEditTextString(EditText aEdit) {
        return aEdit.getText().toString().trim();
    }

}
