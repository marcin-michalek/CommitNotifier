package pl.michalek.marcin.commitnotifier.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.michalek.marcin.commitnotifier.R;
import pl.michalek.marcin.commitnotifier.fragment.GcmRegisterFragment;
import pl.michalek.marcin.commitnotifier.utils.Preferences;

/**
 * Created by Marcin Michalek on 2015-05-15.
 * File belongs to project SendIt!
 */
public class FragmentContainerActivity extends BaseActivity implements ContentReplacer {
  private static final String SHOW_FRAGMENT = FragmentContainerActivity.class.getPackage() + "SHOW_FRAGMENT";

  @InjectView(R.id.container)
  FrameLayout container;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_fragment_container);
    ButterKnife.inject(this);
    replaceFragment(new GcmRegisterFragment());
  }

  public void replaceFragment(Fragment fragment) {
    getSupportFragmentManager().beginTransaction()
        .setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out, R.anim.abc_fade_in, R.anim.abc_fade_out)
        .replace(R.id.container, fragment)
        .commit();
  }

  @Override
  public void replaceFragment(Fragment fragment, String backStack) {
    getSupportFragmentManager().beginTransaction()
        .setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out, R.anim.abc_fade_in, R.anim.abc_fade_out)
        .replace(R.id.container, fragment)
        .addToBackStack(backStack)
        .commit();
  }

  public void showFragment(String fragmentName) {
    replaceFragment(Fragment.instantiate(this, fragmentName));
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_settings, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle item selection
    switch (item.getItemId()) {
      case R.id.action_settings:
        new AlertDialog.Builder(this)
            .setTitle(getString(R.string.gcm_registration_id))
            .setView(getSelectableTextView())
            .setNeutralButton(getString(R.string.roger_that), new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
              }
            })
            .create()
            .show();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  private TextView getSelectableTextView() {
    TextView textView = new TextView(this);
    textView.setText(Preferences.getGcmRegistrationId(this));
    int textViewPadding = (int) getResources().getDimension(R.dimen.aler_dialog_content_padding);
    textView.setPadding(textViewPadding, textViewPadding, textViewPadding, textViewPadding);
    textView.setOnLongClickListener(new View.OnLongClickListener() {
      @Override
      public boolean onLongClick(View view) {
        startSendRegistrationIdInEmailIntent(Preferences.getGcmRegistrationId(FragmentContainerActivity.this));
        return true;
      }
    });

    return textView;
  }

  private void startSendRegistrationIdInEmailIntent(String registrationId) {
    Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
    emailIntent.setType("plain/text");
    emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getResources().getString(R.string.gcm_registration_id));
    emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, registrationId);
    startActivity(emailIntent);
  }
}
