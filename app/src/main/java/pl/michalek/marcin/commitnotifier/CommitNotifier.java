package pl.michalek.marcin.commitnotifier;

import android.app.Application;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by Marcin Michalek on 2015-05-15.
 * File belongs to project SendIt!
 */
public class CommitNotifier extends Application {
  @Override
  public void onCreate() {
    super.onCreate();
    initUniversalImageLoader();
  }

  private void initUniversalImageLoader() {
    ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
        .writeDebugLogs()
        .build();
    ImageLoader.getInstance().init(config);
  }
}
