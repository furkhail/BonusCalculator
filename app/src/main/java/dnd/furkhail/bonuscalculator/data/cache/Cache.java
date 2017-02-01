package dnd.furkhail.bonuscalculator.data.cache;


import rx.Observable;

public interface Cache<T> {

    void clearMemory();

    Observable<T> memory();

    Observable<T> disk();

    Observable<T> network();
}
