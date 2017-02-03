package dnd.furkhail.bonuscalculator.domain.repository;

import java.util.List;

import dnd.furkhail.bonuscalculator.domain.business.Status;
import io.reactivex.Observable;

public interface StatusRepository {

    Observable<List<Status>> addStatus(Status status);

    void removeStatus(String name);

    Observable<List<Status>> getStatusList();
}
