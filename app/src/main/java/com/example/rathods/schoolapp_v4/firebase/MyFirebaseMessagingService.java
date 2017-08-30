package com.example.rathods.schoolapp_v4.firebase;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.example.rathods.schoolapp_v4.R;
import com.example.rathods.schoolapp_v4.User.UserHome;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();
    private Context mContext;
    SharedPreferences sharedPreferences;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e(TAG, "From: " + remoteMessage.getFrom());
        this.mContext = getApplicationContext();
        sharedPreferences = mContext.getSharedPreferences("schoolapp", Context.MODE_PRIVATE);
        if (remoteMessage == null)
            return;

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.e(TAG, "Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.e(TAG, "Data Payload: " + remoteMessage.getData().toString());

            final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                    mContext);
            try {
                JSONObject mainObject = new JSONObject(remoteMessage.getData().toString());
                JSONObject jsonObject = mainObject.getJSONObject("data");
                final PendingIntent resultPendingIntent =
                        PendingIntent.getActivity(
                                mContext,
                                0,
                                new Intent(mContext, UserHome.class),
                                PendingIntent.FLAG_CANCEL_CURRENT
                        );

                final Uri alarmSound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                        + "://" + mContext.getPackageName() + "/raw/notification");
                if (sharedPreferences.contains("user_id") && sharedPreferences.getString("user_id", "").toString().trim().length() > 0)
                    showSmallNotification(mBuilder, R.drawable.logo, jsonObject.getString("title"), jsonObject.getString("message"), jsonObject.getString("timestamp"), resultPendingIntent, alarmSound);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    private void showSmallNotification(NotificationCompat.Builder mBuilder, int icon, String title, String message, String timeStamp, PendingIntent resultPendingIntent, Uri alarmSound) {

        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

        inboxStyle.addLine(message);

        Notification notification;
        notification = mBuilder.setSmallIcon(icon).setTicker(title).setWhen(0)
                .setAutoCancel(true)
                .setContentTitle(title)
                .setContentIntent(resultPendingIntent)
                .setSound(alarmSound)
                .setStyle(inboxStyle)
                .setWhen(getTimeMilliSec(timeStamp))
                .setSmallIcon(R.drawable.ic_stat_ac_unit)
                .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), icon))
                .setContentText(message)
                .build();

        NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
    }


    public static long getTimeMilliSec(String timeStamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = format.parse(timeStamp);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}