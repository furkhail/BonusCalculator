package dnd.furkhail.bonuscalculator.domain.repository;

import dnd.furkhail.bonuscalculator.domain.business.PlayerCharacter;
import io.reactivex.Maybe;
import io.reactivex.Observable;

public interface PlayerCharacterRepository {

    Observable<PlayerCharacter> getPlayerCharacter();

    Maybe<PlayerCharacter> updatePlayerCharacter(PlayerCharacter playerCharacter);

}
