package com.home.oleg.mvpplayground.counter.view;


import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.home.oleg.mvpplayground.R;
import com.home.oleg.mvpplayground.counter.presenter.CounterPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BindedCounterView implements CounterView {
    private CounterPresenter counterPresenter;

    private Activity activity;

    @BindView(R.id.nextButton)
    Button nextButton;
    @BindView(R.id.resetButton)
    Button resetButton;
    @BindView(R.id.currentCount)
    TextView currentCountText;

    public BindedCounterView(Activity activity, CounterPresenter _counterPresenter) {
        Log.d("BindV", "BindedCounterView " + activity);
        this.activity = activity;
        this.counterPresenter = _counterPresenter;
        ButterKnife.bind(this, activity);

        nextButton.setOnClickListener(v -> counterPresenter.onNext());
        resetButton.setOnClickListener(v -> counterPresenter.onReset());

        counterPresenter.attach(this);
    }

    @Override
    public void setCurrentCount(int value) {
        currentCountText.setText("" + value);
    }

}
