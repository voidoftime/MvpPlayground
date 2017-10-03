package com.home.oleg.mvpplayground.api;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface WordsApi {
    @GET("wordpairs")
    Observable<List<WordPair>> fetchWordPairs();
}
