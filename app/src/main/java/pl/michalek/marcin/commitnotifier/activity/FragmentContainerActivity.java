package pl.michalek.marcin.commitnotifier.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;
import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.michalek.marcin.commitnotifier.R;
import pl.michalek.marcin.commitnotifier.fragment.GcmRegisterFragment;

/**
 * Created by Marcin Michalek on 2015-05-15.
 * File belongs to project SendIt!
 */
public class FragmentContainerActivity extends BaseActivity {
  @InjectView(R.id.container)
  FrameLayout container;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_fragment_container);
    ButterKnife.inject(this);
    replaceFragment(new GcmRegisterFragment());
  }

  protected void replaceFragment(Fragment fragment) {
    getSupportFragmentManager().beginTransaction()
        .replace(R.id.container, fragment)
        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
        .commit();
  }
}
