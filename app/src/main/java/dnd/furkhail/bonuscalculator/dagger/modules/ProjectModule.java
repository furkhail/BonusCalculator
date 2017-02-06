package dnd.furkhail.bonuscalculator.dagger.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dnd.furkhail.bonuscalculator.data.cache.DiskCache;
import dnd.furkhail.bonuscalculator.data.cache.playerCharacter.PlayerCharacterCache;
import dnd.furkhail.bonuscalculator.data.cache.playerCharacter.PlayerCharacterCacheImpl;
import dnd.furkhail.bonuscalculator.data.cache.status.StatusCache;
import dnd.furkhail.bonuscalculator.data.cache.status.StatusCacheImpl;
import dnd.furkhail.bonuscalculator.data.repository.PlayerCharacterDataRepository;
import dnd.furkhail.bonuscalculator.data.repository.StatusDataRepository;
import dnd.furkhail.bonuscalculator.domain.repository.PlayerCharacterRepository;
import dnd.furkhail.bonuscalculator.domain.repository.StatusRepository;

@Module
public class ProjectModule {

    @Provides
    @Singleton
    StatusCache provideStatusCache(DiskCache diskCache){
        return new StatusCacheImpl(diskCache);
    }

    @Provides
    @Singleton
    StatusRepository provideStatusRepository(StatusCache statusCache){
        return new StatusDataRepository(statusCache);
    }

    @Provides
    @Singleton
    PlayerCharacterCache providePlayerCharacterCache(DiskCache diskCache){
        return new PlayerCharacterCacheImpl(diskCache);
    }

    @Provides
    @Singleton
    PlayerCharacterRepository providePlayerCharacterRepository(PlayerCharacterCache playerCharacterCache){
        return new PlayerCharacterDataRepository(playerCharacterCache);
    }
}
