package com.example.codingchallenge.RecyclerviewBinding.adapter.binder;

public interface ItemBinder<T>
{
      int getLayoutRes(T model);
      int getBindingVariable(T model);
}
