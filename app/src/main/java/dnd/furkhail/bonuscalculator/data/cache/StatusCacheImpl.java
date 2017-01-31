package dnd.furkhail.bonuscalculator.data.cache;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dnd.furkhail.bonuscalculator.domain.business.Status;
import io.reactivex.Observable;

@Singleton
public class StatusCacheImpl implements StatusCache{

    private List<Status> statusList;

    @Inject
    public StatusCacheImpl() {
        this.statusList = new ArrayList<>();
    }

    @Override
    public Observable<List<Status>> getStatus() {
        return Observable.just(statusList);
    }

    @Override
    public void put(Status status) {
        statusList.add(status);
    }

    @Override
    public boolean isCached(String name) {
        for(Status s: statusList){
            if(name.equals(s.getName())){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isExpired() {
        return false;
    }

    @Override
    public void evictAll() {
        statusList.clear();
    }
}
