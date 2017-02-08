package dnd.furkhail.bonuscalculator.data.cache.status;

import com.github.pwittchen.prefser.library.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import dnd.furkhail.bonuscalculator.data.cache.DiskCache;
import dnd.furkhail.bonuscalculator.domain.business.Status;
import io.reactivex.Observable;

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
        ArrayList<Status> list = diskCache.get(STATUS_LIST_KEY, new TypeToken<ArrayList<Status>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        if(list!=null) {
            Observable<List<Status>> observable = Observable.just(list);
            return observable.doOnNext(data -> statusList = data);
        }
        return Observable.empty();
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
    public boolean removeStatus(String name) {
        boolean deleted = false;
        for(Status s: statusList){
            if(name.equals(s.getName())){
                statusList.remove(s);
                deleted = true;
            }
        }
        diskCache.put(STATUS_LIST_KEY, statusList);
        return deleted;
    }
}
