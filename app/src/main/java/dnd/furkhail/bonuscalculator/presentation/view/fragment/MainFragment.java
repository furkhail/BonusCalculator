package dnd.furkhail.bonuscalculator.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.util.Log;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import dnd.furkhail.bonuscalculator.MyApp;
import dnd.furkhail.bonuscalculator.R;
import dnd.furkhail.bonuscalculator.dagger.components.ApplicationComponent;
import dnd.furkhail.bonuscalculator.domain.business.Status;
import dnd.furkhail.bonuscalculator.presentation.base.BaseFragment;
import dnd.furkhail.bonuscalculator.presentation.presenter.StatusListPresenter;
import dnd.furkhail.bonuscalculator.presentation.view.StatusListView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends BaseFragment implements StatusListView {

    private static final String TAG = "MainFragment";

    @Inject
    StatusListPresenter presenter;

    public MainFragment() {
    }

    public static MainFragment newInstance() {
        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectComponent(MyApp.get(getActivity()).getApplicationComponent());
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_main;
    }

    @Override
    protected void injectComponent(ApplicationComponent component) {
        Log.d(TAG, "injectComponent() called with: component = [" + component + "]");
        component.inject(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        presenter.setView(this);
        if (savedInstanceState == null) {
            presenter.initialize();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.resume();
    }

    @Override
    public void showAddStatusDialog() {
        new MaterialDialog.Builder(context())
                .content("añade un estado")
                .inputType(InputType.TYPE_CLASS_TEXT)
                .input("nombre estado", "",
                        (dialog, input) -> presenter.addStatus(input.toString()))
                .show();
    }

    @Override
    public void renderStatusList(List<Status> statusListt) {
        Log.d(TAG, "renderStatusList() called with: statusListt = [" + statusListt + "]");
    }

    @Override
    public void viewStatus(Status status) {

    }

    @OnClick(R.id.fab)
    private void pressFab(){
        showAddStatusDialog();
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
    public Context context() {
        return getContext();
    }

}
