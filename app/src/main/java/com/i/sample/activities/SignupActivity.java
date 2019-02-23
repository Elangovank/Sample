package com.i.sample.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.i.sample.R;
import com.i.sample.database.AppDatabase;
import com.i.sample.database.models.User;

public class SignupActivity extends AppCompatActivity {

    EditText mUsernameEdt, mPasswordEdt, mEmailEdt, mMobileEdt, mConPasswordEdt;
    private AppDatabase myAppDatabase = null;
    Context mContext;
    Button mLoginBtn, mSignupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mContext = this;
        init();
        Listener();
    }

    private void Listener() {

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, MainActivity.class));
                finish();
            }
        });

        mSignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
    }

    private void validate() {
        if (getEditTextString(mUsernameEdt).length() == 0) {
            Printmsg("Please enter user name");
            return;
        } else if (getEditTextString(mPasswordEdt).length() == 0) {
            Printmsg("Please enter password");
            return;
        } else if (getEditTextString(mConPasswordEdt).length() == 0) {
            Printmsg("Please enter confirm password");
            return;
        } else if (!isValidMobile(getEditTextString(mMobileEdt))) {
            Printmsg("Please enter valid mobile number");
            return;
        } else if (!isValidMail(getEditTextString(mEmailEdt))) {
            Printmsg("Please enter valid email id");
            return;
        } else if (!(getEditTextString(mPasswordEdt).equals(getEditTextString(mConPasswordEdt)))) {
            Printmsg("Password not match !!!");
            return;
        } else {
            User aUser = new User();
            aUser.mobile = getEditTextString(mMobileEdt);
            aUser.name = getEditTextString(mUsernameEdt);
            aUser.email = getEditTextString(mEmailEdt);
            aUser.password = getEditTextString(mPasswordEdt);
            myAppDatabase.UserDao().insert(aUser);
            Printmsg("User created Successfully");
            startActivity(new Intent(SignupActivity.this, MainActivity.class));
            finish();
        }
    }

    private boolean isValidMail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidMobile(String phone) {
        return Patterns.PHONE.matcher(phone).matches();
    }

    private void init() {

        myAppDatabase = AppDatabase.getDatabase(mContext);

        mUsernameEdt = findViewById(R.id.name);
        mPasswordEdt = findViewById(R.id.password);
        mConPasswordEdt = findViewById(R.id.con_password);
        mEmailEdt = findViewById(R.id.email);
        mMobileEdt = findViewById(R.id.mobile);
        mLoginBtn = findViewById(R.id.btn_login);
        mSignupBtn = findViewById(R.id.btn_signup);
    }

    private String getEditTextString(EditText aEdit) {
        return aEdit.getText().toString().trim();
    }


    private void Printmsg(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
}
