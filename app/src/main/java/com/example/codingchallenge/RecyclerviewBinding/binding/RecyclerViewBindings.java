package com.example.codingchallenge.RecyclerviewBinding.binding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.example.codingchallenge.RecyclerviewBinding.adapter.BindingRecyclerViewAdapter;
import com.example.codingchallenge.RecyclerviewBinding.adapter.ClickHandler;
import com.example.codingchallenge.RecyclerviewBinding.adapter.LongClickHandler;
import com.example.codingchallenge.RecyclerviewBinding.adapter.binder.ItemBinder;
import com.example.codingchallenge.Utils.BindingPresenter;

import java.util.Collection;

public class RecyclerViewBindings {
    private static final int KEY_ITEMS = -123;
    private static final int KEY_CLICK_HANDLER = -124;
    private static final int KEY_LONG_CLICK_HANDLER = -125;
    private static final int KEY_ITEM_CLICK_PRESENTER = -126;

    @SuppressWarnings("unchecked")
    @BindingAdapter(value = {"pagedItems", "scrollListener"}, requireAll = false)
    public static <T> void setPagedItems(RecyclerView recyclerView, Collection<T> items, RecyclerView.OnScrollListener onScrollListener) {
        BindingRecyclerViewAdapter<T> adapter = (BindingRecyclerViewAdapter<T>) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.setList(items);
        } else {
            recyclerView.setTag(KEY_ITEMS, items);
        }

        if (onScrollListener != null) {
            recyclerView.addOnScrollListener(onScrollListener);
        }

    }

    @SuppressWarnings("unchecked")
    @BindingAdapter("items")
    public static <T> void setItems(RecyclerView recyclerView, Collection<T> items) {
        BindingRecyclerViewAdapter<T> adapter = (BindingRecyclerViewAdapter<T>) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.setItems(items);
        } else {
            recyclerView.setTag(KEY_ITEMS, items);
        }
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter("clickHandler")
    public static <T> void setHandler(RecyclerView recyclerView, ClickHandler<T> handler) {
        BindingRecyclerViewAdapter<T> adapter = (BindingRecyclerViewAdapter<T>) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.setClickHandler(handler);
        } else {
            recyclerView.setTag(KEY_CLICK_HANDLER, handler);
        }
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter("itemClickPresenter")
    public static <T> void setPresenter(RecyclerView recyclerView, BindingPresenter presenter) {
        BindingRecyclerViewAdapter<T> adapter = (BindingRecyclerViewAdapter<T>) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.setItemClickPresenter(presenter);
        } else {
            recyclerView.setTag(KEY_ITEM_CLICK_PRESENTER, presenter);
        }
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter("longClickHandler")
    public static <T> void setHandler(RecyclerView recyclerView, LongClickHandler<T> handler) {
        BindingRecyclerViewAdapter<T> adapter = (BindingRecyclerViewAdapter<T>) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.setLongClickHandler(handler);
        } else {
            recyclerView.setTag(KEY_LONG_CLICK_HANDLER, handler);
        }
    }


    @SuppressWarnings("unchecked")
    @BindingAdapter(value = {"itemViewBinder", "fastScroller"}, requireAll = false)
    public static <T> void setItemViewBinder(RecyclerView recyclerView, ItemBinder<T> itemViewMapper, View fastScroller) {
        Collection<T> items = (Collection<T>) recyclerView.getTag(KEY_ITEMS);
        ClickHandler<T> clickHandler = (ClickHandler<T>) recyclerView.getTag(KEY_CLICK_HANDLER);
        LongClickHandler<T> longClickHandler = (LongClickHandler<T>) recyclerView.getTag(KEY_LONG_CLICK_HANDLER);

        BindingRecyclerViewAdapter<T> adapter = new BindingRecyclerViewAdapter<>(itemViewMapper, items);
        if (clickHandler != null) {
            adapter.setClickHandler(clickHandler);
        }

        if (longClickHandler != null) {
            adapter.setLongClickHandler(longClickHandler);
        }
        recyclerView.setAdapter(adapter);
    }
}