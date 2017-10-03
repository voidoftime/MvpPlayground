package com.home.oleg.mvpplayground.api;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.junit.Test;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class FetchTest {
    @Test
    public void test() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.mocky.io/v2/59d3d4a52700004c0107b1f5/")
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        WordsApi wordsApi = retrofit.create(WordsApi.class);
        wordsApi.fetchWordPairs()
                .subscribe(
                        words -> {
                            System.out.println("GOT " + words);
                            words.forEach(wp->System.out.println("WP " + wp));
                        },
                        e -> {
                            System.out.println("ERR " + e);
                        },
                        () -> {
                            System.out.println("DONE");
                        });
    }
}
