package pl.michalek.marcin.commitnotifier.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.michalek.marcin.commitnotifier.R;
import pl.michalek.marcin.commitnotifier.entity.Commit;
import pl.michalek.marcin.commitnotifier.utils.Preferences;

import java.util.List;

/**
 * Created by Marcin Michalek on 2015-05-15.
 * File belongs to project SendIt!
 */
public class CommitListAdapter extends RecyclerView.Adapter<CommitListAdapter.ViewHolder> {
  private LayoutInflater infalter;
  private List<Commit> data;
  private int lastPosition;
  private Animation showAnimation;

  public CommitListAdapter(Context context) {
    this.infalter = LayoutInflater.from(context);
    this.data = Preferences.getCommits(context);
    showAnimation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
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
    setAnimation(viewHolder.root, position);
  }

  private void setAnimation(View viewToAnimate, int position) {
    if (position > lastPosition) {
      viewToAnimate.startAnimation(showAnimation);
      lastPosition = position;
    }
  }

  @Override
  public int getItemCount() {
    return data.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    @InjectView(R.id.root)
    ViewGroup root;

    @InjectView(R.id.nameTextView)
    TextView nameTextView;

    @InjectView(R.id.authorTextView)
    TextView authorTextView;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.inject(this, itemView);
    }
  }
}
