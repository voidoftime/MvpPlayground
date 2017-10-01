package com.home.oleg.mvpplayground.items.match.view;


import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.annimon.stream.Stream;
import com.home.oleg.mvpplayground.R;
import com.home.oleg.mvpplayground.items.match.presenter.ForeignItemsPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BindedForeignItemsView implements ForeignItemsView {
    public static final String TAG = "BiFoIView";
    private final List<Item> foreignWordStore = new ArrayList<>();
    private final ItemsAdapter foreignWordListAdapter;
    private Activity activity;
    private ForeignItemsPresenter foreignItemsPresenter;

    //    @BindView(R.id.leftList)
    RecyclerView foreignWordList;
//    @BindView(R.id.rightList)
//    RecyclerView rightList;

    public BindedForeignItemsView(Activity activity, int listViewId, ForeignItemsPresenter _foreignItemsPresenter) {
        this.activity = activity;
        this.foreignItemsPresenter = _foreignItemsPresenter;

        foreignWordList = activity.findViewById(listViewId);
        ButterKnife.bind(this, activity);

//        foreignWordStore.add(new Item("oleg"));

        foreignWordList.setHasFixedSize(true);
        foreignWordList.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        foreignWordListAdapter = new ItemsAdapter(foreignWordStore);
        foreignWordList.setAdapter(foreignWordListAdapter);
        foreignWordListAdapter.onItemClick.subscribe(text -> foreignItemsPresenter.onClickItem(text));

        foreignItemsPresenter.attachView(this);
    }


    @Override
    public void setItems(List<String> items) {
        foreignWordStore.clear();
        ArrayList<Item> itemsList = new ArrayList<>();
        for (String item : items) {
            itemsList.add(new Item(item));
        }
        foreignWordStore.addAll(itemsList);
        foreignWordListAdapter.notifyDataSetChanged();
        Log.d(TAG, "setItems: " + items);
    }

    @Override
    public void setSelectedItem(String value) {
        for (int i = 0; i < foreignWordStore.size(); i++) {
            Item item = foreignWordStore.get(i);
            if (!item.isSelected) continue;
            item.isSelected = false;
            foreignWordListAdapter.notifyItemChanged(i);
        }
        Stream.of(foreignWordStore).indexed()
                .filter(pair -> pair.getSecond().text.equals(value))
                .forEach(pair -> {
                    pair.getSecond().isSelected = true;
                    foreignWordListAdapter.notifyItemChanged(pair.getFirst());
                });
    }

    @Override
    public void clearSelection() {
        for (Item item : foreignWordStore) {
            item.isSelected = false;
        }
        foreignWordListAdapter.notifyDataSetChanged();
    }

    @Override
    public void clear() {
        foreignWordStore.clear();
        foreignWordListAdapter.notifyDataSetChanged();
    }
}
