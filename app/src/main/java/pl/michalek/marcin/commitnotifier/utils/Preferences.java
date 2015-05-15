package pl.michalek.marcin.commitnotifier.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import pl.michalek.marcin.commitnotifier.entity.Commit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Marcin Michalek on 2015-05-15.
 * File belongs to project SendIt!
 */
public final class Preferences {
  private static final String GCM_REGISTRATION_ID = Preferences.class.getPackage() + "GCM_REGISTRATION_ID";
  private static final String GCM_APP_VERSION = Preferences.class.getPackage() + "GCM_APP_VERSION";
  private static final String COMMITS = Preferences.class.getPackage() + "COMMITS";

  private Preferences() {
  }

  public static SharedPreferences from(Context context) {
    return PreferenceManager.getDefaultSharedPreferences(context);
  }

  public static String getGcmRegistrationId(Context context) {
    return Preferences.from(context)
        .getString(GCM_REGISTRATION_ID, "");
  }

  public static int getAppVersion(Context context) {
    return Preferences.from(context)
        .getInt(GCM_APP_VERSION, Integer.MIN_VALUE);
  }

  public static void putRegistrationId(Context context, String gcmRegistrationId) {
    Preferences.from(context)
        .edit()
        .putString(GCM_REGISTRATION_ID, gcmRegistrationId)
        .apply();
  }

  public static void putAppVersion(Context context, int appVersion) {
    Preferences.from(context)
        .edit()
        .putInt(GCM_APP_VERSION, appVersion)
        .apply();
  }

  public static void save(Context context, Commit commit) {
    SharedPreferences sharedPreferences = Preferences.from(context);
    Set<String> commits = sharedPreferences
        .getStringSet(COMMITS, new HashSet<String>());
    removeCommits(context);
    if (commits != null) {
      commits.add(commit.toJson());
      sharedPreferences
          .edit()
          .putStringSet(COMMITS, commits)
          .apply();
    }
  }

  public static void removeCommits(Context context) {
    Preferences.from(context)
        .edit()
        .remove(COMMITS)
        .apply();
  }

  public static List<Commit> getCommits(Context context) {
    Set<String> commitSet = Preferences.from(context)
        .getStringSet(COMMITS, new HashSet<String>());
    List<Commit> commitList = new ArrayList<>();
    if (commitSet != null) {
      for (String commitJson : commitSet) {
        commitList.add(Commit.from(commitJson));
      }
    }
    return commitList;
  }
}
