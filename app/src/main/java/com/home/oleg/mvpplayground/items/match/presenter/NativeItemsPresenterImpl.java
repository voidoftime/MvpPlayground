package com.home.oleg.mvpplayground.items.match.presenter;


import android.util.Log;

import com.home.oleg.mvpplayground.items.match.model.MatchItemsStore;
import com.home.oleg.mvpplayground.items.match.view.ForeignItemsView;

import javax.inject.Inject;

public class NativeItemsPresenterImpl implements NativeItemsPresenter {
    public static final String FIPI = "NIPI";
    private ForeignItemsView itemsView;
    private MatchItemsStore matchItemsStore;

    @Inject
    public NativeItemsPresenterImpl(MatchItemsStore matchItemsStore) {
        this.matchItemsStore = matchItemsStore;
        matchItemsStore.getOnWordsListChange().subscribe(wordList -> {
            itemsView.setItems(matchItemsStore.getNativeWords());
            itemsView.clearSelection();
        });
    }

    @Override
    public void attachView(ForeignItemsView view) {
        itemsView = view;
        view.setItems(matchItemsStore.getNativeWords());
        view.clearSelection();
    }

    @Override
    public void onClickItem(String value) {
        Log.d(FIPI, "onClickItem: "+value);
        matchItemsStore.setNativeSelectedWord(value);
        itemsView.setSelectedItem(matchItemsStore.getNativeSelectedWord());
        if(matchItemsStore.isWordsMatch()){
            matchItemsStore.removeSelectedWord();
//            itemsView.setItems(matchItemsStore.getNativeWords());
//            itemsView.clearSelection();
        }
    }
}
