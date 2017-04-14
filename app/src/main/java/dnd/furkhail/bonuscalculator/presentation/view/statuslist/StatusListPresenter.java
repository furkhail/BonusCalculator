package dnd.furkhail.bonuscalculator.presentation.view.statuslist;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import dnd.furkhail.bonuscalculator.domain.business.Status;
import dnd.furkhail.bonuscalculator.domain.interactor.base.DefaultMaybeObserver;
import dnd.furkhail.bonuscalculator.domain.interactor.status.AddStatusUseCase;
import dnd.furkhail.bonuscalculator.domain.interactor.status.GetStatusListUseCase;
import dnd.furkhail.bonuscalculator.presentation.base.Presenter;

class StatusListPresenter implements Presenter<StatusListView> {

    private final GetStatusListUseCase mGetStatusListUseCase;
    private final AddStatusUseCase mAddStatusUseCase;

    private StatusListView mStatusListView;

    @Inject
    StatusListPresenter(GetStatusListUseCase getStatusListUseCase,
                        AddStatusUseCase addStatusUseCase) {
        mGetStatusListUseCase = getStatusListUseCase;
        mAddStatusUseCase = addStatusUseCase;
    }

    @Override
    public void setView(@NonNull StatusListView view) {
        mStatusListView = view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        mStatusListView = null;
        mGetStatusListUseCase.dispose();
        mAddStatusUseCase.dispose();
    }

    @Override
    public void initialize() {
        this.loadStatusList();
    }

    private void loadStatusList() {
        hideViewRetry();
        showViewLoading();
        getStatusList();
    }

    private void showStatusListInView(List<Status> statusList) {
        mStatusListView.renderStatusList(statusList);
    }

    private void getStatusList() {
        mGetStatusListUseCase.execute(new StatusListObserver());
    }

    public void addStatus(String input){
        Status status = new Status(input);
        mAddStatusUseCase.execute(new StatusListObserver(), status);
    }

    private final class StatusListObserver extends DefaultMaybeObserver<List<Status>> {

        @Override public void onComplete() {
            hideViewLoading();
        }

        @Override public void onError(Throwable e) {
            hideViewLoading();
            showViewRetry();
        }

        @Override public void onSuccess(List<Status> status) {
            hideViewLoading();
            showStatusListInView(status);
        }
    }


    private void showViewLoading() {
        mStatusListView.showLoading();
    }

    private void hideViewLoading() {
        mStatusListView.hideLoading();
    }

    private void showViewRetry() {
        mStatusListView.showRetry();
    }

    private void hideViewRetry() {
        mStatusListView.hideRetry();
    }
}
