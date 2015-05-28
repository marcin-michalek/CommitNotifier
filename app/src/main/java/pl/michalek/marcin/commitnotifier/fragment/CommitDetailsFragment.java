package pl.michalek.marcin.commitnotifier.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import pl.michalek.marcin.commitnotifier.R;

/**
 * Created by Marcin Michalek on 2015-05-15.
 * File belongs to project SendIt!
 */
public class CommitDetailsFragment extends BaseFragment {
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_commit_details, container, false);
    view.setBackgroundColor(getResources().getColor(R.color.commit_list_background));
    ButterKnife.inject(this, view);
    return view;
  }
}
