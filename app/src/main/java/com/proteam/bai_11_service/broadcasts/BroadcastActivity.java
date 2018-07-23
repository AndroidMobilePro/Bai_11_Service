package com.proteam.bai_11_service.broadcasts;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.proteam.bai_11_service.R;

public class BroadcastActivity extends AppCompatActivity {
    private ExampleBroadcast mBroadCastReceiver = new ExampleBroadcast();
    private Button mBtnChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_broadcast);
        mBtnChange = (Button) findViewById(R.id.btnRegistryTimeChanged);

        mBtnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(Intent.ACTION_TIME_TICK);
                intentFilter.addAction(Intent.ACTION_TIMEZONE_CHANGED);
                intentFilter.addAction(Intent.ACTION_TIME_CHANGED);
                registerReceiver(mBroadCastReceiver, intentFilter);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadCastReceiver);
    }

}
