package dnd.furkhail.bonuscalculator.data.cache;

import com.github.pwittchen.prefser.library.Prefser;
import com.github.pwittchen.prefser.library.TypeToken;

import java.lang.reflect.Type;

import javax.inject.Inject;
import javax.inject.Singleton;

import dnd.furkhail.bonuscalculator.MyApp;
import rx.Observable;

public class DiskPrefser {

    private Prefser library;

    @Singleton
    @Inject
    public DiskPrefser() {
        library = new Prefser(MyApp.getInstance().getApplicationContext());
    }

    public void clear(){
        library.clear();
    }

    public <T> void put(String key, T data){
        library.put(key,data);
    }

    public <T> Observable<T> get(String key){
        return library.getAndObserve(key, new TypeToken<T>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        }, null);
    }
}
