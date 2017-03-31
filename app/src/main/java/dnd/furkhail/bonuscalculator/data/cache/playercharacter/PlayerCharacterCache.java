package dnd.furkhail.bonuscalculator.data.cache.playercharacter;

import dnd.furkhail.bonuscalculator.data.cache.Cache;
import dnd.furkhail.bonuscalculator.domain.business.PlayerCharacter;
import io.reactivex.Observable;

public interface PlayerCharacterCache extends Cache<PlayerCharacter> {

    Observable<PlayerCharacter> write(PlayerCharacter playerCharacter);
}
