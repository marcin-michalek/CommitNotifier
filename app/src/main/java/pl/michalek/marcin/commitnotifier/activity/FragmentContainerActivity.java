package pl.michalek.marcin.commitnotifier.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.michalek.marcin.commitnotifier.R;
import pl.michalek.marcin.commitnotifier.entity.Commit;
import pl.michalek.marcin.commitnotifier.fragment.GcmRegisterFragment;
import pl.michalek.marcin.commitnotifier.utils.Preferences;

import java.util.Date;

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

    //@TODO remove after testing
    Commit commit = new Commit();
    commit.setAuthor("marcin");
    commit.setName("commit name");
    commit.setTimestamp(new Date().getTime());
    commit.setStatus("status");
    Preferences.save(this, commit);

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
            .setMessage(Preferences.getGcmRegistrationId(this))
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
}
