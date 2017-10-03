package com.home.oleg.mvpplayground.api;


import com.annimon.stream.Stream;
import com.home.oleg.mvpplayground.items.match.model.*;
import com.home.oleg.mvpplayground.items.match.model.WordPair;
import com.home.oleg.mvpplayground.settings.ApplicationSettings;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RemoteItemsMatchWordsProvider implements ItemsMatchWordsProvider {
    ApplicationSettings settings;

    private final WordsApi wordsApi;

    @Inject
    public RemoteItemsMatchWordsProvider(ApplicationSettings settings) {
        this.settings = settings;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(settings.getApiUrl())
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        wordsApi = retrofit.create(WordsApi.class);
    }

    @Override
    public Observable<List<WordPair>> getWords() {
        return wordsApi.fetchWordPairs()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(apiWP -> Stream.of(apiWP)
                        .map(v -> new WordPair(v.foreignWord, v.nativeWord))
                        .toList());
    }
}
