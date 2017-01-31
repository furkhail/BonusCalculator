package dnd.furkhail.bonuscalculator.data.cache;

import java.util.List;

import dnd.furkhail.bonuscalculator.domain.business.Status;
import io.reactivex.Observable;

public interface StatusCache {

    Observable<List<Status>> getStatus();

    void put(Status status);

    boolean isCached(final String name);

    boolean isExpired();

    void evictAll();

}
