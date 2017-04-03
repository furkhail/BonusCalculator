package dnd.furkhail.bonuscalculator.data.cache.playercharacter;

import dnd.furkhail.bonuscalculator.data.cache.Cache;
import dnd.furkhail.bonuscalculator.domain.business.PlayerCharacter;
import io.reactivex.Maybe;

public interface PlayerCharacterCache extends Cache<PlayerCharacter> {

    Maybe<PlayerCharacter> write(PlayerCharacter playerCharacter);
}
