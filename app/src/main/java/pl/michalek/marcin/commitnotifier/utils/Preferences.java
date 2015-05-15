package pl.michalek.marcin.commitnotifier.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Marcin Michalek on 2015-05-15.
 * File belongs to project SendIt!
 */
public final class Preferences {
  private static final String GCM_REGISTRATION_ID = Preferences.class.getPackage() + "GCM_REGISTRATION_ID";
  private static final String GCM_APP_VERSION = Preferences.class.getPackage() + "GCM_APP_VERSION";

  private Preferences() {
  }

  public static SharedPreferences from(Context context) {
    return PreferenceManager.getDefaultSharedPreferences(context);
  }

  public static String getGcmRegistrationId(Context context) {
    return Preferences.from(context)
        .getString(GCM_REGISTRATION_ID, "");
  }

  public static int getAppVersion(Activity activity) {
    return Preferences.from(activity)
        .getInt(GCM_APP_VERSION, Integer.MIN_VALUE);
  }

  public static void putRegistrationId(Activity activity, String gcmRegistrationId) {
    Preferences.from(activity)
        .edit()
        .putString(GCM_REGISTRATION_ID, gcmRegistrationId)
        .apply();
  }

  public static void putAppVersion(Activity activity, int appVersion) {
    Preferences.from(activity)
        .edit()
        .putInt(GCM_APP_VERSION, appVersion)
        .apply();
  }
}
