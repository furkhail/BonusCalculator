package dnd.furkhail.bonuscalculator.data.cache;

import com.github.pwittchen.prefser.library.Prefser;
import com.github.pwittchen.prefser.library.TypeToken;

import java.lang.reflect.Type;

import javax.inject.Inject;

import dnd.furkhail.bonuscalculator.MyApp;

public class DiskCache {

    private Prefser library;

    @Inject
    public DiskCache() {
        library = new Prefser(MyApp.getContext());
    }

    public void clear(){
        library.clear();
    }

    <T> void put(String key, T data){
        library.put(key,data);
    }

    public <T> T get(String key){
        return library.get(key, new TypeToken<T>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        }, null);
    }

//    public <T> Observable<T> get(String key){
//        return library.getAndObserve(key, new TypeToken<T>() {
//            @Override
//            public Type getType() {
//                return super.getType();
//            }
//        }, null);
//    }
}
