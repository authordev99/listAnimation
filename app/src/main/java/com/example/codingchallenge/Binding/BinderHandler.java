package com.example.codingchallenge.Binding;


import com.example.codingchallenge.RecyclerviewBinding.adapter.ClickHandler;
import com.example.codingchallenge.RecyclerviewBinding.adapter.LongClickHandler;
import com.example.codingchallenge.RecyclerviewBinding.adapter.binder.ItemBinder;

/**
 * Created by Sudhan on 6/16/16.
 */
public interface BinderHandler<T> {

    ClickHandler<T> clickHandler();

    LongClickHandler<T> longClickHandler();

    ItemBinder<T> itemViewBinder();

}