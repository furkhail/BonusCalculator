package dnd.furkhail.bonuscalculator.data.cache;

import java.util.List;

import dnd.furkhail.bonuscalculator.domain.business.Status;

public interface StatusCache extends Cache<List<Status>> {

    void addStatus(Status status);

    void removeStatus(String name);
}
