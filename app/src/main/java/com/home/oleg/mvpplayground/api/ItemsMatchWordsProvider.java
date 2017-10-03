package com.home.oleg.mvpplayground.api;


import com.home.oleg.mvpplayground.items.match.model.*;
import com.home.oleg.mvpplayground.items.match.model.WordPair;

import java.util.List;

import io.reactivex.Observable;

public interface ItemsMatchWordsProvider {
    Observable<List<WordPair>> getWords();
}
