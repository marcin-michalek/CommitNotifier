/**
 * Created by MiQUiDO on 18.08.2014.
 * <p/>
 * Copyright 2014 MiQUiDO <http://www.miquido.com/>. All rights reserved.
 */
package pl.michalek.marcin.commitnotifier.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import pl.michalek.marcin.commitnotifier.R;
import pl.michalek.marcin.commitnotifier.activity.FragmentContainerActivity;
import pl.michalek.marcin.commitnotifier.entity.Commit;
import pl.michalek.marcin.commitnotifier.receiver.GcmBroadcastReceiver;
import pl.michalek.marcin.commitnotifier.utils.Preferences;

public class GcmIntentService extends IntentService {
  public static final int NOTIFICATION_ID = 1;
  private NotificationManager notificationManager;

  public GcmIntentService() {
    super("GcmIntentService");
    notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
  }

  @Override
  protected void onHandleIntent(Intent intent) {
    Bundle extras = intent.getExtras();
    String messageType = GoogleCloudMessaging.getInstance(this)
        .getMessageType(intent);

    if (!extras.isEmpty()) {
      if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
        Commit commit = Commit.from(extras);
        Preferences.save(getApplicationContext(), commit);
        displayNotification(commit);
      }
    }
    GcmBroadcastReceiver.completeWakefulIntent(intent);
  }

  private void displayNotification(Commit commit) {
    Intent startMainActivityIntent = new Intent(this, FragmentContainerActivity.class);
    // put commit data
//    startMainActivityIntent.putExtra(Constants.START_ACTIVITY_AFTER_NOTIFICATION_CLICKED, MyCarePlanActivity.class.getName());
    PendingIntent contentIntent = PendingIntent.getActivity(this, 0, startMainActivityIntent, 0);

    NotificationCompat.Builder notificationBuilder =
        new NotificationCompat.Builder(this)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("titile")
            .setAutoCancel(true)
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            .setStyle(new NotificationCompat.BigTextStyle()
                .bigText(""))
            .setTicker("")
            .setVibrate(new long[]{1000, 1000, 2000})
            .setContentText("");

    notificationBuilder.setContentIntent(contentIntent);
    notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build());
  }
}
