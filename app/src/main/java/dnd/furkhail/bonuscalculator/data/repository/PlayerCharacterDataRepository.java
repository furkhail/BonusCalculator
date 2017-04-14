package dnd.furkhail.bonuscalculator.data.repository;

import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

import dnd.furkhail.bonuscalculator.data.cache.playercharacter.PlayerCharacterCache;
import dnd.furkhail.bonuscalculator.domain.business.PlayerCharacter;
import dnd.furkhail.bonuscalculator.domain.repository.PlayerCharacterRepository;
import io.reactivex.Maybe;
import io.reactivex.Observable;

@Singleton
public class PlayerCharacterDataRepository implements PlayerCharacterRepository {

    private static final String TAG = "PlayerCharacterDataRepo";

    private PlayerCharacterCache mPlayerCharacterCache;

    @Inject
    PlayerCharacterDataRepository(PlayerCharacterCache playerCharacterCache) {
        mPlayerCharacterCache = playerCharacterCache;
    }

    @Override
    public Observable<PlayerCharacter> getPlayerCharacter() {
        return Maybe.concat(
                mPlayerCharacterCache.memory(),
                mPlayerCharacterCache.disk(),
                mPlayerCharacterCache.network())
                .filter(playerCharacter -> {
                    Log.d(TAG, "filter() called with: playerCharacter = [" + playerCharacter + "]");
                    return playerCharacter != null;
                }).toObservable();
    }

    @Override
    public Maybe<PlayerCharacter> updatePlayerCharacter(PlayerCharacter playerCharacter) {
        return mPlayerCharacterCache.write(playerCharacter);
    }
}
