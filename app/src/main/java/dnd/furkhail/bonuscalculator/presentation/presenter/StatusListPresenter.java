package dnd.furkhail.bonuscalculator.presentation.presenter;

import java.util.List;

import javax.inject.Inject;

import dnd.furkhail.bonuscalculator.domain.business.Status;
import dnd.furkhail.bonuscalculator.domain.interactor.DefaultObserver;
import dnd.furkhail.bonuscalculator.domain.interactor.GetStatusUseCase;
import dnd.furkhail.bonuscalculator.presentation.view.StatusListView;

public class StatusListPresenter implements Presenter{

    private final GetStatusUseCase getStatusUseCase;

    private StatusListView viewListView;


    @Inject
    public StatusListPresenter(GetStatusUseCase getStatusUseCase) {
        this.getStatusUseCase = getStatusUseCase;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        getStatusUseCase.dispose();
    }

    public void initialize() {
        this.loadUserList();
    }

    /**
     * Loads all users.
     */
    private void loadUserList() {
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
        getStatusUseCase.execute(new StatusListObserver(), null);
    }

    private final class StatusListObserver extends DefaultObserver<List<Status>> {

        @Override public void onComplete() {
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
