package com.home.oleg.mvpplayground.items.match.presenter;


import android.util.Log;

import com.home.oleg.mvpplayground.base.BasePresenter;
import com.home.oleg.mvpplayground.items.match.model.MatchItemsStore;
import com.home.oleg.mvpplayground.items.match.view.ForeignItemsView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class ForeignItemsPresenterImpl implements ForeignItemsPresenter {
    public static final String FIPI = "FIPI";
    private ForeignItemsView itemsView;
    private MatchItemsStore matchItemsStore;

    @Inject
    public ForeignItemsPresenterImpl(MatchItemsStore matchItemsStore) {
        this.matchItemsStore = matchItemsStore;
        matchItemsStore.getOnWordsListChange()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(wordList -> {
            itemsView.setItems(matchItemsStore.getForeignWords());
            itemsView.clearSelection();
        });
    }

    @Override
    public void attachView(ForeignItemsView view) {
        itemsView = view;
        view.setItems(matchItemsStore.getForeignWords());
        view.clearSelection();
    }

    @Override
    public void onClickItem(String value) {
        Log.d(FIPI, "onClickItem: "+value);
        matchItemsStore.setForeignSelectedWord(value);
        itemsView.setSelectedItem(matchItemsStore.getForeignSelectedWord());
    }
}
