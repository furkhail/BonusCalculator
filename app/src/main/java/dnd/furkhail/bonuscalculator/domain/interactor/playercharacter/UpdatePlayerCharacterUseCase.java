package dnd.furkhail.bonuscalculator.domain.interactor.playercharacter;

import javax.inject.Inject;

import dnd.furkhail.bonuscalculator.domain.business.PlayerCharacter;
import dnd.furkhail.bonuscalculator.domain.executor.PostExecutionThread;
import dnd.furkhail.bonuscalculator.domain.executor.ThreadExecutor;
import dnd.furkhail.bonuscalculator.domain.interactor.base.UseCaseMaybe;
import dnd.furkhail.bonuscalculator.domain.repository.PlayerCharacterRepository;
import io.reactivex.Maybe;

public class UpdatePlayerCharacterUseCase extends UseCaseMaybe<PlayerCharacter, PlayerCharacter> {

    private final PlayerCharacterRepository mPlayerCharacterRepository;

    @Inject
    UpdatePlayerCharacterUseCase(PlayerCharacterRepository playerCharacterRepository,
                                 ThreadExecutor threadExecutor,
                                 PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.mPlayerCharacterRepository = playerCharacterRepository;
    }

    @Override
    public Maybe<PlayerCharacter> buildUseCaseMaybe(PlayerCharacter playerCharacter) {
        return mPlayerCharacterRepository.updatePlayerCharacter(playerCharacter);
    }
}
