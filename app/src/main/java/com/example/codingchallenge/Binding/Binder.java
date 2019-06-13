package com.example.codingchallenge.Binding;


import com.example.codingchallenge.RecyclerviewBinding.adapter.binder.ConditionalDataBinder;

public class Binder extends ConditionalDataBinder {

    public Binder(int bindingVariable, int layoutId) {
        super(bindingVariable, layoutId);
    }

    @Override
    public boolean canHandle(Object model) {
        return true;
    }

}
