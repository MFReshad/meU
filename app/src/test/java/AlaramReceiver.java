package com.example.meu;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.api.Billing;

import java.io.NotActiveException;

public class  AlaramReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent  i =  new Intent(context, DestinationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivities(context,requestCode:0,i,flag: 0);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,channelld:"foxandroid")

        .builder.setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Foxandroid meu")
                .setContentText("Android Related Content")
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)



                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context)
                        notificationManagerCompat.notify(id: 123,builder.build());

    }
}
