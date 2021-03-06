package dnd.furkhail.bonuscalculator.data.cache.status;

import com.github.pwittchen.prefser.library.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import dnd.furkhail.bonuscalculator.data.cache.DiskCache;
import dnd.furkhail.bonuscalculator.domain.business.Status;
import io.reactivex.Maybe;

public class StatusCacheImpl implements StatusCache {

    private static final String STATUS_LIST_KEY = "status_list_key";

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
    public Maybe<List<Status>> memory() {
        return Maybe.fromCallable(() -> statusList).filter(statuses -> statuses!=null && !statuses.isEmpty());
    }

    @Override
    public Maybe<List<Status>> disk() {
        List<Status> list = diskCache.get(STATUS_LIST_KEY, new TypeToken<ArrayList<Status>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        }, new ArrayList<>());
        return Maybe.fromCallable(() -> list)
                .filter(statuses -> statuses!=null && !statuses.isEmpty())
                .doOnSuccess(data -> statusList = data);
    }

    @Override
    public Maybe<List<Status>> network() {
        return Maybe.empty();
    }

    @Override
    public Maybe<List<Status>> addStatus(Status status) {
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
