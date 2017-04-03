package dnd.furkhail.bonuscalculator.data.cache;

import io.reactivex.Maybe;

public interface Cache<T> {

    void clearMemory();

    Maybe<T> memory();

    Maybe<T> disk();

    Maybe<T> network();

}
