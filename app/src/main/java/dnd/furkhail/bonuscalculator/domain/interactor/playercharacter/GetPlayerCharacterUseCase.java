package dnd.furkhail.bonuscalculator.domain.interactor.playercharacter;

import javax.inject.Inject;

import dnd.furkhail.bonuscalculator.domain.business.PlayerCharacter;
import dnd.furkhail.bonuscalculator.domain.executor.PostExecutionThread;
import dnd.furkhail.bonuscalculator.domain.executor.ThreadExecutor;
import dnd.furkhail.bonuscalculator.domain.interactor.base.UseCaseObservable;
import dnd.furkhail.bonuscalculator.domain.repository.PlayerCharacterRepository;
import io.reactivex.Observable;

public class GetPlayerCharacterUseCase extends UseCaseObservable<PlayerCharacter, Void> {

    private final PlayerCharacterRepository mPlayerCharacterRepository;

    @Inject
    GetPlayerCharacterUseCase(PlayerCharacterRepository playerCharacterRepository,
                              ThreadExecutor threadExecutor,
                              PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.mPlayerCharacterRepository = playerCharacterRepository;
    }

    @Override
    public Observable<PlayerCharacter> buildUseCaseObservable(Void aVoid) {
        return mPlayerCharacterRepository.getPlayerCharacter();
    }
}
