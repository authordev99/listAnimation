package com.example.codingchallenge.Binding;


import android.databinding.ObservableArrayList;

import com.example.codingchallenge.RecyclerviewBinding.adapter.binder.ConditionalDataBinder;

public class BinderPosition extends ConditionalDataBinder {

    int position;
    ObservableArrayList<Object> list;


    public BinderPosition(int bindingVariable, int layoutId, ObservableArrayList<Object> list, int position) {
        super(bindingVariable, layoutId);

        this.list = list;
        this.position = position;

    }

    @Override
    public boolean canHandle(Object model) {
        return list.get(position) == model;
    }

}
