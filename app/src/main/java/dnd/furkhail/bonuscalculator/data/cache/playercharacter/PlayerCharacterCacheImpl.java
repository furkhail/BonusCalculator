package dnd.furkhail.bonuscalculator.data.cache.playercharacter;

import android.util.Log;

import javax.inject.Inject;

import dnd.furkhail.bonuscalculator.data.cache.DiskCache;
import dnd.furkhail.bonuscalculator.domain.business.PlayerCharacter;
import io.reactivex.Maybe;

public class PlayerCharacterCacheImpl implements PlayerCharacterCache {

    private static final String TAG = "PlayerCharacterCacheImp";

    private static final String PLAYER_CHARACTER_KEY = "player_character_key";

    private PlayerCharacter mPlayerCharacter;

    private DiskCache diskCache;

    @Inject
    public PlayerCharacterCacheImpl(DiskCache diskCache) {
        Log.d(TAG, "PlayerCharacterCacheImpl() called with: diskCache = [" + diskCache + "]");
        this.mPlayerCharacter = new PlayerCharacter();
        this.diskCache = diskCache;
    }

    @Override
    public void clearMemory() {
        mPlayerCharacter = null;
    }

    @Override
    public Maybe<PlayerCharacter> memory() {
        Log.d(TAG, "memory() called: "+mPlayerCharacter);
        return Maybe.fromCallable(() -> mPlayerCharacter)
                .filter(playerCharacter -> playerCharacter != null);
    }

    @Override
    public Maybe<PlayerCharacter> disk() {
        return Maybe.fromCallable(() -> diskCache.get(PLAYER_CHARACTER_KEY, PlayerCharacter.class))
                .filter(playerCharacter -> playerCharacter != null)
                .doOnSuccess(data -> mPlayerCharacter = data);
    }

    @Override
    public Maybe<PlayerCharacter> network() {
        return Maybe.empty();
    }

    @Override
    public Maybe<PlayerCharacter> write(PlayerCharacter playerCharacter) {
        if (playerCharacter != null) {
            mPlayerCharacter = playerCharacter;
            diskCache.put(PLAYER_CHARACTER_KEY, playerCharacter);
        }
        return memory();
    }
}
