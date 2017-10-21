package com.ben.etsyclient.data;

import rx.Observable;

public interface Repository<T> {

    Observable<T> syncCategories();
    Observable<T> getCategories();
}
