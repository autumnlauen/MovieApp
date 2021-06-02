package com.example.movieinfoapp

import androidx.annotation.NonNull
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class NonNullObserver<T : Any>(val onChanged: (T) -> Unit) : Observer<T> {
    override fun onChanged(value: T?) {
        value!!.let(onChanged)
    }
}

class NonNullMutableLiveData<T : Any>(initialValue: T) : MutableLiveData<T>() {

    init {
        setValue(initialValue)
    }

    fun observe(owner: LifecycleOwner, observer: (T) -> Unit) {
        observe(owner, NonNullObserver(observer))
    }

    fun observe(owner: LifecycleOwner, observer: NonNullObserver<T>) {
        removeObservers(owner)

        super.observe(owner, observer)
    }

    @NonNull
    override fun getValue(): T {
        return super.getValue()!!
    }
}
