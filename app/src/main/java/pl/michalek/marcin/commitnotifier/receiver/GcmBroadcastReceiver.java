/**
 * Created by MiQUiDO on 18.08.2014.
 *
 * Copyright 2014 MiQUiDO <http://www.miquido.com/>. All rights reserved.
 */
package pl.michalek.marcin.commitnotifier.receiver;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import pl.michalek.marcin.commitnotifier.service.GcmIntentService;

public class GcmBroadcastReceiver extends WakefulBroadcastReceiver {
  @Override
  public void onReceive(Context context, Intent intent) {
    ComponentName comp = new ComponentName(context.getPackageName(),
        GcmIntentService.class.getName());
    startWakefulService(context, intent.setComponent(comp));
    setResultCode(Activity.RESULT_OK);
  }
}
