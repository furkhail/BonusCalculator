package dnd.furkhail.bonuscalculator.dagger.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dnd.furkhail.bonuscalculator.data.cache.DiskCache;
import dnd.furkhail.bonuscalculator.data.cache.playercharacter.PlayerCharacterCache;
import dnd.furkhail.bonuscalculator.data.cache.playercharacter.PlayerCharacterCacheImpl;
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
    StatusRepository provideStatusRepository(StatusDataRepository repository){
        return repository;
    }

    @Provides
    @Singleton
    PlayerCharacterRepository providePlayerCharacterRepository(PlayerCharacterDataRepository repository){
        return repository;
    }

    @Provides
    @Singleton
    PlayerCharacterCache providePlayerCharacterCache(PlayerCharacterCacheImpl cache){
        return cache;
    }
}
