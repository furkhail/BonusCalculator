package dnd.furkhail.bonuscalculator.presentation.presenter;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import dnd.furkhail.bonuscalculator.domain.business.Status;
import dnd.furkhail.bonuscalculator.domain.interactor.DefaultObserver;
import dnd.furkhail.bonuscalculator.domain.interactor.status.AddStatusUseCase;
import dnd.furkhail.bonuscalculator.domain.interactor.status.GetStatusListUseCase;
import dnd.furkhail.bonuscalculator.presentation.view.StatusListView;

public class StatusListPresenter implements Presenter{

    private final GetStatusListUseCase getStatusListUseCase;
    private final AddStatusUseCase addStatusUseCase;

    private StatusListView viewListView;

    @Inject
    public StatusListPresenter(GetStatusListUseCase getStatusListUseCase,
                               AddStatusUseCase addStatusUseCase) {
        this.getStatusListUseCase = getStatusListUseCase;
        this.addStatusUseCase = addStatusUseCase;
    }

    public void setView(@NonNull StatusListView view) {
        this.viewListView = view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        getStatusListUseCase.unsubscribe();
    }

    public void initialize() {
        this.loadStatusList();
    }

    /**
     * Loads all users.
     */
    private void loadStatusList() {
        hideViewRetry();
        showViewLoading();
        getStatusList();
    }

    private void showViewLoading() {
        viewListView.showLoading();
    }

    private void hideViewLoading() {
        viewListView.hideLoading();
    }

    private void showViewRetry() {
        viewListView.showRetry();
    }

    private void hideViewRetry() {
        viewListView.hideRetry();
    }

    private void showStatusListInView(List<Status> statusList) {
        viewListView.renderStatusList(statusList);
    }

    private void getStatusList() {
        getStatusListUseCase.execute(new StatusListObserver(), null);
    }

    public void addStatus(String input){
        Status status = new Status(input);
        addStatusUseCase.execute(new StatusListObserver(), status);
    }

    private final class StatusListObserver extends DefaultObserver<List<Status>> {

        @Override public void onCompleted() {
            hideViewLoading();
        }

        @Override public void onError(Throwable e) {
            hideViewLoading();
//            StatusListPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            showViewRetry();
        }

        @Override public void onNext(List<Status> status) {
            showStatusListInView(status);
        }
    }
}
