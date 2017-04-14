package dnd.furkhail.bonuscalculator.presentation.view.statuslist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dnd.furkhail.bonuscalculator.MyApp;
import dnd.furkhail.bonuscalculator.R;
import dnd.furkhail.bonuscalculator.dagger.components.ApplicationComponent;

import dnd.furkhail.bonuscalculator.domain.business.Status;
import dnd.furkhail.bonuscalculator.presentation.base.BaseFragment;
import dnd.furkhail.bonuscalculator.presentation.view.adapter.StatusAdapter;
import io.reactivex.functions.Consumer;

public class StatusListFragment extends BaseFragment implements StatusListView {

    private static final String TAG = "StatusListFragment";

    @BindView(R.id.status_scores_recycler)
    RecyclerView mStatusScoresRecycler;

    StatusAdapter mStatusAdapter;
    List<Status> mStatusList;

    @Inject
    StatusListPresenter mPresenter;

    public static StatusListFragment newInstance() {
        Bundle args = new Bundle();

        StatusListFragment fragment = new StatusListFragment();
        fragment.setArguments(args);

        Log.i(TAG, "StatusListFragment: newInstance");

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectComponent(MyApp.get(getActivity()).getApplicationComponent());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        if (view != null) {
            ButterKnife.bind(this, view);
            mStatusScoresRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
            //mStatScoresRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        return view;
    }

    @Override
    public void renderStatusList(@NonNull List<Status> statusList) {
        mStatusList = statusList;

        Log.i(TAG, "renderStatusList");

        setStatusAdapter();

    }

    private void setStatusAdapter() {
        if (mStatusAdapter == null) {
            mStatusAdapter = new StatusAdapter(mStatusList);
            mStatusScoresRecycler.setAdapter(mStatusAdapter);
            mStatusAdapter.getPositionClicks().subscribe(this::showAlert);
            Log.i(TAG, "setStatusAdapter: null");

        } else {
            Log.i(TAG, "setStatusAdapter: not null");
            mStatusAdapter.setStatusList(mStatusList);
        }
    }

    private void showAlert(Status status){
        Log.i(TAG, "showAlert: " + status.getName());
    }

    public void showAddStatusDialog() {

    }

    @Override
    protected void injectComponent(ApplicationComponent component) {
        component.inject(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.setView(this);
        if (savedInstanceState == null) {
//            Log.i(TAG, "onViewCreated: no saveinstanceState");
            mPresenter.initialize();
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_status_list_rv;
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void viewStatus(Status status) {

    }

    @Override
    public Context context() {
        return getContext();
    }
}
