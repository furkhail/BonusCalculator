package dnd.furkhail.bonuscalculator.dagger.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dnd.furkhail.bonuscalculator.data.cache.DiskPrefser;
import dnd.furkhail.bonuscalculator.data.cache.StatusCache;
import dnd.furkhail.bonuscalculator.data.cache.StatusCacheImpl;
import dnd.furkhail.bonuscalculator.data.repository.StatusDataRepository;
import dnd.furkhail.bonuscalculator.domain.repository.StatusRepository;

@Module
public class ProjectModule {

    @Provides
    @Singleton
    StatusCache provideStatusCache(DiskPrefser diskPrefser){
        return new StatusCacheImpl(diskPrefser);
    }

    @Provides
    @Singleton
    StatusRepository provideStatusRepository(StatusCache statusCache){
        return new StatusDataRepository(statusCache);
    }
}
