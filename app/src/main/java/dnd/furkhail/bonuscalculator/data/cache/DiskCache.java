package dnd.furkhail.bonuscalculator.data.cache;

import com.github.pwittchen.prefser.library.Prefser;
import com.github.pwittchen.prefser.library.TypeToken;

import javax.inject.Inject;

import dnd.furkhail.bonuscalculator.MyApp;

public class DiskCache {

    private Prefser library;

    @Inject
    DiskCache() {
        library = new Prefser(MyApp.getContext());
    }

    public void clear(){
        library.clear();
    }

    public <T> void put(String key, T data){
        library.put(key,data);
    }

    public <T> T get(String key, Class<T> classOf, T defaultValue){
        return library.get(key, classOf, defaultValue);
    }

    public <T> T get(String key, TypeToken<T> typeToken, T defaultValue){
        return library.get(key, typeToken, defaultValue);
    }
}
