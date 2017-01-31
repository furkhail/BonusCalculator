package dnd.furkhail.bonuscalculator.data.repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dnd.furkhail.bonuscalculator.data.cache.StatusCache;
import dnd.furkhail.bonuscalculator.domain.business.Status;
import dnd.furkhail.bonuscalculator.domain.repository.StatusRepository;
import io.reactivex.Observable;

@Singleton
public class StatusDataRepository implements StatusRepository {

    private StatusCache statusCache;

    @Inject
    public StatusDataRepository(StatusCache statusCache) {
        this.statusCache = statusCache;
    }

    @Override
    public Observable<List<Status>> statuses() {
        return statusCache.getStatus();
    }
}
