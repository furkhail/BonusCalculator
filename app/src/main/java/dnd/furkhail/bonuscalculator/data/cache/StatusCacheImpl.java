package dnd.furkhail.bonuscalculator.data.cache;

import java.util.ArrayList;
import java.util.List;

import dnd.furkhail.bonuscalculator.domain.business.Status;
import rx.Observable;

public class StatusCacheImpl implements StatusCache {

    private static final String STATUS_LIST_KEY="status_list_key";

    private List<Status> statusList;

    private DiskCache diskCache;

    public StatusCacheImpl(DiskCache diskCache) {
        this.statusList = new ArrayList<>();
        this.diskCache = diskCache;
    }

    @Override
    public void clearMemory() {
        statusList.clear();
    }

    @Override
    public Observable<List<Status>> memory() {
        return Observable.just(statusList);
    }

    @Override
    public Observable<List<Status>> disk() {
        Observable<List<Status>> observable = diskCache.get(STATUS_LIST_KEY);
        return observable.doOnNext(data -> statusList = data);
    }

    @Override
    public Observable<List<Status>> network() {
        return null;
    }

    @Override
    public Observable<List<Status>> addStatus(Status status) {
        if(!statusList.contains(status)){
            statusList.add(status);
        }
        diskCache.put(STATUS_LIST_KEY, statusList);
        return memory();
    }

    @Override
    public void removeStatus(String name) {
        for(Status s: statusList){
            if(name.equals(s.getName())){
                statusList.remove(s);
            }
        }
        diskCache.put(STATUS_LIST_KEY, statusList);
    }

    Observable.Transformer<List<Status>, List<Status>> logSource(final String source) {
        return dataObservable -> dataObservable.doOnNext(data -> {
            if (data == null) {
                System.out.println(source + " does not have any data.");
            }
            else {
                System.out.println(source + " has the data you are looking for!");
            }
        });
    }
}
