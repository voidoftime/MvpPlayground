package com.home.oleg.mvpplayground.items.match.view;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.home.oleg.mvpplayground.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.subjects.PublishSubject;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.content)
        TextView content;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    private List<Item> items;
    public PublishSubject<String> onItemClick = PublishSubject.create();

    public ItemsAdapter(List<Item> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = items.get(position);

        holder.itemView.setOnClickListener(v -> onItemClick.onNext(item.text));
        holder.content.setText(item.text);
        holder.content.setBackgroundResource(item.isSelected ? R.color.selected_item_bg : R.color.regular_item_bg);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
