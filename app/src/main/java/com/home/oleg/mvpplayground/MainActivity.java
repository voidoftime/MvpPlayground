package com.home.oleg.mvpplayground;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.home.oleg.mvpplayground.counter.presenter.CounterPresenter;
import com.home.oleg.mvpplayground.counter.view.BindedCounterView;
import com.home.oleg.mvpplayground.items.match.presenter.ForeignItemsPresenter;
import com.home.oleg.mvpplayground.items.match.presenter.NativeItemsPresenter;
import com.home.oleg.mvpplayground.items.match.view.BindedForeignItemsView;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    //    @Inject
//    CounterView counterView;
    @Inject
    CounterPresenter counterPresenter;
    @Inject
    ForeignItemsPresenter foreignItemsPresenter;
    @Inject
    NativeItemsPresenter nativeItemsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("MAIN ACTIVITY ON CREATE");
        setContentView(R.layout.activity_main);
        Log.d(TAG, "before build component " + getApplication());
        ((MainApp) getApplication()).getMainComponent().inject(this);
//        Log.d(TAG, ""+counterView);
        Log.d(TAG, "counterPresenter " + counterPresenter);
        Log.d(TAG, "foreignItemsPresenter " + foreignItemsPresenter);
        new BindedCounterView(this, counterPresenter);
        new BindedForeignItemsView(this,R.id.leftList,foreignItemsPresenter);
        new BindedForeignItemsView(this,R.id.rightList,nativeItemsPresenter);
    }
}
