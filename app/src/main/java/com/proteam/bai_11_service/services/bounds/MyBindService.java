package com.proteam.bai_11_service.services.bounds;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.proteam.bai_11_service.R;

public class MyBindService extends Service {
    private static final String TAG = MyBindService.class.getName();
    private MediaPlayer myPlayer;
    private IBinder binder;

    // phương thức khởi tạo
    @Override
    public void onCreate() {
        Log.d(TAG, "Đã gọi onCreate()");
        myPlayer = MediaPlayer.create(this.getApplicationContext(), R.raw.mysong);
        myPlayer.setLooping(false); // Set looping
        binder = new MyBinder(); // do MyBinder được extends Binder
    }

    // Bắt đầu một Service
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "Đã gọi onBind()");
        myPlayer.start();
        // trả về đối tượng binder cho ActivityMain
        return binder;
    }

    // Kết thúc một Service
    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "Đã gọi onUnBind()");
        myPlayer.stop();
        return super.onUnbind(intent);
    }

    // Xây dựng các phương thức thực hiện nhiệm vụ,
    // ở đây mình demo phương thức tua bài hát
    public void fastForward() {
        myPlayer.seekTo(60000); // tua đến giây thứ 60
    }

    public class MyBinder extends Binder {

        // phương thức này trả về đối tượng MyBindService
        public MyBindService getService() {
            return MyBindService.this;
        }
    }
}
 
