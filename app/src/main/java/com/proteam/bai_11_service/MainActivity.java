package com.proteam.bai_11_service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.proteam.bai_11_service.broadcasts.BroadcastActivity;
import com.proteam.bai_11_service.services.bounds.MainBindActivity;
import com.proteam.bai_11_service.services.intent_service.ExampleIntentActivity;
import com.proteam.bai_11_service.services.un_bounds.MainStartActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnNotification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BroadcastActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnBindService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainBindActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnStartService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainStartActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnIntentService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ExampleIntentActivity.class);
                startActivity(intent);
            }
        });
    }
}
