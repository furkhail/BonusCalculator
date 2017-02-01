package dnd.furkhail.bonuscalculator.data.cache;

import java.util.List;

import dnd.furkhail.bonuscalculator.domain.business.Status;
import rx.Observable;

public interface StatusCache extends Cache<List<Status>> {

    Observable<List<Status>> addStatus(Status status);

    void removeStatus(String name);
}
