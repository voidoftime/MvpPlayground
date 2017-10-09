package com.home.oleg.mvpplayground.test.di;


import com.home.oleg.mvpplayground.api.ItemsMatchWordsProvider;
import com.home.oleg.mvpplayground.items.match.model.WordPair;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class TestItemsMatchWordsProvider implements ItemsMatchWordsProvider {
    public static List<WordPair> words;
//    public static PublishSubject<List<WordPair>> words=PublishSubject.create();

    @Override
    public Observable<List<WordPair>> getWords() {
//        return words;
        return Observable.create(s -> {
            s.onNext(words);
        });
    }
}
