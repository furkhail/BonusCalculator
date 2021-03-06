package dnd.furkhail.bonuscalculator.data.cache.status;

import java.util.List;

import dnd.furkhail.bonuscalculator.data.cache.Cache;
import dnd.furkhail.bonuscalculator.domain.business.Status;
import io.reactivex.Maybe;

public interface StatusCache extends Cache<List<Status>> {

    Maybe<List<Status>> addStatus(Status status);

    boolean removeStatus(String name);
}
