package pl.michalek.marcin.commitnotifier.config;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * Different display image options for Universal Image Loader.
 *
 * @author Marcin Michałek
 */
public final class ImageDisplayShape {
  private ImageDisplayShape() {
  }

  public static final DisplayImageOptions CIRCLE = new DisplayImageOptions.Builder()
      .resetViewBeforeLoading(false)
      .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
      .cacheInMemory(false)
      .cacheOnDisk(true)
      .displayer(new RoundedBitmapDisplayer(90))
      .build();

  public static final DisplayImageOptions ROUNDED_RECTANGLE = new DisplayImageOptions.Builder()
      .resetViewBeforeLoading(false)
      .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
      .cacheInMemory(false)
      .cacheOnDisk(true)
      .displayer(new RoundedBitmapDisplayer(45))
      .build();

  public static final DisplayImageOptions SQUARE = new DisplayImageOptions.Builder()
      .resetViewBeforeLoading(false)
      .cacheInMemory(false)
      .cacheOnDisc(true)
      .displayer(new FadeInBitmapDisplayer(100))
      .build();
}
