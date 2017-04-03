package dnd.furkhail.bonuscalculator.domain.interactor.base;


import io.reactivex.observers.DisposableMaybeObserver;

public class DefaultMaybeObserver<T> extends DisposableMaybeObserver<T> {

    @Override public void onComplete() {
        // no-op by default.
    }

    @Override
    public void onSuccess(T value) {

    }

    @Override public void onError(Throwable exception) {
        // no-op by default.
    }
}
