package com.mohheader.wally;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mohheader.wally.models.User;


public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        checkIfLogedIn();

        final EditText loginName = (EditText)findViewById(R.id.login_name);
        loginName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 0) {
                    findViewById(R.id.login_bt).setVisibility(View.VISIBLE);
                } else {
                    findViewById(R.id.login_bt).setVisibility(View.INVISIBLE);
                }
            }
        });

        findViewById(R.id.login_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User.setUseName(LoginActivity.this, loginName.getText().toString());

                Intent i = new Intent(LoginActivity.this, WallActivity.class);
                startActivity(i);
            }
        });
    }

    private void checkIfLogedIn() {
        if(User.isLogedIn(this) ){
            Intent i = new Intent(LoginActivity.this, WallActivity.class);
            startActivity(i);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
