package dnd.furkhail.bonuscalculator.domain.interactor.status;

import javax.inject.Inject;

import dnd.furkhail.bonuscalculator.domain.executor.PostExecutionThread;
import dnd.furkhail.bonuscalculator.domain.executor.ThreadExecutor;
import dnd.furkhail.bonuscalculator.domain.interactor.base.UseCaseSingle;
import dnd.furkhail.bonuscalculator.domain.repository.StatusRepository;
import io.reactivex.Single;

public class RemoveStatusUseCase extends UseCaseSingle<Boolean, String> {

    private final StatusRepository statusRepository;

    @Inject
    RemoveStatusUseCase(StatusRepository statusRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.statusRepository = statusRepository;
    }

    @Override
    public Single<Boolean> buildUseCaseSingle(String statusName) {
        return Single.fromCallable(() -> statusRepository.removeStatus(statusName));
    }
}
