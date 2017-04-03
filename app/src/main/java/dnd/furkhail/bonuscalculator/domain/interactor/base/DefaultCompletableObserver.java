package dnd.furkhail.bonuscalculator.domain.interactor.base;


import io.reactivex.observers.DisposableCompletableObserver;

public class DefaultCompletableObserver extends DisposableCompletableObserver {

    @Override public void onComplete() {
        // no-op by default.
    }

    @Override public void onError(Throwable exception) {
        // no-op by default.
    }
}
