package dnd.furkhail.bonuscalculator.domain.interactor.status;

import java.util.List;

import javax.inject.Inject;

import dnd.furkhail.bonuscalculator.domain.business.Status;
import dnd.furkhail.bonuscalculator.domain.executor.PostExecutionThread;
import dnd.furkhail.bonuscalculator.domain.executor.ThreadExecutor;
import dnd.furkhail.bonuscalculator.domain.interactor.UseCase;
import dnd.furkhail.bonuscalculator.domain.repository.StatusRepository;
import rx.Observable;

public class AddStatusUseCase extends UseCase<List<Status>, Status> {

    private final StatusRepository statusRepository;

    @Inject
    AddStatusUseCase(StatusRepository statusRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.statusRepository = statusRepository;
    }

    @Override
    public Observable<List<Status>> buildUseCaseObservable(Status status) {
        return statusRepository.addStatus(status);
    }
}
