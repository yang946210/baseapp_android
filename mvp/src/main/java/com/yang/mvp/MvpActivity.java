package com.yang.mvp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;



public class MvpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
    }

    public void Rxjava(View view){
        startActivity(new Intent(this,RxAndroidActivity.class));
    }
}