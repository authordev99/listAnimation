package com.example.codingchallenge.RecyclerviewBinding.adapter.binder;

public class ItemBinderBase<T> implements com.example.codingchallenge.RecyclerviewBinding.adapter.binder.ItemBinder<T>
{
    protected final int bindingVariable;
    protected final int layoutId;

    public ItemBinderBase(int bindingVariable, int layoutId)
    {
        this.bindingVariable = bindingVariable;
        this.layoutId = layoutId;
    }

    public int getLayoutRes(T model)
    {
        return layoutId;
    }

    public int getBindingVariable(T model)
    {
        return bindingVariable;
    }
}
