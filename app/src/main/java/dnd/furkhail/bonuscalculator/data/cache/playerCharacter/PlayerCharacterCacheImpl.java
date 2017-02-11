package dnd.furkhail.bonuscalculator.data.cache.playercharacter;

import android.util.Log;

import dnd.furkhail.bonuscalculator.data.cache.DiskCache;
import dnd.furkhail.bonuscalculator.domain.business.PlayerCharacter;
import io.reactivex.Observable;

public class PlayerCharacterCacheImpl implements PlayerCharacterCache {

    private static final String TAG = "PlayerCharacterCacheImp";

    private static final String PLAYER_CHARACTER_KEY = "player_character_key";

    private PlayerCharacter mPlayerCharacter;

    private DiskCache diskCache;

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
    public Observable<PlayerCharacter> memory() {
        Log.d(TAG, "memory() called: "+mPlayerCharacter);
        return Observable.just(mPlayerCharacter);
    }

    @Override
    public Observable<PlayerCharacter> disk() {
        PlayerCharacter playerCharacter = diskCache.get(PLAYER_CHARACTER_KEY, PlayerCharacter.class, null);
        if (playerCharacter != null) {
            Observable<PlayerCharacter> observable = Observable.just(playerCharacter);
            return observable.doOnNext(data -> mPlayerCharacter = data);
        }
        return Observable.empty();
    }

    @Override
    public Observable<PlayerCharacter> network() {
        return Observable.empty();
    }

    @Override
    public Observable<PlayerCharacter> write(PlayerCharacter playerCharacter) {
        if (playerCharacter != null) {
            mPlayerCharacter = playerCharacter;
            diskCache.put(PLAYER_CHARACTER_KEY, playerCharacter);
        }
        return memory();
    }
}
