package com.proteam.bai_11_service.broadcasts;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;

import com.proteam.bai_11_service.R;

import java.text.SimpleDateFormat;

public class ExampleBroadcast extends BroadcastReceiver {
    private Context mContext;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss");

    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;
        final String action = intent.getAction();

        if (action.equals(Intent.ACTION_TIME_CHANGED) ||
                action.equals(Intent.ACTION_TIMEZONE_CHANGED)) {
            showNotification();
        }
    }

    /**
     * Show notification
     */
    private void showNotification() {
        //save data when show
        // Instantiate a Builder object.
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(mContext.getString(R.string.app_name))
                .setContentText("This is content of Notification")
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setAutoCancel(true)//co the cancel
                .setOngoing(true);//khong the keo qua de tat
        builder.setVibrate(new long[]{1000, 1000});

        // Creates an Intent for the Activity
        Intent notifyIntent = new Intent(mContext, BroadcastActivity.class);

        // Creates the PendingIntent
        //co the getBroadcast,getService
        PendingIntent notifyPendingIntent =
                PendingIntent.getActivity(
                        mContext,
                        0,
                        notifyIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        // Puts the PendingIntent into the notification builder
        builder.setContentIntent(notifyPendingIntent);
        // Notifications are issued by sending them to the
        // NotificationManager system service.
        NotificationManager mNotificationManager =
                (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        // Builds an anonymous Notification object from the builder, and
        // passes it to the NotificationManager
        mNotificationManager.notify(0, builder.build());
    }
}
