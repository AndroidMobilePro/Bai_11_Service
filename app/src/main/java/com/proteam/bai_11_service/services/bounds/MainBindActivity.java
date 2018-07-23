package com.proteam.bai_11_service.services.bounds;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.proteam.bai_11_service.R;

public class MainBindActivity extends AppCompatActivity {
    private MyBindService myService;
    private boolean isBound = false;
    private ServiceConnection connection;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_bind_main);

        final Button btOn = (Button) findViewById(R.id.btOn);
        final Button btOff = (Button) findViewById(R.id.btOff);
        final Button btFast = (Button) findViewById(R.id.btTua);

        // Khởi tạo ServiceConnection
        connection = new ServiceConnection() {

            // Phương thức này được hệ thống gọi khi kết nối tới service bị lỗi
            @Override
            public void onServiceDisconnected(ComponentName name) {
                isBound = false;
            }

            // Phương thức này được hệ thống gọi khi kết nối tới service thành công
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                MyBindService.MyBinder binder = (MyBindService.MyBinder) service;
                myService = binder.getService(); // lấy đối tượng MyBindService
                isBound = true;
            }
        };

        // Khởi tạo intent
        final Intent intent = new Intent(MainBindActivity.this, MyBindService.class);

        btOn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Bắt đầu một service sủ dụng bind
                bindService(intent, connection, Context.BIND_AUTO_CREATE);
                // Đối thứ ba báo rằng Service sẽ tự động khởi tạo
            }
        });

        btOff.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Nếu Service đang hoạt động
                if (isBound) {
                    // Tắt Service
                    unbindService(connection);
                    isBound = false;
                }
            }
        });

        btFast.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // nếu service đang hoạt động
                if (isBound) {
                    // tua bài hát
                    myService.fastForward();
                } else {
                    Toast.makeText(MainBindActivity.this, "Service chưa hoạt động", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}