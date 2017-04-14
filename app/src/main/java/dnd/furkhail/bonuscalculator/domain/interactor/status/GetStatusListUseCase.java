package dnd.furkhail.bonuscalculator.domain.interactor.status;

import java.util.List;

import javax.inject.Inject;

import dnd.furkhail.bonuscalculator.domain.business.Status;
import dnd.furkhail.bonuscalculator.domain.executor.PostExecutionThread;
import dnd.furkhail.bonuscalculator.domain.executor.ThreadExecutor;
import dnd.furkhail.bonuscalculator.domain.interactor.base.UseCaseMaybe;
import dnd.furkhail.bonuscalculator.domain.repository.StatusRepository;
import io.reactivex.Maybe;

public class GetStatusListUseCase extends UseCaseMaybe<List<Status>, Void> {

    private final StatusRepository statusRepository;

    @Inject
    GetStatusListUseCase(StatusRepository statusRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.statusRepository = statusRepository;
    }

    @Override
    public Maybe<List<Status>> buildUseCaseMaybe(Void aVoid) {
        return statusRepository.getStatusList();
    }
}
