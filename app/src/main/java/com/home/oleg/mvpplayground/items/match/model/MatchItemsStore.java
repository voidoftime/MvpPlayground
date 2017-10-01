package com.home.oleg.mvpplayground.items.match.model;


import java.util.List;

import io.reactivex.subjects.PublishSubject;

public interface MatchItemsStore {
    String getForeignSelectedWord();
    String getNativeSelectedWord();

    void setForeignSelectedWord(String value);
    void setNativeSelectedWord(String value);

    List<String> getForeignWords();
    List<String> getNativeWords();

    void prepareSession();

    boolean isWordsMatch();
    void removeSelectedWord();

    PublishSubject<List<WordPair>> getOnWordsListChange();
}
