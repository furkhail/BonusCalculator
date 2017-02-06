package dnd.furkhail.bonuscalculator.domain.interactor.status;

import javax.inject.Inject;

import dnd.furkhail.bonuscalculator.domain.executor.PostExecutionThread;
import dnd.furkhail.bonuscalculator.domain.executor.ThreadExecutor;
import dnd.furkhail.bonuscalculator.domain.interactor.UseCase;
import dnd.furkhail.bonuscalculator.domain.repository.StatusRepository;
import io.reactivex.Observable;

public class RemoveStatusUseCase extends UseCase<Boolean, String> {

    private final StatusRepository statusRepository;

    @Inject
    RemoveStatusUseCase(StatusRepository statusRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.statusRepository = statusRepository;
    }

    @Override
    public Observable<Boolean> buildUseCaseObservable(String statusName) {
        return Observable.just(statusRepository.removeStatus(statusName));
    }
}
