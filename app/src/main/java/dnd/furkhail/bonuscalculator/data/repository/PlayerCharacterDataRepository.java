package dnd.furkhail.bonuscalculator.data.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import dnd.furkhail.bonuscalculator.data.cache.playerCharacter.PlayerCharacterCache;
import dnd.furkhail.bonuscalculator.domain.business.PlayerCharacter;
import dnd.furkhail.bonuscalculator.domain.repository.PlayerCharacterRepository;
import io.reactivex.Observable;
import io.reactivex.functions.Predicate;

@Singleton
public class PlayerCharacterDataRepository implements PlayerCharacterRepository {

    private PlayerCharacterCache mPlayerCharacterCache;

    @Inject
    public PlayerCharacterDataRepository(PlayerCharacterCache playerCharacterCache) {
        mPlayerCharacterCache = playerCharacterCache;
    }

    @Override
    public Observable<PlayerCharacter> getPlayerCharacter() {
        return Observable.concat(
                mPlayerCharacterCache.memory(),
                mPlayerCharacterCache.disk(),
                mPlayerCharacterCache.network())
                .filter(new Predicate<PlayerCharacter>() {
                    @Override
                    public boolean test(PlayerCharacter playerCharacter) throws Exception {
                        return playerCharacter != null;
                    }
                });
//        return mPlayerCharacterCache.memory();
    }

    @Override
    public Observable<PlayerCharacter> updatePlayerCharacter(PlayerCharacter playerCharacter) {
        return mPlayerCharacterCache.updatePlayerCharacter(playerCharacter);
    }
}
