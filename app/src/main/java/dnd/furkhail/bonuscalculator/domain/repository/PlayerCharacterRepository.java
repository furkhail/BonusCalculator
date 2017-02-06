package dnd.furkhail.bonuscalculator.domain.repository;

import dnd.furkhail.bonuscalculator.domain.business.PlayerCharacter;
import io.reactivex.Observable;

public interface PlayerCharacterRepository {

    Observable<PlayerCharacter> getPlayerCharacter();

    Observable<PlayerCharacter> updatePlayerCharacter(PlayerCharacter playerCharacter);

}
