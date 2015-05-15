package pl.michalek.marcin.commitnotifier.activity;

import android.support.v4.app.Fragment;

/**
 * Created by Marcin Michalek on 2015-05-15.
 * File belongs to project SendIt!
 */
public interface ContentReplacer {
  void replaceFragment(Fragment fragment);

  void replaceFragment(Fragment fragment, String addToBackStack);

  void showFragment(String fragmentName);
}
