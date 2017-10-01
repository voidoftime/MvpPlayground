package com.home.oleg.mvpplayground.items.match.view;


import java.util.List;

public interface ForeignItemsView {
    void setItems(List<String> items);
    void setSelectedItem(String index);
    void clearSelection();
//    void setRightItems(List<String> items);
    void clear();
}
