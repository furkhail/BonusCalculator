package dnd.furkhail.bonuscalculator.domain.interactor.base;


import io.reactivex.observers.DisposableSingleObserver;

public class DefaultSingleObserver<T> extends DisposableSingleObserver<T> {

    @Override
    public void onSuccess(T value) {

    }

    @Override public void onError(Throwable exception) {
        // no-op by default.
    }
}
