package dnd.furkhail.bonuscalculator.domain.interactor;

import java.util.List;

import javax.inject.Inject;

import dnd.furkhail.bonuscalculator.domain.business.Status;
import dnd.furkhail.bonuscalculator.domain.executor.PostExecutionThread;
import dnd.furkhail.bonuscalculator.domain.executor.ThreadExecutor;
import dnd.furkhail.bonuscalculator.domain.repository.StatusRepository;
import rx.Observable;

public class GetStatusUseCase extends UseCase<List<Status>, Void>{

    private final StatusRepository statusRepository;

    @Inject
    GetStatusUseCase(StatusRepository statusRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.statusRepository = statusRepository;
    }

    @Override
    Observable<List<Status>> buildUseCaseObservable(Void aVoid) {
        return statusRepository.getStatusList();
    }
}
