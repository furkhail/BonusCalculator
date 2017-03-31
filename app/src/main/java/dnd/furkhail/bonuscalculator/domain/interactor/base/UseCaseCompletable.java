package dnd.furkhail.bonuscalculator.domain.interactor.base;

import android.util.Log;

import dagger.internal.Preconditions;
import dnd.furkhail.bonuscalculator.domain.executor.PostExecutionThread;
import dnd.furkhail.bonuscalculator.domain.executor.ThreadExecutor;
import io.reactivex.Completable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public abstract class UseCaseCompletable<Params> extends UseCase<Void, Params>{

    private static final String TAG = "UseCase";

    public UseCaseCompletable(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    public abstract Completable buildUseCaseCompletable(Params params);

    /**
     * Executes the current use case.
     *
     * @param observer {@link DisposableObserver} which will be listening to the observable build
     * by {@link #buildUseCaseObservable(Params)} ()} method.
     * @param params Parameters (Optional) used to build/execute this use case.
     */
    public void execute(DisposableCompletableObserver observer, Params params) {
        Log.d(TAG, "execute() called with: observer = [" + observer + "], params = [" + params + "]");
        Preconditions.checkNotNull(observer);
        final Completable observable = this.buildUseCaseCompletable(params)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
        addDisposable(observable.subscribeWith(observer));
    }

    public void execute(DisposableCompletableObserver observer){
        execute(observer, null);
    }

}