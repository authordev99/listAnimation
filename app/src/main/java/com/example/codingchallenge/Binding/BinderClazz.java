package com.example.codingchallenge.Binding;


import com.example.codingchallenge.RecyclerviewBinding.adapter.binder.ConditionalDataBinder;

public class BinderClazz extends ConditionalDataBinder {

    Class clazz;

    public BinderClazz(int bindingVariable, int layoutId, Class clazz) {
        super(bindingVariable, layoutId);
        this.clazz = clazz;
    }

    @Override
    public boolean canHandle(Object model) {
        return clazz.isInstance(model);
    }

}
