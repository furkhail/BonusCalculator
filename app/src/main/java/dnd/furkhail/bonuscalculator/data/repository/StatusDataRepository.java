package dnd.furkhail.bonuscalculator.data.repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dnd.furkhail.bonuscalculator.data.cache.status.StatusCache;
import dnd.furkhail.bonuscalculator.domain.business.Status;
import dnd.furkhail.bonuscalculator.domain.repository.StatusRepository;
import io.reactivex.Maybe;

@Singleton
public class StatusDataRepository implements StatusRepository {

    private StatusCache statusCache;

    @Inject
    StatusDataRepository(StatusCache statusCache) {
        this.statusCache = statusCache;
    }

    @Override
    public Maybe<List<Status>> addStatus(Status status) {
        return statusCache.addStatus(status);
    }

    @Override
    public boolean removeStatus(String name) {
        return statusCache.removeStatus(name);
    }

    @Override
    public Maybe<List<Status>> getStatusList() {
        return Maybe.concat(statusCache.memory(), statusCache.disk(), statusCache.network())
                .filter(data -> data != null)
                .firstElement();
    }
}
