package pl.michalek.marcin.commitnotifier.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.michalek.marcin.commitnotifier.R;
import pl.michalek.marcin.commitnotifier.adapter.CommitListAdapter;

/**
 * Created by Marcin Michalek on 2015-05-15.
 * File belongs to project SendIt!
 */
public class CommitListFragment extends Fragment {
  @InjectView(R.id.recycler_view)
  RecyclerView recyclerView;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);
    view.setBackgroundColor(getResources().getColor(R.color.commit_list_background));
    ButterKnife.inject(this, view);
    return view;
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    initRecyclerView();
  }

  private void initRecyclerView() {
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    recyclerView.setAdapter(new CommitListAdapter(getActivity()));
  }
}
