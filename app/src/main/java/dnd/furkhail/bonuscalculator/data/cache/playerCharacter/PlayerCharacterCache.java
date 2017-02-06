package dnd.furkhail.bonuscalculator.data.cache.playerCharacter;

import dnd.furkhail.bonuscalculator.data.cache.Cache;
import dnd.furkhail.bonuscalculator.domain.business.PlayerCharacter;
import io.reactivex.Observable;

public interface PlayerCharacterCache extends Cache<PlayerCharacter> {

    Observable<PlayerCharacter> updatePlayerCharacter(PlayerCharacter playerCharacter);
}
