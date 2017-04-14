package dnd.furkhail.bonuscalculator.domain.interactor.base;

import android.util.Log;

import dagger.internal.Preconditions;
import dnd.furkhail.bonuscalculator.domain.executor.PostExecutionThread;
import dnd.furkhail.bonuscalculator.domain.executor.ThreadExecutor;
import io.reactivex.Maybe;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public abstract class UseCaseMaybe<T, Params> extends UseCase<T, Params>{

    private static final String TAG = "UseCase";

    public UseCaseMaybe(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    public abstract Maybe<T> buildUseCaseMaybe(Params params);

    /**
     * Executes the current use case.
     *
     * @param observer {@link DisposableObserver} which will be listening to the observable build
     * by {@link #buildUseCaseSingle(Params)} ()} method.
     * @param params Parameters (Optional) used to build/execute this use case.
     */
    public void execute(DisposableMaybeObserver<T> observer, Params params) {
        Log.d(TAG, "execute() called with: observer = [" + observer + "], params = [" + params + "]");
        Preconditions.checkNotNull(observer);
        final Maybe<T> observable = this.buildUseCaseMaybe(params)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
        addDisposable(observable.subscribeWith(observer));
    }

    public void execute(DisposableMaybeObserver<T> observer){
        execute(observer, null);
    }

}