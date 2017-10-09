package com.home.oleg.mvpplayground.testDi;


import com.home.oleg.mvpplayground.api.ItemsMatchWordsProvider;
import com.home.oleg.mvpplayground.items.match.model.WordPair;

import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import lombok.ToString;

public class TestItemsMatchWordsProvider implements ItemsMatchWordsProvider {

    public List<WordPair> words;

    public TestItemsMatchWordsProvider() {
        this(Collections.emptyList());
    }

    public TestItemsMatchWordsProvider(List<WordPair> words) {
        this.words = words;
    }

    //    public static PublishSubject<List<WordPair>> words=PublishSubject.create();

    @Override
    public Observable<List<WordPair>> getWords() {
//        return words;
        return Observable.create(s -> {
            s.onNext(words);
        });
    }
}
