package dnd.furkhail.bonuscalculator.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.List;

import javax.inject.Inject;

import butterknife.OnClick;
import dnd.furkhail.bonuscalculator.R;
import dnd.furkhail.bonuscalculator.domain.business.Status;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.initialize();
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
