package dnd.furkhail.bonuscalculator.domain.repository;

import java.util.List;

import dnd.furkhail.bonuscalculator.domain.business.Status;
import rx.Observable;

public interface StatusRepository {

    void addStatus(Status status);

    void removeStatus(String name);

    Observable<List<Status>> getStatusList();
}
