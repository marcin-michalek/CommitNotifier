package pl.michalek.marcin.commitnotifier.fragment;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import pl.michalek.marcin.commitnotifier.R;
import pl.michalek.marcin.commitnotifier.activity.BaseActivity;
import pl.michalek.marcin.commitnotifier.config.Constants;
import pl.michalek.marcin.commitnotifier.utils.Preferences;

import java.io.IOException;

/**
 * Created by Marcin Michalek on 2015-05-15.
 * File belongs to project SendIt!
 */
public class GcmRegisterFragment extends BaseFragment {

  @InjectView(R.id.progressBar)
  ProgressBar progressBar;

  @InjectView(R.id.infoTextView)
  TextView infoTextView;

  private GoogleCloudMessaging gcm;
  private String gcmRegistrationId;


  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    BaseActivity baseActivity = (BaseActivity) getActivity();
    if (baseActivity != null) {
      gcm = GoogleCloudMessaging.getInstance(getActivity());
      checkIfGooglePlayServicesAreAvailable(getActivity());
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_gcm_register, container, false);
    ButterKnife.inject(this, view);
    return view;
  }

  //  GCM METHODS
  private void checkIfGooglePlayServicesAreAvailable(Activity activity) {
    int googlePlayServicesResponseCode;
    if ((googlePlayServicesResponseCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity)) != ConnectionResult.SUCCESS) {
      GooglePlayServicesUtil.getErrorDialog(googlePlayServicesResponseCode, activity, 0).show();
    }

    gcmRegistrationId = getRegistrationId(activity);
    if (gcmRegistrationId.isEmpty()) {
      registerInBackground(activity);
    } else {
      Log.d(Constants.LOG_TAG, "Device registered, will now collect login information");
      setRegistrationSuccess(activity);
    }
  }

  private String getRegistrationId(Activity activity) {
    String registrationId = Preferences.getGcmRegistrationId(activity);
    if (registrationId.isEmpty() || appVersionChanged(activity)) {
      Log.d(Constants.LOG_TAG, "Registration not found or app version changed.");
      return "";
    }
    return registrationId;
  }

  private boolean appVersionChanged(Activity activity) {
    return Preferences.getAppVersion(activity) != getAppVersion(activity);
  }

  private void registerInBackground(final Activity activity) {
    Runnable registerInBackground = new Runnable() {
      @Override
      public void run() {
        try {
          gcmRegistrationId = gcm.register(Constants.GCM_SENDER_ID);
        } catch (IOException e) {
          Log.d(Constants.LOG_TAG, e.getMessage(), e);
        }
        Log.d(Constants.LOG_TAG, "Device registered");
        storeRegistrationId(activity);
      }
    };
    (new Thread(registerInBackground)).start();
  }

  private void storeRegistrationId(final Activity activity) {
    Preferences.putRegistrationId(activity, gcmRegistrationId);
    Preferences.putAppVersion(activity, getAppVersion(activity));

    activity.runOnUiThread(new Runnable() {
      @Override
      public void run() {
        setRegistrationSuccess(activity);
      }
    });
  }

  private void setRegistrationSuccess(Activity activity){
    progressBar.setVisibility(View.GONE);
    infoTextView.setText(activity.getString(R.string.success));
  }

  private static int getAppVersion(Activity activity) {
    try {
      PackageInfo packageInfo = activity.getPackageManager()
          .getPackageInfo(activity.getPackageName(), 0);
      return packageInfo.versionCode;
    } catch (PackageManager.NameNotFoundException e) {
      // should never happen
      Log.e(Constants.LOG_TAG, "Package name not found", e);
      return -1;
    }
  }
}
