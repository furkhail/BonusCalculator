package dnd.furkhail.bonuscalculator.domain.interactor.base;

import android.util.Log;

import dagger.internal.Preconditions;
import dnd.furkhail.bonuscalculator.domain.executor.PostExecutionThread;
import dnd.furkhail.bonuscalculator.domain.executor.ThreadExecutor;
import io.reactivex.Single;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public abstract class UseCaseSingle<T, Params> extends UseCase<T, Params>{

    private static final String TAG = "UseCase";

    public UseCaseSingle(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    public abstract Single<T> buildUseCaseSingle(Params params);

    /**
     * Executes the current use case.
     *
     * @param observer {@link DisposableObserver} which will be listening to the observable build
     * by {@link #buildUseCaseSingle(Params)} ()} method.
     * @param params Parameters (Optional) used to build/execute this use case.
     */
    public void execute(DisposableSingleObserver<T> observer, Params params) {
        Log.d(TAG, "execute() called with: observer = [" + observer + "], params = [" + params + "]");
        Preconditions.checkNotNull(observer);
        final Single<T> observable = this.buildUseCaseSingle(params)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
        addDisposable(observable.subscribeWith(observer));
    }

    public void execute(DisposableSingleObserver<T> observer){
        execute(observer, null);
    }

}