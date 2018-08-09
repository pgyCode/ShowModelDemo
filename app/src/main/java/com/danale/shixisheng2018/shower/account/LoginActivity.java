package com.danale.shixisheng2018.shower.account;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.danale.shixisheng2018.shower.R;
import com.danale.shixisheng2018.shower.main.MainActivity;
import com.danale.shixisheng2018.shower.model.AccountModel;
import com.danale.shixisheng2018.shower.parent.OnWeakTaskListener;
import com.danale.shixisheng2018.shower.parent.WeakTask;
import com.danale.shixisheng2018.shower.tool.App;
import com.google.android.material.snackbar.Snackbar;

import java.security.interfaces.ECKey;
import java.util.logging.Level;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewStub loading;
    private EditText edtId;
    private EditText edtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        initLayout();
    }

    private void initLayout(){
        loading = findViewById(R.id.loading);
        edtId = findViewById(R.id.edt_id);
        edtPass = findViewById(R.id.edt_pass);

        findViewById(R.id.btn_login).setOnClickListener(this);
        findViewById(R.id.btn_find).setOnClickListener(this);
        findViewById(R.id.btn_regist).setOnClickListener(this);

        if (AccountModel.getInstance().userBean != null){
            startActivity(MainActivity.class);
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_login){
            new WeakTask<Integer>(new LoginWeakTaskListener()).execute();
        } else if (view.getId() == R.id.btn_find){
            Snackbar.make(findViewById(R.id.root), "暂时不提供找回功能", Snackbar.LENGTH_SHORT).show();
        } else if (view.getId() == R.id.btn_regist) {
            Snackbar.make(findViewById(R.id.root), "暂时不提供注册功能", Snackbar.LENGTH_SHORT).show();
        }
    }


    private class LoginWeakTaskListener implements OnWeakTaskListener<Integer>{

        @Override
        public void before() {
            loading.setVisibility(View.VISIBLE);
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(edtPass.getWindowToken(), 0);
        }

        @Override
        public Integer middle() {
            return AccountModel.getInstance().netLogin(edtId.getText().toString().trim(), edtPass.getText().toString().trim());
        }

        @Override
        public void after(Integer integer) {
            loading.setVisibility(View.GONE);
            if (integer == App.NET_SUCCEED){
                startActivity(MainActivity.class);
                finish();
            } else {
                Snackbar.make(findViewById(R.id.root), "请检查你的网络设置", Snackbar.LENGTH_SHORT).show();
            }
        }
    }

    private void startActivity(Class<? extends Activity> c){
        startActivity(new Intent(this, c));
    }
}
