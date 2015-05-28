package pl.michalek.marcin.commitnotifier.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.nostra13.universalimageloader.core.ImageLoader;
import pl.michalek.marcin.commitnotifier.R;
import pl.michalek.marcin.commitnotifier.entity.Commit;
import pl.michalek.marcin.commitnotifier.utils.Preferences;

import java.util.Date;
import java.util.List;

/**
 * Created by Marcin Michalek on 2015-05-15.
 * File belongs to project SendIt!
 */
public class CommitListAdapter extends RecyclerView.Adapter<CommitListAdapter.ViewHolder> {
  private LayoutInflater infalter;
  private List<Commit> data;
  private ImageLoader imageLoader;
  private Context context;

  public CommitListAdapter(Context context) {
    this.context = context;
    this.infalter = LayoutInflater.from(context);
    this.data = Preferences.getCommits(context);
    imageLoader = ImageLoader.getInstance();
  }

  @Override
  public CommitListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
    return new ViewHolder(infalter.inflate(R.layout.item_commit, parent, false));

  }

  @Override
  public void onBindViewHolder(CommitListAdapter.ViewHolder viewHolder, int position) {
    Commit commit = data.get(position);
    viewHolder.authorTextView.setText(commit.getAuthor());
    viewHolder.nameTextView.setText(commit.getName());
    viewHolder.statusTextView.setText(commit.getStatus());
    viewHolder.timeTextView.setText(new Date(commit.getTimestamp()).toString());
    imageLoader.displayImage("drawable://" + R.drawable.commit, viewHolder.commitPicture);
  }

  @Override
  public int getItemCount() {
    return data.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @InjectView(R.id.root)
    ViewGroup root;

    @InjectView(R.id.nameTextView)
    TextView nameTextView;

    @InjectView(R.id.authorTextView)
    TextView authorTextView;

    @InjectView(R.id.commitPicture)
    ImageView commitPicture;

    @InjectView(R.id.statusTextView)
    TextView statusTextView;

    @InjectView(R.id.timeTextView)
    TextView timeTextView;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.inject(this, itemView);
      root.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
      Commit commit = data.get(getAdapterPosition());
      //@TODO add commit to details
      //((ContentReplacer) context).replaceFragment(new CommitDetailsFragment(), "history");
    }
  }
}
